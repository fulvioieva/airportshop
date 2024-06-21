package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Article;
import com.example.demo.entity.Cart;
import com.example.demo.service.CartFunctions;

@RestController
@RequestMapping(path = "cart")
public class ControllerCart {
	
	@Autowired
	private CartFunctions cartFunctions;
	
	@PostMapping(value = "addArticle/{idOrder}/{qtaOrdered}")
	public ResponseEntity<String> addArticle(@RequestBody Article article,@PathVariable("idOrder") int idOrder,@PathVariable("qtaOrdered") int qtaOrdered) {
		return cartFunctions.addArticle(article,idOrder,qtaOrdered) ? new ResponseEntity<String>("Insert!", HttpStatus.OK)
				: new ResponseEntity<String>("false", HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@GetMapping(value = "existsArticle/{idOrder}/{idArticle}")
	public ResponseEntity<String> existsArticle(@PathVariable("idOrder") int idOrder , @PathVariable("idArticle") String idArticle) {
		return cartFunctions.existsArticle(idOrder,idArticle)
				? new ResponseEntity<>("esiste", HttpStatus.OK)
				: new ResponseEntity<>("non trovato", HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping(value = "deleteArticle/{idOrder}/{idArticle}")
	public ResponseEntity<String> deleteArticle(@PathVariable("idOrder") int idOrder , @PathVariable("idArticle") String idArticle) {
		return cartFunctions.deleteArticle(idOrder,idArticle)
				? new ResponseEntity<>("eliminato", HttpStatus.OK)
				: new ResponseEntity<>("non trovato", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "deleteAllArticles/{idOrder}")
	public ResponseEntity<String> deleteAllArticles(@PathVariable("idOrder") int idOrder ) {
		return cartFunctions.deleteAllArticles(idOrder)
				? new ResponseEntity<>("Eliminato", HttpStatus.OK)
				: new ResponseEntity<>("non torvato", HttpStatus.NOT_FOUND);
	}

	
	
	@GetMapping(value = "getArticle/{idOrder}/{idArticle}")
	public ResponseEntity<Cart> getArticle(@PathVariable("idOrder") int idOrder,@PathVariable("idArticle") String idArticle ) {
		return !cartFunctions.getArticle(idOrder,idArticle).isEmpty()
				? new ResponseEntity<>(cartFunctions.getArticle(idOrder,idArticle).get(), HttpStatus.OK)
				: new ResponseEntity<>(cartFunctions.getArticle(idOrder,idArticle).get(), HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping(value = "getAllArticles/{idOrder}")
	public ResponseEntity<List<Cart>> getAllArticles(@PathVariable("idOrder") int idOrder ) {
		return !cartFunctions.getAllArticles(idOrder).isEmpty()
				? new ResponseEntity<>(cartFunctions.getAllArticles(idOrder), HttpStatus.OK)
				: new ResponseEntity<>(cartFunctions.getAllArticles(idOrder), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "computePriceTotal/{idOrder}")
	public ResponseEntity<String> computePriceTotal(@PathVariable("idOrder") int idOrder ) {
		return cartFunctions.computePriceTotal(idOrder)>-1
				? new ResponseEntity<>("Total Price : "+cartFunctions.computePriceTotal(idOrder), HttpStatus.OK)
				: new ResponseEntity<>("non trovato!", HttpStatus.NOT_FOUND);
	}
	

}
