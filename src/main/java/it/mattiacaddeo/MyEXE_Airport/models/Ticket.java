package it.mattiacaddeo.MyEXE_Airport.models;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
create table if not exists Tickets (
	id_ticket int auto_increment,
    id_reservation int,
    total_price decimal(10, 2),
    tickets_quantity int,
	primary key(id_ticket),
    CONSTRAINT FK_ReservationTicket FOREIGN KEY (id_reservation)
    REFERENCES Reservations(id_reservation)
);
*/

@Entity
@Table(name = "Tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTicket;
	private double totalPrice;
	private int ticketsQuantity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idReservation")
	private Reservation reservation;

	public Ticket() {
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTicketsQuantity() {
		return ticketsQuantity;
	}

	public void setTicketsQuantity(int ticketsQuantity) {
		this.ticketsQuantity = ticketsQuantity;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTicket, reservation, ticketsQuantity, totalPrice);
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
		return idTicket == other.idTicket && Objects.equals(reservation, other.reservation)
				&& ticketsQuantity == other.ticketsQuantity
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
	}

	@Override
	public String toString() {
		return "Ticket [idTicket=" + idTicket + ", totalPrice=" + totalPrice + ", ticketsQuantity=" + ticketsQuantity
				+ ", reservation=" + reservation + "]";
	}

}
