package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Article;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartKey;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CartRepository;

@Service
public class ServiceCart implements CartFunctions {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	@Transactional
	synchronized public boolean addArticle(Article article, int idOrder, int qtaOrdered) {
		if (!articleRepository.findById(article.getIdArticle()).isEmpty()) {
			Article ar = articleRepository.findById(article.getIdArticle()).get();
			if (ar.getQtaAvailable() >= qtaOrdered && 0 < qtaOrdered) {
				int sumQuantita = 0;
				Optional<Cart> cart = cartRepository.findById(new CartKey(idOrder, article.getIdArticle()));
				if (!cart.isEmpty()) {
					sumQuantita = cart.get().getQtaOrdered() + qtaOrdered;
				}
				cartRepository.save(new Cart(idOrder, article.getIdArticle(), sumQuantita,
						articleRepository.findById(article.getIdArticle()).get().getPrice(),
						articleRepository.findById(article.getIdArticle()).get().getPrice() * sumQuantita));
				ar.setQtaAvailable(ar.getQtaAvailable() - qtaOrdered);
				articleRepository.save(ar);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean existsArticle(int idOrder, String idArticle) {
		return cartRepository.existsById(new CartKey(idOrder, idArticle));
	}

	@Override
	public boolean deleteArticle(int idOrder, String idArticle) {
		CartKey k = new CartKey(idOrder, idArticle);
		if (cartRepository.existsById(k)) {
			cartRepository.delete(cartRepository.findById(k).get());
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAllArticles(int idOrder) {
		return cartRepository.deleteByIdOrder(idOrder) == 1 ? true : false;
	}

	@Override
	public Optional<Cart> getArticle(int idOrder, String idArticle) {
		return cartRepository.findById(new CartKey(idOrder, idArticle));
	}

	@Override
	public List<Cart> getAllArticles(int idOrder) {
		return cartRepository.findByIdOrder(idOrder);
	}

	@Override
	public int computePriceTotal(int idOrder) {
		List<Cart> cart = this.getAllArticles(idOrder);
		return !cart.isEmpty() ? cart.stream().mapToInt(Cart::getUnitPrice).sum() : -1;
	}

}
