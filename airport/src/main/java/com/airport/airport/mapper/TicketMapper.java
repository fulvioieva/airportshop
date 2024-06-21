package com.airport.airport.mapper;

import com.airport.airport.dto.TicketDTO;
import com.airport.airport.model.Ticket;

public class TicketMapper {

	public static TicketDTO convertToTicketDTO(Ticket ticket) {
		
		TicketDTO dto = new TicketDTO();
		
		dto.setIdTicket(ticket.getIdTicket());
		dto.setFlight(FlightMapper.convertToFlightDTO(ticket.getFlight()));
		dto.setTotalPrice(ticket.getTotalPrice());
		dto.setTicketsQty(ticket.getTicketsQty());
		dto.setTypePayment(ticket.getTypePayment());
		
		return dto;
		
	}
	
}
