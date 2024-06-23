package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Cart;

public interface ICartService {
	boolean   addCart(Cart Cart);
	boolean   updateCart (Cart Cart);
	List<Cart> getAllCart();
	Optional<Cart> getCart(int idCart);
	boolean deleteCart(int idCart);
}
