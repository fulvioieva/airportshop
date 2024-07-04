package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.ArticleCart;
import com.e_commerceWebApp.entity.ArticleCartId;
import com.e_commerceWebApp.repository.ArticleCartRepository;
import com.e_commerceWebApp.repository.ArticleRepository;
import com.e_commerceWebApp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCartService implements IArticleCartService{

    @Autowired
    ArticleRepository artRepo;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    ArticleCartRepository aCRepo;

    @Autowired
    ArticleService aS;

    @Autowired
    CartService cS;



    @Override
    public boolean addArticle(String articleId, int cartId, int qtaOrdered) {
        //verifico l'esistenza del'articolo
        if (!aS.existsById(articleId)||aS.getArticleById(articleId).getQtyAvailable()<qtaOrdered){
        return false;
        }
        //verifico l'esistenza del carrello
        if (!cS.existsById(cartId)){
            return false;
        }
        //istanzio i nuovi oggetti
        ArticleCartId newIdCart= new ArticleCartId();
        ArticleCart newItem=new ArticleCart();

        //salvo i nuovi oggetti
        newIdCart.setArticleId(articleId);
        newIdCart.setCartId(cartId);

        newItem.setId(newIdCart);
        newItem.setQtyOrdered(qtaOrdered);
        aCRepo.save(newItem);

        return true;
    }

    @Override
    public boolean existsArticleInCart(int cartId, String articleId) {
        List<ArticleCart> cart=aCRepo.findByCartId(cartId);
        if (cart==null||cart.isEmpty()) {
            return false;
        }
        return cart.stream().anyMatch(articleCart -> articleCart.getId().getArticleId().equals(articleId));
    }

    @Override
    public boolean deleteArticleFromCart(int cartId, String articleId) {
        // Crea un nuovo oggetto ArticleCartId e imposta i suoi valori
        ArticleCartId acId = new ArticleCartId();
        acId.setArticleId(articleId);
        acId.setCartId(cartId);
        // Controlla se esiste un ArticleCart con l'ID specificato
        if (aCRepo.existsById(acId)) {
            // Se esiste, ottieni la quantità ordinata e la quantità disponibile dell'articolo
            int qty = aCRepo.findById(acId).get().getQtyOrdered();
           // int availQty = aS.getArticleById(articleId).getQtyAvailable();
            // Elimina l'ArticleCart dal repository
            aCRepo.deleteById(acId);
            // Aggiorna la quantità disponibile dell'articolo
           // aS.getArticleById(articleId).setQtyAvailable(availQty + qty);
            return true;
        }
        return false;
    }

    @Override
    public List<ArticleCart> getAllArticleCart() {
        // Restituisce tutti gli ArticleCart dal repository
        return aCRepo.findAll();
    }

    @Override
    public List<Integer> getAllCartId() {
        // Ottiene tutti gli ID dei carrelli da tutti gli ArticleCart nel repository
        return aCRepo.findAll().stream()
                .map(e -> e.getCart().getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getAllArticles() {
        // Ottiene tutti gli articoli da tutti gli ArticleCart nel repository
        List<Article> articleList = aCRepo.findAll().stream()
                .map(ArticleCart::getArticle)
                .collect(Collectors.toList());
        return articleList;
    }

    @Override
    public List<Article> getAllArticleFromCart(int idCart) {
        // Ottiene tutti gli articoli da un carrello specifico
        return aCRepo.findByCartId(idCart).stream()
                .map(ArticleCart::getArticle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getAllArticleFromCartById(String articleId) {
        // Ottiene tutti gli articoli con un ID specifico da tutti i carrelli
        return aCRepo.findByArticleId(articleId).stream()
                .map(ArticleCart::getArticle)
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(ArticleCart articleCart) {
        if (articleCart != null&&aCRepo.existsById(articleCart.getId())) {
            aCRepo.save(articleCart);
            return true;
        }
        return false;
    }

}
