package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.OrderHead;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;

import come.example.demo.enums.StateOrder;

import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderDetailKey;

@Service
public class ServiceOrder implements OrderFunctions {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public OrderHead addOrder(OrderHead order) {
		return !orderRepository.existsById(order.getIdOrder()) ? orderRepository.save(order)
				: orderRepository.findById(order.getIdOrder()).get();
	}

	@Override
	public Optional<OrderHead> getOrder(int idOrder) {
		return orderRepository.findById(idOrder);
	}

	@Override
	public List<OrderHead> getAllOrders() {
		List<OrderHead> list = new ArrayList<>();
		orderRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public boolean updateOrder(OrderHead order) {
		return orderRepository.existsById(order.getIdOrder()) ? orderRepository.save(order) != null : false;

	}

	@Override
	public boolean addArticle(int idOrder, String idArticle, int qtaOrdered) {
		OrderDetailKey k = new OrderDetailKey(idOrder, idArticle);
		if (articleRepository.existsById(idArticle) && orderDetailRepository.existsById(k)) {
			OrderDetail o = orderDetailRepository.findById(k).get();
			if (o.getIdArticle().equals(idArticle)) {
				o.setQtaOrdered(qtaOrdered + o.getQtaOrdered());
				o.setTotalPrice(o.getQtaOrdered() * o.getUnitPrice());
			} else {
				o.setIdArticle(idArticle);
				o.setQtaOrdered(qtaOrdered);
				o.setUnitPrice(articleRepository.findById(idArticle).get().getPrice());
				o.setTotalPrice(o.getUnitPrice() * qtaOrdered);
			}
			orderDetailRepository.save(o);

			return true;
		}
		return false;

	}

	@Override
	public boolean existsArticle(int idOrder, String idArticle) {
		return orderDetailRepository.existsById(new OrderDetailKey(idOrder, idArticle));
	}

	@Override
	public boolean deleteArticle(int idOrder, String idArticle) {
		OrderDetailKey k = new OrderDetailKey(idOrder, idArticle);
		if (orderDetailRepository.existsById(k)) {
			orderDetailRepository.delete(orderDetailRepository.findById(k).get());
			return true;
		}
		return false;
	}

	@Override
	public void deleteAllArticles(int idOrder) {
		orderDetailRepository.deleteByIdOrder(idOrder);
	}

	@Override
	@Transactional
	public boolean confirmOrder(int idOrder) {
		Optional<OrderHead> ordH = orderRepository.findById(idOrder);
		if(ordH.isEmpty()) {
			return false;
		}
		if (!orderRepository.findById(idOrder).get().getStateOrder().equals(StateOrder.PROGRESS)) {
			return false;
		}
		List<Cart> cart = cartRepository.findByIdOrder(idOrder);
		for (Cart c : cart) {
			OrderDetail ord = new OrderDetail();
			ord.setIdArticle(c.getIdArticle());
			ord.setIdOrder(c.getIdOrder());
			ord.setQtaOrdered(c.getQtaOrdered());
			ord.setTotalPrice(c.getTotalPrice());
			ord.setUnitPrice(c.getUnitPrice());
			orderDetailRepository.save(ord);
			cartRepository.delete(c);
		}
		ordH.get().setStateOrder(StateOrder.CONFIRMED);
		orderRepository.save(ordH.get());
		return true;
	}

	@Override
	public Optional<OrderDetail> getArticle(int idOrder, String idArticle) {
		return orderDetailRepository.findById(new OrderDetailKey(idOrder, idArticle));
	}

	@Override
	public List<OrderDetail> getAllArticles(int idOrder) {
		return orderDetailRepository.findByIdOrder(idOrder);
	}

	@Override
	public List<Integer> getOrdersId(String idClient) {
		return orderRepository.findByIdClient(idClient).stream().map(OrderHead::getIdOrder)
				.collect(Collectors.toList());
	}

	@Override
	public List<Integer> getOrdersIdByDates(String idClient, String dateStart, String dateEnd) {
		return orderRepository.findByIdClient(idClient).stream()
				.filter(order -> LocalDate.parse(order.getDtOrder()).isAfter(LocalDate.parse(dateStart))
						&& LocalDate.parse(order.getDtOrder()).isBefore(LocalDate.parse(dateEnd)))
				.map(OrderHead::getIdOrder).collect(Collectors.toList());
	}

	@Override
	public List<Integer> getOrdersIdByArticle(String idArticle) {
		return orderDetailRepository.findByIdArticle(idArticle).stream().map(OrderDetail::getIdOrder)
				.collect(Collectors.toList());
	}

	@Override
	public boolean updateOrderState(int idOrder, String state) {
		Optional<OrderHead> ordHead = orderRepository.findById(idOrder);
		if (ordHead.isEmpty()) {
			return false;
		}
		try {
			ordHead.get().setStateOrder(StateOrder.valueOf(state.toUpperCase()));
			orderRepository.save(ordHead.get());
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean closeOrder(int idOrder) {
		Optional<OrderHead> ordHead = orderRepository.findById(idOrder);
		if (ordHead.isEmpty()) {
			return false;
		}
		if(ordHead.get().getStateOrder().equals(StateOrder.CLOSED)) {
			return false;
		}
		ordHead.get().setStateOrder(StateOrder.CLOSED);
		orderRepository.save(ordHead.get());
		return true;
	}

}
