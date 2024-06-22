package com.mr.Airport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mr.Airport.apiresponse.ApiResponse;
import com.mr.Airport.entity.Ticket;
import com.mr.Airport.enums.PaymentType;
import com.mr.Airport.interfaces.TicketFunctions;

@RestController
@RequestMapping(path= "Airport")
public class TicketController {
	
	@Autowired
	private TicketFunctions ticketService;
	
	// PURCHASE TICKET
	@PostMapping(value= "ticket/buy/{userId}/{flightId}/{qta}/{paymentType}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> buyTicket(@PathVariable("userId") long userId,
														  @PathVariable("flightId") long flightId,
														  @PathVariable("qta") int qta,
														  @PathVariable("paymentType") PaymentType paymentType) {
		
		Boolean purchaseStatus = false;
		try {
			purchaseStatus = ticketService.buyTicket(userId, flightId, qta, paymentType);
		} catch (Exception e) {
			// TODO -> Da controllare perchè non viene richiamato quando inserisco un volo inesistente
			return new ResponseEntity<>(new ApiResponse<>(false, "Flight does not exist", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
				
		if (!purchaseStatus) {
			return new ResponseEntity<>(new ApiResponse<>(false, "Ticket no purchased", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(true, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	// EXIST User
	@GetMapping(value= "tickets", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets() {
		
		List<Ticket> tickets = ticketService.getAllTickets();
				
		if (tickets.isEmpty()) {
//			return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(new ApiResponse<>(null, "No tickets founded", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
//		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
		return new ResponseEntity<>(new ApiResponse<>(tickets, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	

	// TODO -> FAI GLI ALTRI METODI DEL CONTROLLER TICKET

}
