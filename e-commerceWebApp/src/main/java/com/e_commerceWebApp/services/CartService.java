package com.e_commerceWebApp.services;

import com.e_commerceWebApp.entity.Cart;
import com.e_commerceWebApp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{
    @Autowired
    CartRepository cartRepo;

    @Override
    public List<Cart> getAllCarts() {

        return cartRepo.findAll();
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepo.findById(id).orElse(null);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public boolean deleteCart(Cart cart) {
        try {
            int id= cart.getId();
            if(cartRepo.existsById(id)){
                cartRepo.delete(cart);
                return true;
            }
        } catch (Exception e) {
            // Stampa l'eccezione per il debug
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int updateCart(Cart cart) {
        if (cartRepo.existsById(cart.getId())) {
            cartRepo.save(cart);
            return 1;
        }
        return 0;
    }

    @Override
    public boolean existsById(Integer cartId) {
        return cartRepo.existsById(cartId);
    }
}
