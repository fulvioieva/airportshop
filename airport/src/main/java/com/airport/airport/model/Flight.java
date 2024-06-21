package com.airport.airport.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "id_flight")
	private String idFlight;
	
	@Column(name= "available_places")
	private int availablePlaces;
	
	@Column(name= "date_departure")
	private LocalDate dateDeparture;
	
	@Column(name= "time_departure")
	private LocalTime timeDeparture;
	
	@Column(name= "price")
	private int price;
	
	@Column(name= "departure_airport")
	private String departureAirport;
	
	@Column(name= "destination_airport")
	private String destinationAirport;
	
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

	public Flight(String idFlight, int availablePlaces, LocalDate dateDeparture, LocalTime timeDeparture, int price,
			String departureAirport, String destinationAirport, List<Ticket> tickets) {
		super();
		this.idFlight = idFlight;
		this.availablePlaces = availablePlaces;
		this.dateDeparture = dateDeparture;
		this.timeDeparture = timeDeparture;
		this.price = price;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
		this.tickets = tickets;
	}

	public Flight() {
		super();
	}

	public String getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(String idFlight) {
		this.idFlight = idFlight;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}

	public LocalDate getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(LocalDate dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public LocalTime getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(LocalTime timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availablePlaces, dateDeparture, departureAirport, destinationAirport, idFlight, price,
				tickets, timeDeparture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return availablePlaces == other.availablePlaces && Objects.equals(dateDeparture, other.dateDeparture)
				&& Objects.equals(departureAirport, other.departureAirport)
				&& Objects.equals(destinationAirport, other.destinationAirport)
				&& Objects.equals(idFlight, other.idFlight) && price == other.price
				&& Objects.equals(tickets, other.tickets) && Objects.equals(timeDeparture, other.timeDeparture);
	}

	
	
}
