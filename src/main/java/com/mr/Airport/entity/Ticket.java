package com.mr.Airport.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mr.Airport.enums.PaymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")  // Serve per evitare di avere un JSON infinito, funziona, ma li fa nestati uno dentro l'altro
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ticket() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
	private long id;
	
//	@Column(name = "flight_id")
//	private long flightId;
	
//	@Column(name = "user_id")
//	private long userId;
	
	@Column(name = "tickets_qta")
	private int ticketsQta;
	
	@Column(name = "total_price", precision = 7, scale = 2)
	private BigDecimal totalPrice;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type")
	private PaymentType paymentType;
	
	@Column(name = "purchase_date")
	private Timestamp purchaseDate;
	
	
	
	
	@ManyToOne // Con questa indico la relazione inversa
	@JoinColumn(name = "user_id", nullable = false)  // con questo sto dicendo fare la join prendendo gli user che corrispondono a user_id
	//@JsonIgnore // Ignora questo campo durante la serializzazione
	/* Quando prendo i ticket dell'utente, questo campo riporta l'oggetto user, che riporta i ticket che a loro
	 * volta riportano l'user, creando un JSON infinito. Con questa annotazione questo campo viene ignorato. 
	 * */
//	@JsonBackReference
	private User user;
	
	
	
	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
//	@JsonIgnore
	private Flight flight;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTicketsQta() {
		return ticketsQta;
	}

	public void setTicketsQta(int ticketsQta) {
		this.ticketsQta = ticketsQta;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(flight, id, paymentType, purchaseDate, ticketsQta, totalPrice, user);
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
		return Objects.equals(flight, other.flight) && id == other.id && paymentType == other.paymentType
				&& Objects.equals(purchaseDate, other.purchaseDate) && ticketsQta == other.ticketsQta
				&& Objects.equals(totalPrice, other.totalPrice) && Objects.equals(user, other.user);
	}



	@Override
	public String toString() {
		return "Ticket [id=" + id + ", ticketsQta=" + ticketsQta + ", totalPrice=" + totalPrice + ", paymentType="
				+ paymentType + ", purchaseDate=" + purchaseDate + ", user=" + user + ", flight=" + flight + "]";
	}

}