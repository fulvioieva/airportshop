package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.*;
import com.e_commerceWebApp.repository.ArticleOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleOrderService implements IArticleOrderService{

    @Autowired
    private ArticleService aS;

    @Autowired
    private ArticleOrderRepository aOR;

    @Autowired
    private CartService cS;

    @Autowired
    private OrderService oS;


    @Override
    public boolean addArticle(String articleId, int orderId, int qtaOrdered) {
        //verifico l'esistenza del'articolo
        if (!aS.existsById(articleId)||aS.getArticleById(articleId).getQtyAvailable()<qtaOrdered){
            return false;
        }
        //verifico l'esistenza del carrello
        if (!cS.existsById(orderId)){
            return false;
        }
        //istanzio i nuovi oggetti
        ArticleOrderId newIdOrder= new ArticleOrderId();
        ArticleOrder newItem=new ArticleOrder();

        //salvo i nuovi oggetti
        newIdOrder.setArticleId(articleId);
        newIdOrder.setOrderId(orderId);

        newItem.setId(newIdOrder);
        newItem.setQtyOrdered(qtaOrdered);
        aOR.save(newItem);

        //aggiorno la quantità disponibile a magazzino
        int qtyAvailable=aS.getArticleById(articleId).getQtyAvailable();
        aS.getArticleById(articleId).setQtyAvailable(qtyAvailable - qtaOrdered);

        return true;
    }


    @Override
    public boolean existsArticleInOrder(int orderId, String articleId) {
        //verifica che l'ordine non sia vuoto
        List<ArticleOrder> order= aOR.findByOrderId(orderId);
        if (order==null||order.isEmpty()){
            return false;
        }
        return order.stream().anyMatch(articleOrder -> articleOrder.getId().getArticleId().equals(articleId));
    }

    @Override
    public boolean deleteArticleFromOrder(int orderId, String articleId) {
        // Crea un nuovo oggetto ArticleOrderId e imposta i suoi valori
        ArticleOrderId aoId = new ArticleOrderId();
        aoId.setOrderId(orderId);
        aoId.setArticleId(articleId);
        // Controlla se esiste un ArticleOrder con l'ID specificato
        if (aOR.existsById(aoId)) {
            // Se esiste, ottieni la quantità ordinata e la quantità disponibile dell'articolo
            int qty = aOR.findById(aoId).get().getQtyOrdered();
            int availQty = aS.getArticleById(articleId).getQtyAvailable();
            // Elimina l'ArticleCart dal repository
            aOR.deleteById(aoId);
            // Aggiorna la quantità disponibile dell'articolo
             aS.getArticleById(articleId).setQtyAvailable(availQty + qty);
            return true;
        }
        return false;
    }

    @Override
    public List<ArticleOrder> getAllArticleOrder() {
        // Restituisce tutti gli ArticleOrder dal repository
        return aOR.findAll();
    }

    @Override
    public List<Integer> getAllOrderId() {
        // Ottiene tutti gli ID dei carrelli da tutti gli ArticleCart nel repository
        return aOR.findAll().stream()
                .map(e -> e.getOrder().getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getAllArticles() {
        // Ottiene tutti gli articoli da tutti gli ArticleCart nel repository
        List<Article> articleList = aOR.findAll().stream()
                .map(ArticleOrder::getArticle)
                .collect(Collectors.toList());
        return articleList;
    }

    @Override
    public List<Article> getAllArticleFromOrder(int idOrder) {
        // Ottiene tutti gli articoli da tutti gli articoli di un ordine nel repository
        return aOR.findAll().stream()
                .map(ArticleOrder::getArticle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getAllArticleFromOrderById(String articleId) {
        // Ottiene tutti gli articoli da un ordine specifico
        return aOR.findByArticleId(articleId).stream()
                .map(ArticleOrder::getArticle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllArticleIdFromOrder(int orderId) {
    return aOR.findByOrderId(orderId).stream()
            .map(ArticleOrder::getArticle).collect(Collectors.toList()).stream()
            .map(Article::getIdArticle).collect(Collectors.toList());
    }

    @Override
    public boolean save(ArticleOrder articleOrder) {
        if (articleOrder != null&&oS.existsById(articleOrder.getId().getOrderId())) {
            aOR.save(articleOrder);
            return true;
        }
        return false;
    }

    @Override
    public ArticleOrder findById(ArticleOrderId articleorderId) {
        if (aOR.existsById(articleorderId)){
        return aOR.findById(articleorderId).get();
        }
        return null;
    }
}
