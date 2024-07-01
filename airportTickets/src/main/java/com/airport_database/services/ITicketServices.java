package com.airport_database.services;

import java.util.List;

import com.airport_database.entity.Ticket;
import com.airport_database.enums.TypePayment;

public interface ITicketServices {

	List<Ticket> getAllTickets();
	
	boolean existsById(int idTicket);
	
	Ticket getTicketById(int idTicket);
	
	List<Ticket> getTicketsByFlightId(String idFlight);
	
	List<Ticket> getTicketsByUserId(String idUser);
	
	Ticket addTicket(String idFlight,String idUser, int qty,TypePayment pay) throws Throwable;
	
	boolean updateTicketQuantity(int idTicket, int newQty) throws Exception;
		
	public Ticket getExistingTicketId(String idFlight, String idUser);
}
