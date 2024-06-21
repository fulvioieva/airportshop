package com.airport.airport.dto;

import com.airport.airport.enums.TypePayment;

public class TicketDTO {

	private int idTicket;
	
    private FlightDTO flight;
	
	private long totalPrice;

	private int ticketsQty;

	private TypePayment typePayment;

	public TicketDTO(int idTicket, FlightDTO flight, long totalPrice, int ticketsQty, TypePayment typePayment) {
		super();
		this.idTicket = idTicket;
		this.flight = flight;
		this.totalPrice = totalPrice;
		this.ticketsQty = ticketsQty;
		this.typePayment = typePayment;
	}

	public TicketDTO() {
		super();
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public FlightDTO getFlight() {
		return flight;
	}

	public void setFlight(FlightDTO flight) {
		this.flight = flight;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTicketsQty() {
		return ticketsQty;
	}

	public void setTicketsQty(int ticketsQty) {
		this.ticketsQty = ticketsQty;
	}

	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}
	
	
	
}
