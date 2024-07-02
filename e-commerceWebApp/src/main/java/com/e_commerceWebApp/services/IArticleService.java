package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;

import java.util.List;

public interface IArticleService {
    List<Article> getAllArticles();

    boolean existsById(String articleId);

    int updateArticle(Article article);

    Article getArticleById(String articleId);
}
