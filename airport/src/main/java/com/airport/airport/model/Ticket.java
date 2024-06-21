package com.airport.airport.model;

import java.io.Serializable;
import java.util.Objects;

import com.airport.airport.enums.TypePayment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id_ticket")
	private int idTicket;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

	@Column(name= "total_price")
	private long totalPrice;

	@Column(name= "tickets_qty")
	private int ticketsQty;

	@Column(name= "payment_type")
	@Enumerated(EnumType.STRING)
	private TypePayment typePayment;

	public Ticket(int idTicket, Flight flight, User user, long totalPrice, int ticketsQty, TypePayment typePayment) {
		super();
		this.idTicket = idTicket;
		this.flight = flight;
		this.user = user;
		this.totalPrice = totalPrice;
		this.ticketsQty = ticketsQty;
		this.typePayment = typePayment;
	}

	public Ticket() {
		super();
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight, idTicket, ticketsQty, totalPrice, typePayment, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(flight, other.flight) && idTicket == other.idTicket && ticketsQty == other.ticketsQty
				&& totalPrice == other.totalPrice && typePayment == other.typePayment
				&& Objects.equals(user, other.user);
	}

	
	
}
