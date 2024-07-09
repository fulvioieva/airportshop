package com.e_commerceWebApp.controller;

import com.e_commerceWebApp.entity.*;
import com.e_commerceWebApp.repository.CartRepository;
import com.e_commerceWebApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "e_commerce/order")
public class OrderController {

    @Autowired
    private CartService cS;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderService oS;

    @Autowired
    private UserService uS;

    @Autowired
    private ArticleService aS;

    @Autowired
    private ArticleCartService aCS;

    @Autowired
    private ArticleOrderService aOS;


    //ROTTA PER TUTTI GLI ARTICOLI

    @GetMapping(value = "allArticles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllArticles() {
        List<ArticleOrder> articleList = aOS.getAllArticleOrder();
        if (articleList.isEmpty()) {
            return new ResponseEntity<List<ArticleOrder>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ArticleOrder>>(articleList, HttpStatus.OK);
    }

    //ROTTA PER L'ORDER CON ID SPECIFICO

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCartById(@PathVariable("id") Integer id) {
        Order order = oS.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<String>("ordine non trovato", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    //ROTTA PER AGGIUNGERE UN ARTICOLO
    @PostMapping("/orders/{userId}")
    public ResponseEntity<?> createOrder(@PathVariable("userId") int userId, @RequestBody Order orderDetails) {
        // Ottieni il Cart dell'utente
        if (!cS.existsById(userId)) {
            return new ResponseEntity<String>("Carrello non trovato", HttpStatus.NOT_FOUND);
        }
        Cart cart = cS.getCartById(userId);
        boolean orderCreated=false;
        if (cart != null) {
            // Crea un nuovo Order
            Order order = new Order();
            order.setUser(uS.getUserById(userId));
            order.setTypePayment(orderDetails.getTypePayment());
            order.setState(orderDetails.getState());
            order.setTotalPrice(orderDetails.getTotalPrice());

            // Salva l'Order per generare l'ID
            oS.saveOrder(order);

            // Clona gli Article nel cart in ArticleOrder
            for (Article article : cart.getArticles()) {
                ArticleOrder articleOrder = new ArticleOrder();
                ArticleOrderId artOrderId = new ArticleOrderId();

                //imposta l'articleOrderId di articleOrder
                artOrderId.setOrderId(order.getId());
                artOrderId.setArticleId(article.getIdArticle());
                articleOrder.setId(artOrderId);

                // ottiene la quantità ordinata per l'articolo corrente
                ArticleCartId artCartId = new ArticleCartId();
                artCartId.setArticleId(article.getIdArticle());
                artCartId.setCartId(userId);

                if (!aCS.exists(artCartId)) {
                    return new ResponseEntity<String>("Articolo non presente nel cart", HttpStatus.NOT_FOUND);
                }
                ArticleCart artCart = aCS.findById(artCartId);

                articleOrder.setQtyOrdered(artCart.getQtyOrdered());
                articleOrder.setArticle(article);
                order.setTotalPrice(order.getTotalPrice()
                        .add(BigDecimal.valueOf(artCart.getQtyOrdered()).multiply(article.getPrice())));
                //oS.saveOrder(order);
                articleOrder.setOrder(order);

                if (aOS.save(articleOrder)){
                    article.setQtyAvailable((article.getQtyAvailable()-artCart.getQtyOrdered()));
                    aS.saveArticle(article);
                }
            }
        }
        // Elimina il carrello

        //recupero gliarticleCArt
        List<ArticleCart> articlesCart=  aCS.getAllArticleCartfromCart(userId);
        for (ArticleCart sc : articlesCart) {
            // elimino gli articleCart
            aCS.deleteArticleFromCart(sc.getCart().getId(),sc.getArticle().getIdArticle());
        }
        //elimino infine il carrello
        cS.deleteCart(cart);

        return new ResponseEntity<String>("ordine creato", HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<?> deleteCart(@RequestParam("userId") int userId) {
        // Ottieni il carrello dell'utente
        Cart cart = cS.getCartById(userId);
        if (cart == null) {
            return new ResponseEntity<String>("Carrello non trovato", HttpStatus.NOT_FOUND);
        }
        // Elimina il carrello
        cS.deleteCart(cart);
        return new ResponseEntity<String>("carrello eliminato",HttpStatus.OK);
    }

    @DeleteMapping("/order")
    public ResponseEntity<?> deleteOrder(@RequestParam("orderId") int orderId) {
        int counter=0;
        // Ottieni il carrello dell'utente
        Order order = oS.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<String>("Ordine non trovato", HttpStatus.NOT_FOUND);
        }
        List<String> allArticlesId = aOS.getAllArticleIdFromOrder(orderId);
        System.out.println("lista articoli = " + allArticlesId.toString());
        if (!allArticlesId.isEmpty()) {

            for (String articleId : allArticlesId) {
                counter++;

                System.out.println("contatore nel delete item" + counter);
                // Esegui l'operazione qui

                //Article article= aS.getArticleById(articleId);

                //aS.saveArticle(article);
                aOS.deleteArticleFromOrder(orderId,articleId);

            }
        }
        // Elimina il carrello
        oS.deleteOrder(order);
        return new ResponseEntity<String>("ordine eliminato " + counter,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderId}/articles/{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteArticleFromOrder(@PathVariable("orderId") int orderId, @PathVariable("articleId") String articleId) {

        if (!aOS.existsArticleInOrder(orderId,articleId)) {
            return new ResponseEntity<String>("non trovo l'articolo", HttpStatus.NOT_FOUND);
        }
        Article article= aS.getArticleById(articleId);
        // Ottieni l'ArticleCart
        ArticleOrderId articleorderId = new ArticleOrderId();
        articleorderId.setOrderId(orderId);
        articleorderId.setArticleId(articleId);
        ArticleOrder articleorder = aOS.findById(articleorderId);
        // Calcola il costo totale dell'Article nel Cart
        BigDecimal articleTotalCost = articleorder.getArticle().getPrice().multiply(BigDecimal.valueOf(articleorder.getQtyOrdered()));

        // Ottieni il Cart
        Order order = oS.getOrderById(orderId);

        // Aggiorna il total_price nel Cart
        order.setTotalPrice(order.getTotalPrice().subtract(articleTotalCost));
        int availableQty=article.getQtyAvailable();
        int orderedQty =articleorder.getQtyOrdered();
        article.setQtyAvailable(availableQty+orderedQty);
        //oS.saveOrder(order);
        //aS.saveArticle(article);
        boolean result = aOS.deleteArticleFromOrder(orderId, articleId);

        if (!result){
            return new ResponseEntity<String>("Problemi nella eliminazione",HttpStatus.EXPECTATION_FAILED);

        }

        return new ResponseEntity<String>("Articolo eliminato",HttpStatus.OK);
    }
}
