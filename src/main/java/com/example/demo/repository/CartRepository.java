package com.example.demo.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartKey;

import jakarta.transaction.Transactional;

public interface CartRepository extends CrudRepository<Cart, CartKey> {
	
	List<Cart> findByIdOrder(int idOrder);

	@Transactional
	long deleteByIdOrder(int idOrder);
	
//	  @Query("select sum(prezzoUnico) TestDataEntity (NAME, VALUE) VALUES (:name, :value)")
//	    void insertTestData(String name, String value);
	
	
}
