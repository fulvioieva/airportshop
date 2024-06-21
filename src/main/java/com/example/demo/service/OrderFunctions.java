package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.OrderHead;
import com.example.demo.entity.OrderDetail;

public interface OrderFunctions {

	OrderHead addOrder(OrderHead order);

	Optional<OrderHead> getOrder(int idOrder);

	List<OrderHead> getAllOrders();

	boolean updateOrder(OrderHead order);

	boolean addArticle(int idOrder, String idArticle, int qtaOrdered);

	boolean existsArticle(int idOrder, String idArticle);

	boolean deleteArticle(int idOrder, String idArticle);

	void deleteAllArticles(int idOrder);

	boolean confirmOrder(int idOrder);

	Optional<OrderDetail> getArticle(int idOrder, String idArticle);

	List<OrderDetail> getAllArticles(int idOrder);

	List<Integer> getOrdersId(String idClient);

	List<Integer> getOrdersIdByDates(String idClient, String dateStart, String dateEnd);

	List<Integer> getOrdersIdByArticle(String idArticle);

	public boolean updateOrderState(int idOrder, String state);

	public boolean closeOrder(int idOrder);
}
