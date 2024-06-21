package com.airport.airport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.airport.dto.TicketDTO;
import com.airport.airport.enums.StatusOrder;
import com.airport.airport.enums.TypePayment;
import com.airport.airport.mapper.TicketMapper;
import com.airport.airport.model.Ticket;
import com.airport.airport.service.ServiceTicket;

@RestController
@RequestMapping("airport")
public class ControllerTicket {
	
	@Autowired
	private ServiceTicket serviceTicket;
	
//	TICKET SINGOLO
	
	@GetMapping(value="ticket/{id_user}/{id_flight}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getTicket(@PathVariable("id_user") String idUser, @PathVariable("id_flight") String idFlight){
		
		Optional<Ticket> ticket = serviceTicket.getTicket(idUser, idFlight);
		
		TicketDTO ticketDTO = TicketMapper.convertToTicketDTO(ticket.get());
		
		return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
		
	}
	
//	AGGIUNTA DI UN TICKET
	
	@PostMapping(value="ticket", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addTicket(@RequestParam String idUser, 
									   @RequestParam String idFlight, 
									   @RequestParam int ticketsQty, 
									   @RequestParam TypePayment typePayment){
		
		StatusOrder status = serviceTicket.addTicket(idUser, idFlight, ticketsQty, typePayment);
		
		if(status == StatusOrder.QTY_NOT_AVAILABLE) {
			
			return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
			
		}
		
		return new ResponseEntity<>(status, HttpStatus.OK);
		
	}
	
//	TUTTI I TICKET DI UN UTENTE
	
	@GetMapping(value="tickets/{id_user}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getTickets(@PathVariable("id_user") String idUser){
		
		List<Ticket> tickets = serviceTicket.getTickets(idUser);
		
		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		
		for (Ticket ticket : tickets) {
						
			TicketDTO ticketDTO = TicketMapper.convertToTicketDTO(ticket);
			
			ticketsDTO.add(ticketDTO);
			
		}

		
		return new ResponseEntity<>(ticketsDTO, HttpStatus.OK);
		
	}

}
