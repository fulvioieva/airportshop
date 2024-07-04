package com.e_commerceWebApp.controller;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.ArticleCart;
import com.e_commerceWebApp.entity.ArticleCartId;
import com.e_commerceWebApp.entity.Cart;
import com.e_commerceWebApp.repository.CartRepository;
import com.e_commerceWebApp.services.ArticleCartService;
import com.e_commerceWebApp.services.ArticleService;
import com.e_commerceWebApp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "e_commerce/cart")
public class CartController {

    @Autowired
    CartService cS;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    ArticleService aS;

    @Autowired
    ArticleCartService aCS;

    @GetMapping(value = "all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ArticleCart>> getAllArticles() {
        List<ArticleCart> articleList = aCS.getAllArticleCart();
        if (articleList.isEmpty()) {
            return new ResponseEntity<List<ArticleCart>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ArticleCart>>(articleList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id) {
        Cart cart = cS.getCartById(id);
        if (cart == null) {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/addArticle{id}", produces = { MediaType.APPLICATION_JSON_VALUE })


    public boolean addArticleIntoCart( @PathVariable("id") String articleId,
                                       @RequestParam("userId") int userId,
                                        @RequestParam("qtyOrdered") int qtyOrdered)  {
        System.out.println(articleId + " "+ userId + " "+ qtyOrdered);
        // Verifica l'esistenza dell'articolo
        if (!aS.existsById(articleId)) {
            System.out.println("non trovo l'articolo");
            return false;
        }
        // Ottieni l'articolo
        Article article = aS.getArticleById(articleId);
        System.out.println(article.toString() + "ARTICOLO RECUPERATO");
        // Verifica se la quantità ordinata è disponibile
        if (article.getQtyAvailable() < qtyOrdered) {

            System.out.println("non abbastanza articoli, solo " + article.getQtyAvailable() + "articoli trovati");
            return false;

        }
        // Cerca un carrello esistente per l'utente
        System.out.println("verifico l'esistenza del cart");

        Cart cart = cS.getCartById(userId);
        System.out.println("recupero il cart :" + cart);

        // Se non esiste un carrello, creane uno nuovo
        if (cart == null) {
            System.out.println("creo un carrello");

            cart = new Cart();
            cart.setId(userId);
            cart.setUserId(userId);
            cart.setTotalPrice(BigDecimal.ZERO);
            cS.saveCart(cart);

        }
        // Crea i nuovi oggetti

        ArticleCartId newIdCart = new ArticleCartId();
        ArticleCart newItem = new ArticleCart();
        // Imposta i nuovi oggetti

        newIdCart.setArticleId(articleId);
        newIdCart.setCartId(userId);
        newItem.setQtyOrdered(qtyOrdered);

        // Aggiorna la quantità disponibile dell'articolo
        //int qtyAvail = article.getQtyAvailable();
        //article.setQtyAvailable(qtyAvail - qtyOrdered);

        // Aggiorna il prezzo totale del carrello
        BigDecimal price = cart.getTotalPrice();
        BigDecimal unitPrice = article.getPrice();
        BigDecimal qty = BigDecimal.valueOf(qtyOrdered);


        cart.setTotalPrice(price.add(unitPrice.multiply(qty)));

        System.out.println(newItem.toString());

        // Salva i nuovi oggetti
        aCS.save(newItem);
        System.out.println("QUI 10");

        aS.saveArticle(article);
        System.out.println("QUI 11");

        cS.saveCart(cart);
        return true;
    }

    @GetMapping(value = "/{cartId}/articles/{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> existsArticleInCart(@PathVariable("cartId") int cartId, @PathVariable("articleId") String articleId) {
        boolean result = aCS.existsArticleInCart(cartId, articleId);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cartId}/articles/{articleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteArticleFromCart(@PathVariable("cartId") int cartId, @PathVariable("articleId") String articleId) {
        boolean result = aCS.deleteArticleFromCart(cartId, articleId);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
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

}
