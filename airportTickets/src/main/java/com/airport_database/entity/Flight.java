package com.airport_database.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="flights")
public class Flight  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idFlight")
	private String idFlight;
	
	@Column(name="availablePlaces")
	private int availablePlaces;
	
	@Column(name="dateDeparture")
	private LocalDateTime dateDeparture;
	
	@Column(name="price", precision=6, scale=2)
	private BigDecimal price;

	@Column(name = "departureAirport")
	private String departureAirport;
	
	@Column(name = "destinationAirport")
	private String destinationAirport;

	public String getIdFlight() {
		return idFlight;
	}

	@Override
	public String toString() {
		return "Flight [idFlight=" + idFlight + ", availablePlaces=" + availablePlaces + ", dateDeparture="
				+ dateDeparture + ", price=" + price + ", departureAirport=" + departureAirport
				+ ", destinationAirport=" + destinationAirport + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(availablePlaces, dateDeparture, departureAirport, destinationAirport, idFlight, price);
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
				&& Objects.equals(destinationAirport, other.destinationAirport) && idFlight == other.idFlight
				&& Objects.equals(price, other.price);
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

	public LocalDateTime getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(LocalDateTime dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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

}
