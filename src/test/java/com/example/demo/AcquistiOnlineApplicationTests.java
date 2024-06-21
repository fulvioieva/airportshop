package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.OrderHead;

import come.example.demo.enums.StateOrder;
import come.example.demo.enums.TypePayment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcquistiOnlineApplicationTests {

	@Test
	void order_addOrder() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/addOrder";
		OrderHead orderHeadBody = new OrderHead(7, "CL001", "Via Po 20 10123 Torino", TypePayment.DEBIT_CARD,
				"2023-12-25", "100", StateOrder.PROGRESS);

		HttpEntity<OrderHead> requestEntity = new HttpEntity<>(orderHeadBody, headers);
		try {
			ResponseEntity<OrderHead> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					OrderHead.class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.CONFLICT, HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));
		}
	}

	@Test
	void order_getOrder() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/getOrder/33";
		HttpEntity<OrderHead> requestEntity = new HttpEntity<>(headers);
		try {
			ResponseEntity<OrderHead> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					OrderHead.class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));
		}

	}

	@Test
	void order_getAllOrders() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/getAllOrders";
		HttpEntity<OrderHead> requestEntity = new HttpEntity<OrderHead>(headers);
//		OrderHead[] OrderL = responseEntity.getBody();
		try {
			ResponseEntity<OrderHead[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					OrderHead[].class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));

		}
	}

	@Test
	void order_updateOrder() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/updateOrder";
		OrderHead orderHeadBody = new OrderHead(99, "CL001", "Via Po 20 10123 Torino", TypePayment.DEBIT_CARD,
				"2023-12-25", "100", StateOrder.PROGRESS);

		HttpEntity<OrderHead> requestEntity = new HttpEntity<>(orderHeadBody, headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
					String.class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));
		}

	}

	@Test
	void order_addArticle() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/addArticle/2/AR001/1";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.METHOD_NOT_ALLOWED,
					HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));

		}
	}

	@Test
	void order_existsArticle() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/existsArticle/2/AR001";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));

		}
	}
	
	
	@Test
	void order_deleteArticle() {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/order/deleteArticle/2/AR001";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertEquals(HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.valueOf(Integer.parseInt(e.getMessage().substring(0, 3))));

		}
	}
	
	

}
