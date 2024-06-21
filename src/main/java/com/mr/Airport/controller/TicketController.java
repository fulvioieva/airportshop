package com.mr.Airport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mr.Airport.apiresponse.ApiResponse;
import com.mr.Airport.entity.Ticket;
import com.mr.Airport.interfaces.TicketFunctions;

@RestController
@RequestMapping(path= "Airport")
public class TicketController {
	
	@Autowired
	private TicketFunctions ticketService;
	
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
