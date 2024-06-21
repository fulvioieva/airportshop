package it.mattiacaddeo.MyEXE_Airport.models;

import java.util.Objects;

import it.mattiacaddeo.MyEXE_Airport.enums.TypePayment;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
create table if not exists Reservations (
	id_reservation int auto_increment,
    id_client varchar(255),
    id_flight varchar(255),
    type_payment enum('credit_card', 'debit_card'),
    total_price decimal(10, 2),
    primary key(id_reservation),
    CONSTRAINT FK_ClientReservation FOREIGN KEY (id_client)
    REFERENCES Clients(id_client),
    CONSTRAINT FK_FlightReservation FOREIGN KEY (id_flight)
    REFERENCES Flights(id_flight)
);
*/

@Entity
@Table(name = "Reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;
	@Enumerated(EnumType.STRING)
	private TypePayment typePayment;
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "idFlight")
	private Flight flight;
	
	@OneToOne(mappedBy = "reservation")
	private Ticket ticket;
	
	public Reservation() {
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, flight, idReservation, totalPrice, typePayment);
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
		return Objects.equals(client, other.client) && Objects.equals(flight, other.flight)
				&& idReservation == other.idReservation
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice)
				&& typePayment == other.typePayment;
	}

	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", typePayment=" + typePayment + ", totalPrice="
				+ totalPrice + ", client=" + client + ", flight=" + flight + "]";
	}

}
