package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Article;
import com.example.demo.entity.Cart;

public interface CartFunctions {

	boolean addArticle(Article article, int idOrder, int qtaOrdered);

	boolean existsArticle(int idOrder, String idArticle);

	boolean deleteArticle(int idOrder, String idArticle);

	boolean deleteAllArticles(int idOrder);

	Optional<Cart> getArticle(int idOrder, String idArticle);

	List<Cart> getAllArticles(int idOrder);

	int computePriceTotal(int idOrder);
}
