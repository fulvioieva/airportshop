package com.mr.Airport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mr.Airport.apiresponse.ApiResponse;
import com.mr.Airport.entity.Ticket;
import com.mr.Airport.enums.PaymentType;
import com.mr.Airport.interfaces.TicketFunctions;
@CrossOrigin(origins = {"http://localhost:5173/"})

@RestController
@RequestMapping(path= "Airport")
public class TicketController {
	
	@Autowired
	private TicketFunctions ticketService;
	
	// PURCHASE Ticket
	@PostMapping(value= "ticket/buy", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> buyTicket(@RequestParam("userId") long userId,
														  @RequestParam("flightId") long flightId,
													  	  @RequestParam("qta") int qta,
														  @RequestParam("paymentType") PaymentType paymentType) {
		
		Boolean purchaseStatus = false;
		try {
			purchaseStatus = ticketService.buyTicket(userId, flightId, qta, paymentType);
		} catch (Exception e) {
			// TODO -> Da controllare perchè non viene richiamato quando inserisco un volo inesistente
			return new ResponseEntity<>(new ApiResponse<>(false, "User/Flight does not exist or user not logged", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		if (!purchaseStatus) {
			return new ResponseEntity<>(new ApiResponse<>(false, "Ticket no purchased", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(true, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	// GET All Tickets
	@GetMapping(value= "tickets", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets() {
		
		List<Ticket> tickets = ticketService.getAllTickets();
				
		if (tickets.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(null, "No tickets founded", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ApiResponse<>(tickets, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	// PATCH Add user ticket qta
	@PatchMapping(value= "users/{userId}/tickets/{ticketId}/add/{qta}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> addTicketQta(@PathVariable long userId,
															 @PathVariable long ticketId,
															 @PathVariable int qta) {
		
		boolean updateStatus = false;
		try {
			updateStatus = ticketService.addTicketQta(ticketId, qta, userId);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(updateStatus, "User/Flight does not exist or user not logged", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		if (!updateStatus) {
			return new ResponseEntity<>(new ApiResponse<>(updateStatus, "Places Not Available, Ticket quantity unchanged", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(updateStatus, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	// PATCH Remove user ticket qta
	@PatchMapping(value= "users/{userId}/tickets/{ticketId}/remove/{qta}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> removeTicketQta(@PathVariable long userId,
															    @PathVariable long ticketId,
															    @PathVariable int qta) {
		
		boolean updateStatus = false;
		try { // TODO -> Verifica quando viene lanciata l'exception e modifica il messaggio
			updateStatus = ticketService.removeTicketQta(ticketId, qta, userId);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(updateStatus, "User/Flight does not exist or user not logged", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		if (!updateStatus) {
			return new ResponseEntity<>(new ApiResponse<>(updateStatus, "Places Not Available, Ticket quantity unchanged", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(updateStatus, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}

}
