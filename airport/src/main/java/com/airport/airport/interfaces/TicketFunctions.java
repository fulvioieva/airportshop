package com.airport.airport.interfaces;

import java.util.List;
import java.util.Optional;

import com.airport.airport.enums.StatusOrder;
import com.airport.airport.enums.TypePayment;
import com.airport.airport.model.Ticket;

public interface TicketFunctions {

	StatusOrder addTicket(String idUser, String idFlight, int ticketsQty, TypePayment typePayment);
	
	boolean existTicket(String idUser, String idFlight);
	
	Optional<Ticket> getTicket(String idUser, String idFlight);
	
	List<Ticket> getTickets(String idUser);
	
}