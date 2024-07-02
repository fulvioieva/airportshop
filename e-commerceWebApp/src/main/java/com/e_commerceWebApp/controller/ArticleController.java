package com.e_commerceWebApp.controller;

import com.e_commerceWebApp.entity.Article;
import com.e_commerceWebApp.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "e_commerce/articles")
public class ArticleController {

    @Autowired
    private ArticleService ArticleServices;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articleList = ArticleServices.getAllArticles();
        if (articleList.isEmpty()){
            return new ResponseEntity<List<Article>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Article>>(articleList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Article> getArticleById(@PathVariable("id") String id) {

        Article obj = ArticleServices.getArticleById(id);
        if (ArticleServices.existsById(id)) {
            return new ResponseEntity<Article>(obj, HttpStatus.OK);
        }
        return new ResponseEntity<Article>(obj, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Article> updateArticle(@PathVariable String id, @RequestBody Article entity) {

        if (!ArticleServices.existsById(id)) {
            return new ResponseEntity<Article>(entity, HttpStatus.NOT_FOUND);
        }
        ArticleServices.updateArticle(entity);
        return new ResponseEntity<Article>(entity, HttpStatus.OK);
    }
    }
