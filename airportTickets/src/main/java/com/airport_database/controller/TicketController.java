package com.airport_database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport_database.entity.Ticket;
import com.airport_database.enums.TypePayment;
import com.airport_database.services.TicketServices;
@CrossOrigin(origins = {"http://localhost:5173/"})

@RestController
@RequestMapping(path = "/AirportServices/tickets")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;
 
    //rotta per tutti i biglietti
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketServices.getAllTickets();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
  
 // rotta per un biglietto specifico tramite id
    @GetMapping(value = "/{idTicket}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ticket> getTicketByIdAndUser(@PathVariable int idTicket, @RequestParam String idUser) {
        if (!ticketServices.existsById(idTicket)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Ticket ticket = ticketServices.getTicketById(idTicket);
        if (!ticket.getUserId().equals(idUser)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

	//rotta per tutti i biglietti legati a un volo specifico
    @GetMapping(value = "/flight/{idFlight}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ticket>> getTicketsByFlightId(@PathVariable String idFlight) {
        List<Ticket> tickets = ticketServices.getTicketsByFlightId(idFlight);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    //rotta per i biglietti legati ad un utente
    @GetMapping(value = "/user/{idUser}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable String idUser) {
        List<Ticket> tickets = ticketServices.getTicketsByUserId(idUser);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/existing/{idFlight}/{idUser}")
    public int getExistingTicket(@PathVariable String idFlight, @PathVariable String idUser) {
        Optional<Ticket> existingTicket = Optional.ofNullable(ticketServices.getExistingTicketId(idFlight, idUser));
        return existingTicket.get().getIdTicket();
    }
    
    //rotta per aggiungere un nuovo biglietto al DB
    @PostMapping(value="/newTicket",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ticket> addTicket(@RequestParam("idFlight") String idFlight,
    										@RequestParam("idUser") String idUser, 
    										@RequestParam("quantity") int qty,
    										@RequestParam("payment")TypePayment pay) throws Throwable{
    	
	Ticket newTicket = ticketServices.addTicket(idFlight, idUser, qty, pay);
	if (newTicket != null) {
		return new ResponseEntity<>(newTicket, HttpStatus.OK);
	}
    	return new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE);
       	
    }
    
    //rotta per modificare un biglietto
    @PutMapping("/updateTicket")
    public ResponseEntity<String> updateTicketQuantity(@RequestParam("idTicket") int idTicket,
                                                       @RequestParam("newQty") int newQty) {
        try {
            boolean updateResult = ticketServices.updateTicketQuantity(idTicket, newQty);
            if (updateResult) {
                return new ResponseEntity<>("Quantità biglietto aggiornata con successo.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Aggiornamento non riuscito.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}