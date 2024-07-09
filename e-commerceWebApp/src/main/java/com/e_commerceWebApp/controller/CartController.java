package com.e_commerceWebApp.controller;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.ArticleCart;
import com.e_commerceWebApp.entity.ArticleCartId;
import com.e_commerceWebApp.entity.Cart;
import com.e_commerceWebApp.repository.CartRepository;
import com.e_commerceWebApp.services.ArticleCartService;
import com.e_commerceWebApp.services.ArticleService;
import com.e_commerceWebApp.services.CartService;
import com.e_commerceWebApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
import java.lang.constant.DynamicCallSiteDesc;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "e_commerce/cart")
public class CartController {

    @Autowired
    CartService cS;

    @Autowired
    UserService uS;

    @Autowired
    ArticleService aS;

    @Autowired
    ArticleCartService aCS;

    //ROTTA PER TUTTI GLI ARTICOLI

    @GetMapping(value = "allArticles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ArticleCart>> getAllArticles() {
        List<ArticleCart> articleList = aCS.getAllArticleCart();
        if (articleList.isEmpty()) {
            return new ResponseEntity<List<ArticleCart>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ArticleCart>>(articleList, HttpStatus.OK);
    }

    //ROTTA PER IL CART CON ID SPECIFICO

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id) {
        Cart cart = cS.getCartById(id);
        if (cart == null) {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    //ROTTA PER AGGIUNGERE UN ARTICOLO

    @PostMapping(value = "/addArticle{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addArticleIntoCart(@PathVariable("id") String articleId,
                                                @RequestParam("userId") int userId,
                                                @RequestParam("qtyOrdered") int qtyOrdered) {
        if (qtyOrdered<=0) {
            return new ResponseEntity<String>("quantità non gestibile", HttpStatus.BAD_REQUEST);
        }

        // Verifica l'esistenza dell'articolo
        if (!aS.existsById(articleId)) {
            return new ResponseEntity<String>("articolo non trovato", HttpStatus.NOT_FOUND);
        }
        // Ottieni l'articolo
        Article article = aS.getArticleById(articleId);
        System.out.println(article.toString() + "ARTICOLO RECUPERATO");
        // Verifica se la quantità ordinata è disponibile
        if (article.getQtyAvailable() < qtyOrdered) {
            System.out.println("non abbastanza articoli, solo " + article.getQtyAvailable() + "articoli trovati");
            return new ResponseEntity<String>("non abbastanza articoli, solo " + article.getQtyAvailable() + "articoli trovati"
                    , HttpStatus.NOT_ACCEPTABLE);
        }

        // Cerca un carrello esistente per l'utente
        System.out.println("verifico l'esistenza del cart");

        Cart cart = cS.getCartById(userId);
        System.out.println("recupero il cart :" + cart);
        // Se non esiste un carrello, creane uno nuovo
        if (cart == null) {
            System.out.println("creo un carrello per utente id:" + userId + " e con articolo :" + articleId);

            cart = new Cart();
            cart.setId(userId);
            cart.setUserId(userId);
            cart.setTotalPrice(BigDecimal.ZERO);
            cart.setUser(uS.getUserById(userId));
            cart.setArticles(Collections.singletonList(aS.getArticleById(articleId)));
            //cS.saveCart(cart);
        }
        // istanzio articleCart e il relativo id
        ArticleCartId newIdCart = new ArticleCartId();
        ArticleCart newItem = new ArticleCart();

        // Imposta i nuovi oggetti
        newIdCart.setArticleId(articleId);
        newIdCart.setCartId(userId);
        System.out.println("questo è l'idcart" + newIdCart);

        // verifico l'esistenza di articleCart

        if (!aCS.exists(newIdCart)) {

            //inserisco l'articleCartId nel nuovo articleCart
            newItem.setId(newIdCart);
            newItem.setArticle(article);

        } else {
            //recupero l'articleCart esistente
            newItem = aCS.findById(newIdCart);
        }

        if (newItem.getQtyOrdered() == null||newItem.getQtyOrdered()==0) {
            //imposto il valore dell'oggetto vuoto
            newItem.setQtyOrdered(qtyOrdered);
        } else {
            //aggiorno la quantità dell'oggetto nel carrello
            int qtyArticle = newItem.getQtyOrdered();
            newItem.setQtyOrdered(qtyArticle + qtyOrdered);

        }

        // Aggiorna la quantità disponibile dell'articolo
        //int qtyAvail = article.getQtyAvailable();
        //article.setQtyAvailable(qtyAvail - qtyOrdered);

        // Aggiorna il prezzo totale del carrello
        BigDecimal price = cart.getTotalPrice();
        BigDecimal unitPrice = article.getPrice();
        BigDecimal qty = BigDecimal.valueOf(qtyOrdered);

        cart.setTotalPrice(price.add(unitPrice.multiply(qty)));

        System.out.println("il cart è : "+cart);
        cS.saveCart(cart);

        //inserisco il carrello aggiornato nell'oggetto articleCart
        newItem.setCart(cart);

        // Salva l'articleCart
        if (aCS.save(newItem)) {
            System.out.println("Salvato ");
        }
        //aS.saveArticle(article);
        return new ResponseEntity<ArticleCart>(newItem, HttpStatus.ACCEPTED);
    }

    /*@GetMapping(value = "/{cartId}/articles/{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> existsArticleInCart(@PathVariable("cartId") int cartId, @PathVariable("articleId") String articleId) {
        boolean result = aCS.existsArticleInCart(cartId, articleId);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }*/

    @DeleteMapping(value = "/{cartId}/articles/{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteArticleFromCart(@PathVariable("cartId") int cartId, @PathVariable("articleId") String articleId) {


        if (!aCS.existsArticleInCart(cartId,articleId)) {
            return new ResponseEntity<String>("non trovo l'articolo", HttpStatus.NOT_FOUND);
        }
        // Ottieni l'ArticleCart
        ArticleCartId articleCartId = new ArticleCartId();
        articleCartId.setCartId(cartId);
        articleCartId.setArticleId(articleId);
        ArticleCart articleCart = aCS.findById(articleCartId);
        // Calcola il costo totale dell'Article nel Cart
        BigDecimal articleTotalCost = articleCart.getArticle().getPrice().multiply(BigDecimal.valueOf(articleCart.getQtyOrdered()));

        // Ottieni il Cart
        Cart cart = cS.getCartById(cartId);

        // Aggiorna il total_price nel Cart
        cart.setTotalPrice(cart.getTotalPrice().subtract(articleTotalCost));
        cS.saveCart(cart);

        boolean result = aCS.deleteArticleFromCart(cartId, articleId);

        return new ResponseEntity<String>("Articolo eliminato", result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{cartId}/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Article>> getAllArticlesFromCart(@PathVariable("cartId") int cartId) {
        List<Article> articles = aCS.getAllArticleFromCart(cartId);
        if (articles.isEmpty()) {
            return new ResponseEntity<List<Article>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/articles/{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Article>> getAllArticleFromCartById(@PathVariable("articleId") String articleId) {
        List<Article> articles = aCS.getAllArticleFromCartById(articleId);
        if (articles.isEmpty()) {
            return new ResponseEntity<List<Article>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cS.getAllCarts();
        if (carts.isEmpty()) {
            return new ResponseEntity<List<Cart>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
    }


    @PutMapping(value = "/cart/article{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateArticleCart(@PathVariable("articleId") String articleId,
                                               @RequestParam("userId") int userId,
                                               @RequestParam("qtyOrdered") int qtyOrdered) {

        if (qtyOrdered<0) {
            return new ResponseEntity<String>("quantità non gestibile", HttpStatus.BAD_REQUEST);
        }

        if (!aS.existsById(articleId)) {
            return new ResponseEntity<String>("articolo non trovato", HttpStatus.NOT_FOUND);
        }
        // Ottieni l'articolo
        Article article = aS.getArticleById(articleId);
        System.out.println(article.toString() + "ARTICOLO RECUPERATO");
        // Verifica se la quantità ordinata è disponibile
        if (article.getQtyAvailable() < qtyOrdered) {
            System.out.println("non abbastanza articoli, solo " + article.getQtyAvailable() + "articoli trovati");
            return new ResponseEntity<String>("non abbastanza articoli, solo " + article.getQtyAvailable() + "articoli trovati"
                    , HttpStatus.NOT_ACCEPTABLE);
        }

        ArticleCartId newIdCart = new ArticleCartId();
        ArticleCart newItem = new ArticleCart();

        // Imposta i nuovi oggetti
        newIdCart.setArticleId(articleId);
        newIdCart.setCartId(userId);

        if (!aCS.exists(newIdCart)) {
            return new ResponseEntity<String>("non è stato trovato questo elemento"
                    , HttpStatus.NOT_FOUND);
        }
        newItem = aCS.findById(newIdCart);

        Cart cart = cS.getCartById(userId);

        newItem.setQtyOrdered(qtyOrdered);

        cart.setTotalPrice(article.getPrice().multiply(BigDecimal.valueOf(qtyOrdered)));
        cS.saveCart(cart);

        newItem.setCart(cart);

        // Salva l'articleCart
        if (aCS.save(newItem)) {
            System.out.println("Salvato ");
        }
        return new ResponseEntity<ArticleCart>(newItem, HttpStatus.ACCEPTED);
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

}
