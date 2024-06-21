package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderDetailKey;

import jakarta.transaction.Transactional;


public interface OrderDetailRepository extends CrudRepository<OrderDetail, OrderDetailKey> {

	List<OrderDetail> findByIdOrder(int idOrder);

	@Transactional
	long deleteByIdOrder(int idOrder);
	
	List<OrderDetail> findByIdArticle(String idArticle);

	
}
