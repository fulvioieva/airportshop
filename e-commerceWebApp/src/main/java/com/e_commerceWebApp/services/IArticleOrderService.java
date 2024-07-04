package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.ArticleOrder;


import java.util.List;

public interface IArticleOrderService {
    boolean   addArticle(String articleId, int orderId, int qtaOrdered);
    boolean   existsArticleInOrder(int orderId, String articleId);
    boolean   deleteArticleFromOrder(int orderId, String articleId);

    List<ArticleOrder> getAllArticleOrder();
    List<Integer> getAllOrderId();
    List<Article> getAllArticles();
    List<Article> getAllArticleFromOrder(int idOrder);
    List<Article> getAllArticleFromOrderById(String articleId);

    boolean save(ArticleOrder articleOrder);
}
