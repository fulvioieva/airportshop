package com.entity;

import java.sql.Date;
import java.util.Objects;

import com.enums.TypePayment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@Column(name = "reservation_id")
	private Integer reservation_id;

	@Column(name = "client_id")
	private Integer client_id;

	@Column(name = "flight_id")
	private Integer flight_id;

	@Column(name = "reservation_date")
	private Date reservation_date;

	@Column(name = "payment_type")
	private TypePayment payment_type;

	@Column(name = "quantity_tickets")
	private Integer quantity_tickets;

	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public Integer getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(Integer flight_id) {
		this.flight_id = flight_id;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public TypePayment getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(TypePayment payment_type) {
		this.payment_type = payment_type;
	}

	public Integer getQuantity_tickets() {
		return quantity_tickets;
	}

	public void setQuantity_tickets(Integer quantity_tickets) {
		this.quantity_tickets = quantity_tickets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client_id, flight_id, payment_type, quantity_tickets, reservation_date, reservation_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(client_id, other.client_id) && Objects.equals(flight_id, other.flight_id)
				&& payment_type == other.payment_type && Objects.equals(quantity_tickets, other.quantity_tickets)
				&& Objects.equals(reservation_date, other.reservation_date)
				&& Objects.equals(reservation_id, other.reservation_id);
	}

	@Override
	public String toString() {
		return "reservations [reservation_id=" + reservation_id + ", client_id=" + client_id + ", flight_id="
				+ flight_id + ", reservation_date=" + reservation_date + ", payment_type=" + payment_type
				+ ", quantity_tickets=" + quantity_tickets + "]";
	}

	public Reservation(Integer reservation_id, Integer client_id, Integer flight_id, Date reservation_date,
			TypePayment payment_type, Integer quantity_tickets) {
		super();
		this.reservation_id = reservation_id;
		this.client_id = client_id;
		this.flight_id = flight_id;
		this.reservation_date = reservation_date;
		this.payment_type = payment_type;
		this.quantity_tickets = quantity_tickets;
	}

	public Reservation() {
		super();
	}
	
	

}
