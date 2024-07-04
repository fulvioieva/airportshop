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

    // Inietta il servizio degli articoli.
    @Autowired
    private ArticleService ArticleServices;

    // Questo metodo restituisce tutti gli articoli.
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Article>> getAllArticles() {
        // Ottieni tutti gli articoli dal servizio.
        List<Article> articleList = ArticleServices.getAllArticles();
        // Se la lista è vuota, restituisci uno stato HTTP 404 (Not Found).
        if (articleList.isEmpty()){
            return new ResponseEntity<List<Article>>(HttpStatus.NOT_FOUND);
        }
        // Altrimenti, restituisci la lista degli articoli con uno stato HTTP 200 (OK).
        return new ResponseEntity<List<Article>>(articleList, HttpStatus.OK);
    }

    // Questo metodo restituisce un articolo specifico in base al suo ID.
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Article> getArticleById(@PathVariable("id") String id) {
        // Ottieni l'articolo dal servizio.
        Article obj = ArticleServices.getArticleById(id);
        // Se l'articolo esiste, restituisci l'articolo con uno stato HTTP 200 (OK).
        if (ArticleServices.existsById(id)) {
            return new ResponseEntity<Article>(obj, HttpStatus.OK);
        }
        // Altrimenti, restituisci uno stato HTTP 404 (Not Found).
        return new ResponseEntity<Article>(obj, HttpStatus.NOT_FOUND);
    }

    // Questo metodo aggiorna un articolo esistente.
    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Article> updateArticle(@PathVariable String id, @RequestBody Article entity) {
        // Se l'articolo non esiste, restituisci uno stato HTTP 404 (Not Found).
        if (!ArticleServices.existsById(id)) {
            return new ResponseEntity<Article>(entity, HttpStatus.NOT_FOUND);
        }
        // Altrimenti, aggiorna l'articolo e restituisci l'articolo aggiornato con uno stato HTTP 200 (OK).
        ArticleServices.updateArticle(entity);
        return new ResponseEntity<Article>(entity, HttpStatus.OK);
    }
    }
