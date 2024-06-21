package com.entity;

import java.sql.Date;
import java.util.Objects;

import com.enums.AvailabilityAtTheMoment;
import com.enums.TypePayment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
public class Cart {
	
	@Id
	@Column(name = "cart_id")
	private int cart_id;
	
	@Column(name = "client_id")
	private int client_id;
	
	@Column(name = "flight_id")
	private int flight_id;
	
	@Column(name = "payment_type")
	private TypePayment payment_type;
	
	@Column(name = "reservation_date")
	private Date reservation_date;
	
	@Column(name = "quantity_tickets")
	private int quantity_tickets;
	
	@Column(name = "availability_at_the_moment")
	private AvailabilityAtTheMoment availability_at_the_moment;

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public TypePayment getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(TypePayment payment_type) {
		this.payment_type = payment_type;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public int getQuantity_tickets() {
		return quantity_tickets;
	}

	public void setQuantity_tickets(int quantity_tickets) {
		this.quantity_tickets = quantity_tickets;
	}

	public AvailabilityAtTheMoment getAvailability_at_the_moment() {
		return availability_at_the_moment;
	}

	public void setAvailability_at_the_moment(AvailabilityAtTheMoment availability_at_the_moment) {
		this.availability_at_the_moment = availability_at_the_moment;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", client_id=" + client_id + ", flight_id=" + flight_id + ", payment_type="
				+ payment_type + ", reservation_date=" + reservation_date + ", quantity_tickets=" + quantity_tickets
				+ ", availability_at_the_moment=" + availability_at_the_moment + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability_at_the_moment, cart_id, client_id, flight_id, payment_type, quantity_tickets,
				reservation_date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return availability_at_the_moment == other.availability_at_the_moment && cart_id == other.cart_id
				&& client_id == other.client_id && flight_id == other.flight_id && payment_type == other.payment_type
				&& quantity_tickets == other.quantity_tickets
				&& Objects.equals(reservation_date, other.reservation_date);
	}

	public Cart(int cart_id, int client_id, int flight_id, TypePayment payment_type, Date reservation_date,
			int quantity_tickets, AvailabilityAtTheMoment availability_at_the_moment) {
		super();
		this.cart_id = cart_id;
		this.client_id = client_id;
		this.flight_id = flight_id;
		this.payment_type = payment_type;
		this.reservation_date = reservation_date;
		this.quantity_tickets = quantity_tickets;
		this.availability_at_the_moment = availability_at_the_moment;
	}

	public Cart() {
		super();
	}
	
	
	

}
