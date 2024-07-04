package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Cart;

import java.util.List;

public interface ICartService {

    List<Cart> getAllCarts();

    public Cart getCartById(Integer id);

    public Cart saveCart(Cart cart);

    public int deleteCart(Integer id);

    int updateCart(Cart cart);

    boolean existsById(Integer cartId);
}
