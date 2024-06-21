package com.repository;


//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.entity.Cart;


public interface CartRepository extends CrudRepository<Cart, Integer> {
	
}
