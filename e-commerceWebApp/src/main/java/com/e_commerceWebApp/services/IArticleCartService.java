package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.ArticleCart;

import java.util.List;

public interface IArticleCartService {
    boolean   addArticle(String articleId, int cartId, int qtaOrdered);
    boolean   existsArticleInCart(int cartId, String articleId);
    boolean   deleteArticleFromCart(int cartId, String articleId);

    List<ArticleCart> getAllArticleCart();
    List<Integer> getAllCartId();
    List<Article> getAllArticles();
    List<Article> getAllArticleFromCart(int idCart);
    List<Article> getAllArticleFromCartById(String articleId);

    boolean save(ArticleCart articleCart);
}
