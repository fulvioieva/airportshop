package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;

@Service
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleRepository articleRepo;

    @Override
    public List<Article> getAllArticles() {

        return articleRepo.findAll();
    }

    @Override
    public boolean existsById(String articleId) {
        return articleRepo.existsById(articleId);
    }

    @Override
    public int updateArticle(Article article) {
        if (articleRepo.existsById(article.getIdArticle())){
            articleRepo.save(article);
            return 1;
        }
        return 0;
    }

    @Override
    public Article getArticleById(String articleId) {
        if (articleRepo.existsById(articleId)) {
            System.out.println(articleRepo.findById(articleId).toString());
            System.out.println(articleRepo.findById(articleId).get().toString());
            return articleRepo.findById(articleId).get();
        }
        return new Article();
    }

    @Override
    public boolean saveArticle(Article article) {
        if (article != null&&articleRepo.existsById(article.getIdArticle())) {
        articleRepo.save(article);
            return true;
        }
        return false;
    }

}
