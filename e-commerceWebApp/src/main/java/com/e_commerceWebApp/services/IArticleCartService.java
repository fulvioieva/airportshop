package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface IArticleCartService {
    boolean   addArticle(Article article, int idOrder, int qtaOrdered);
    boolean   existsArticle(int idOrder, String idArticle);
    boolean   deleteArticle(int idOrder, String idArticle);
    boolean   deleteAllArticles(int idOrder);
    Optional<Cart> getArticle(int idOrder, String idArticle);
    List< Cart > getAllArticles(int idOrder);
    int computePriceTotal(int idOrder);
}
