package com.mr.Airport.interfaces;

import java.util.List;

import com.mr.Airport.entity.Ticket;
import com.mr.Airport.enums.PaymentType;

public interface TicketFunctions {
	/* 
	 * buyTicket -
	 * deleteTicket
	 * updateTicketsQuantity
	 * getAllTickets
	 * getAllUserTicketsById -
	 * getAllTickets - */
	
	boolean buyTicket(long userId, long flightId, int qta, PaymentType paymentType) throws Exception;
	List<Ticket> getAllTickets();
	List<Ticket> getAllUserTicketsById(long userId) throws Exception;
	boolean addTicketQta(long ticketId, int qta, long userId) throws Exception;
	boolean removeTicketQta(long ticketId, int qta, long userId) throws Exception;
}
