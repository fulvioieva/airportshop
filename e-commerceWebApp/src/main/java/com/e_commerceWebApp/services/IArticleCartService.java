package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.ArticleCart;
import com.e_commerceWebApp.entity.ArticleCartId;

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
    ArticleCart findById(ArticleCartId id);
    boolean save(ArticleCart articleCart);

    boolean exists(ArticleCartId newIdCart);

    List<ArticleCart> getAllArticleCartfromCart(int userId);
}
