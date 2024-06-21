package com.airport_database.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.airport_database.enums.TypePayment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idTicket")
	private int idTicket;
	
	@Column(name="flightId")
	private String flightId;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="ticketsQty")
	private int ticketsQty;
	
	 @Enumerated(EnumType.STRING)
	@Column(name="paymentType")
	private TypePayment paymentType;
	 
	 @Column(name="total_price", precision=10, scale=2)
	private BigDecimal totalPrice;

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTicketsQty() {
		return ticketsQty;
	}

	public void setTicketsQty(int ticketsQty) {
		this.ticketsQty = ticketsQty;
	}

	public TypePayment getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(TypePayment paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flightId, idTicket, paymentType, ticketsQty, totalPrice, userId);
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
		return Objects.equals(flightId, other.flightId) && idTicket == other.idTicket
				&& paymentType == other.paymentType && ticketsQty == other.ticketsQty
				&& Objects.equals(totalPrice, other.totalPrice) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Ticket [idTicket=" + idTicket + ", flightId=" + flightId + ", userId=" + userId + ", ticketsQty="
				+ ticketsQty + ", paymentType=" + paymentType + ", totalPrice=" + totalPrice + "]";
	} 


	

}
