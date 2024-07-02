package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.entity.Cart;

import java.util.List;
import java.util.Optional;

public class ArticleCartService implements IArticleCartService{
    @Override
    public boolean addArticle(Article article, int idOrder, int qtaOrdered) {
        return false;
    }

    @Override
    public boolean existsArticle(int idOrder, String idArticle) {
        return false;
    }

    @Override
    public boolean deleteArticle(int idOrder, String idArticle) {
        return false;
    }

    @Override
    public boolean deleteAllArticles(int idOrder) {
        return false;
    }

    @Override
    public Optional<Cart> getArticle(int idOrder, String idArticle) {
        return Optional.empty();
    }

    @Override
    public List<Cart> getAllArticles(int idOrder) {
        return List.of();
    }

    @Override
    public int computePriceTotal(int idOrder) {
        return 0;
    }
}
