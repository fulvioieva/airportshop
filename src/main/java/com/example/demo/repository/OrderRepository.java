package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.OrderHead;

public interface OrderRepository extends CrudRepository<OrderHead, Integer> {
	    
	List<OrderHead> findByIdClient(String idClient);
	


}
