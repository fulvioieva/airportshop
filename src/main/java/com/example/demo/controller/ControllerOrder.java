package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderHead;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderFunctions;

@RestController
@RequestMapping(path = "order")
public class ControllerOrder {

	@Autowired
	private OrderFunctions orderFunctions;
	@Autowired
	private OrderRepository orderRepository;

	@PostMapping(value = "addOrder")
	public ResponseEntity<OrderHead> addOrder(@RequestBody OrderHead order) {
		return orderRepository.findById(order.getIdOrder()).isPresent()
				? new ResponseEntity<>(orderFunctions.addOrder(order), HttpStatus.CONFLICT)
				: new ResponseEntity<>(orderFunctions.addOrder(order), HttpStatus.OK);
	}

	@GetMapping(value = "getOrder/{idOrder}")
	public ResponseEntity<OrderHead> getOrder(@PathVariable("idOrder") int idOrder) {
		return !orderFunctions.getOrder(idOrder).isEmpty()
				? new ResponseEntity<>(orderFunctions.getOrder(idOrder).get(), HttpStatus.OK)
				: new ResponseEntity<>(new OrderHead(), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getAllOrders")
	public ResponseEntity<List<OrderHead>> getAllOrders() {
		return !orderFunctions.getAllOrders().isEmpty()
				? new ResponseEntity<>(orderFunctions.getAllOrders(), HttpStatus.OK)
				: new ResponseEntity<>(orderFunctions.getAllOrders(), HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "updateOrder")
	public ResponseEntity<String> updateOrder(@RequestBody OrderHead order) {
		return orderFunctions.updateOrder(order) ? new ResponseEntity<>("Aggiornato!", HttpStatus.OK)
				: new ResponseEntity<>("Non trovato!", HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "addArticle/{idOrder}/{idArticle}/{qtaOrdered}")
	public ResponseEntity<String> addArticle(@PathVariable("idOrder") int idOrder,
			@PathVariable("idArticle") String idArticle, @PathVariable("qtaOrdered") int qtaOrdered) {
		return orderFunctions.addArticle(idOrder, idArticle, qtaOrdered)
				? new ResponseEntity<>("Insert!", HttpStatus.OK)
				: new ResponseEntity<>("false", HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping(value = "existsArticle/{idOrder}/{idArticle}")
	public ResponseEntity<String> existsArticle(@PathVariable("idOrder") int idOrder,
			@PathVariable("idArticle") String idArticle) {
		return orderFunctions.existsArticle(idOrder, idArticle) ? new ResponseEntity<>("esiste", HttpStatus.OK)
				: new ResponseEntity<>("non trovato", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "deleteArticle/{idOrder}/{idArticle}")
	public ResponseEntity<String> deleteArticle(@PathVariable("idOrder") int idOrder,
			@PathVariable("idArticle") String idArticle) {
		return orderFunctions.deleteArticle(idOrder, idArticle) ? new ResponseEntity<>("eliminato", HttpStatus.OK)
				: new ResponseEntity<>("non trovato", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "deleteAllArticles/{idOrder}")
	public ResponseEntity<String> deleteAllArticles(@PathVariable("idOrder") int idOrder) {
		orderFunctions.deleteAllArticles(idOrder);
		return !orderRepository.findById(idOrder).isEmpty() ? new ResponseEntity<>("Eliminato", HttpStatus.OK)
				: new ResponseEntity<>("non torvato", HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "confirmOrder/{idOrder}")
	public ResponseEntity<String> confirmOrder(@PathVariable("idOrder") int idOrder) {
		return orderFunctions.confirmOrder(idOrder) ? new ResponseEntity<>("Confermato!", HttpStatus.OK)
				: new ResponseEntity<>("Non confermato!", HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping(value = "getArticle/{idOrder}/{idArticle}")
	public ResponseEntity<OrderDetail> getArticle(@PathVariable("idOrder") int idOrder,
			@PathVariable("idArticle") String idArticle) {
		return !orderFunctions.getArticle(idOrder, idArticle).isEmpty()
				? new ResponseEntity<>(orderFunctions.getArticle(idOrder, idArticle).get(), HttpStatus.OK)
				: new ResponseEntity<>(new OrderDetail(), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getAllArticles/{idOrder}")
	public ResponseEntity<List<OrderDetail>> getAllArticles(@PathVariable("idOrder") int idOrder) {
		return !orderFunctions.getAllArticles(idOrder).isEmpty()
				? new ResponseEntity<>(orderFunctions.getAllArticles(idOrder), HttpStatus.OK)
				: new ResponseEntity<>(orderFunctions.getAllArticles(idOrder), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getOrdersId/{idClient}")
	public ResponseEntity<List<Integer>> getOrdersId(@PathVariable("idClient") String idClient) {
		return !orderFunctions.getOrdersId(idClient).isEmpty()
				? new ResponseEntity<>(orderFunctions.getOrdersId(idClient), HttpStatus.OK)
				: new ResponseEntity<>(orderFunctions.getOrdersId(idClient), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getOrdersIdByDates/{idClient}/{dateStart}/{dateEnd}")
	public ResponseEntity<List<Integer>> getOrdersIdByDates(@PathVariable("idClient") String idClient,
			@PathVariable("dateStart") String dateStart, @PathVariable("dateEnd") String dateEnd) {
		return !orderFunctions.getOrdersIdByDates(idClient, dateStart, dateEnd).isEmpty()
				? new ResponseEntity<>(orderFunctions.getOrdersIdByDates(idClient, dateStart, dateEnd), HttpStatus.OK)
				: new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getOrdersIdByArticle/{idArticle}")
	public ResponseEntity<List<Integer>> getOrdersIdByArticle(@PathVariable("idArticle") String idArticle) {
		return !orderFunctions.getOrdersIdByArticle(idArticle).isEmpty()
				? new ResponseEntity<>(orderFunctions.getOrdersIdByArticle(idArticle), HttpStatus.OK)
				: new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "updateOrderState/{idOrder}/{state}")
	public ResponseEntity<String> updateOrderState(@PathVariable("idOrder") int idOrder,
			@PathVariable("state") String state) {
		return orderFunctions.updateOrderState(idOrder, state) ? new ResponseEntity<>("ok", HttpStatus.OK)
				: new ResponseEntity<>("Tipo di Enum non trovato oppure order non trovato!", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = "closeOrder/{idOrder}")
	public ResponseEntity<String> closeOrder(@PathVariable("idOrder") int idOrder) {
		return orderFunctions.closeOrder(idOrder) ? new ResponseEntity<>("Order chiuso", HttpStatus.OK)
				: new ResponseEntity<>("imposibile chiudere ordine", HttpStatus.NOT_ACCEPTABLE);
	}

}
