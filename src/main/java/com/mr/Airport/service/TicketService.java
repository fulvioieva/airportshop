package com.mr.Airport.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.Airport.Utility.Utility;
import com.mr.Airport.entity.Flight;
import com.mr.Airport.entity.Ticket;
import com.mr.Airport.entity.User;
import com.mr.Airport.enums.PaymentType;
import com.mr.Airport.interfaces.FlightFunctions;
import com.mr.Airport.interfaces.TicketFunctions;
import com.mr.Airport.repository.FlightRepository;
import com.mr.Airport.repository.TicketRepository;
import com.mr.Airport.repository.UserRepository;

@Service
public class TicketService implements TicketFunctions {

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	FlightFunctions flightService;

	@Override
	public boolean buyTicket(long userId, long flightId, int qta, PaymentType paymentType) throws Exception {
		// Controllo se userId e flightId esistono, successivamente controllo disponibilità posti
		if (!userRepository.existsById(userId)) { return false; }
		if (!flightRepository.existsById(flightId)) { return false; }
		if (!flightService.checkPlacesAvailable(flightId, qta)) { return false; }
		
		// Rimuovo posti per aggiungerli nel biglietto
		flightService.removeFlightPlaces(flightId, qta);
		
		User user = userRepository.findById(userId).get();
		Flight flight = flightRepository.findById(flightId).get();
		
		// Creo nuovo biglietto
		this.createTicket(flight, user, qta, paymentType);
		return true;
	}
	
	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public List<Ticket> getAllUserTicketsById(long userId) throws Exception {
		if (!userRepository.existsById(userId)) { throw new Exception(); }
		User user = userRepository.findById(userId).get();
		return user.getTickets();
	}

	// Aumenta la qta dei biglietti ordinati
	@Override
	public boolean addTicketQta(long ticketId, int qta, long userId) throws Exception {
		// Controllo se esiste l'userId e il biglietto, poi prendo l'id del volo
		if (!userRepository.existsById(userId)) { return false; }
		if (!ticketRepository.existsById(ticketId)) { return false; }
		long flightId = ticketRepository.findById(ticketId).get().getFlight().getId();
		// Controllo se ci sono posti disponibili nel volo
		if (!flightService.checkPlacesAvailable(flightId, qta)) { return false; }
		// Decremento i posti disponibili nel volo
		flightService.removeFlightPlaces(flightId, qta);
		// Incremento la tickets_qta
		Ticket ticket = ticketRepository.findById(ticketId).get();
		int ticketOldQta = ticket.getTicketsQta();
		int ticketNewQta = ticketOldQta + qta;
		ticketRepository.updateTicketsQta(ticketId, ticketNewQta);
		// Aggiorno il total_price
		ticketRepository.updateTicketsTotalPrice(ticketId, this.calculateTotalPrice(flightId, ticketNewQta));
		return true;
	}

	// Decrementa la qta dei biglietti ordinati
	@Override
	public boolean removeTicketQta(long ticketId, int qta, long userId) throws Exception {
		// Controllo se esiste l'userId e il biglietto, poi prendo il biglietto
		if (!userRepository.existsById(userId)) { return false; }
		if (!ticketRepository.existsById(ticketId)) { return false; }
		Ticket ticket = ticketRepository.findById(ticketId).get();
		// Controllo se i posti che si vogliono rimuovere sono inferiori o uguali a quelli già acquistati
		int oldQta = ticket.getTicketsQta();
		if (qta > oldQta) { return false; }
		// Rimuovo i posti dal biglietto (se vado a 0 elimino la prenotazione)
		int newQta = oldQta - qta;
		if (newQta == 0) {
			// Elimino prenotazione e return true
			ticketRepository.deleteById(ticketId);
			return true;
		}
		ticket.setTicketsQta(newQta);
		// Aggiorno il total_price
		ticket.setTotalPrice(this.calculateTotalPrice(ticket.getFlight().getId(), newQta));
		// Salvo il ticket
		ticketRepository.save(ticket);
		
		// Incremento i posti disponibili nel volo
		flightService.addFlightPlaces(ticket.getFlight().getId(), qta);
		return true;
	}
	
	private void createTicket(Flight flight, User user, int qta, PaymentType paymentType) throws Exception {
		Ticket newTicket = new Ticket();
		newTicket.setFlight(flight);
		newTicket.setUser(user);
		newTicket.setTicketsQta(qta);
		newTicket.setTotalPrice(this.calculateTotalPrice(flight.getId(), qta));
		newTicket.setPaymentType(paymentType);
		newTicket.setPurchaseDate(Utility.getCurrentTimestamp());
		ticketRepository.save(newTicket);
	}
	
	private BigDecimal calculateTotalPrice(long flightId, int qta) throws Exception {
		
		// Controllo l'esistenza del volo
		if (!flightRepository.existsById(flightId)) { throw new Exception(); }
		
		// Prendo il costo del volo e trasformo l'int della qta in BigDecimal per effettuare le operazioni
		BigDecimal flightPrice = flightRepository.findById(flightId).get().getPrice();
		BigDecimal quantity = BigDecimal.valueOf(qta);
		
		// return priceTotal (per moltiplicare devo utilizzare il metodo di BigDecimal)
		return flightPrice.multiply(quantity);
	}

}
