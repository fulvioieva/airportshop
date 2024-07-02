package com.e_commerceWebApp.repository;

import com.e_commerceWebApp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cartrepository extends JpaRepository<Cart, String> {

}
