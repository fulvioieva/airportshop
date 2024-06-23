package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartService implements ICartService{

	@Autowired
	private CartRepository cartRepository;

	@Override
	public boolean addCart(Cart Cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCart(Cart Cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cart> getAllCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cart> getCart(int idCart) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean deleteCart(int idCart) {
		// TODO Auto-generated method stub
		return false;
	}
}
