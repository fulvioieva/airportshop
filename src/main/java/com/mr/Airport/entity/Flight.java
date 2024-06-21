package com.mr.Airport.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Flight() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
	private long id;
	
	@Column(name = "flight_name")
	private String flightName;
	
	@Column(name = "departure_airport")
	private String departureAirport;
	
	@Column(name = "arrival_airport")
	private String arrivalAirport;
	
	@Column(name = "departure_date")
	private LocalDate departureDate;
	
	@Column(name = "departure_time")
	private LocalTime departureTime;
	
	@Column(name = "places_available")
	private int placesAvailable;
	
	@Column(name = "price")
	private BigDecimal price;
	
	
	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> tickets;

	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public int getPlacesAvailable() {
		return placesAvailable;
	}

	public void setPlacesAvailable(int placesAvailable) {
		this.placesAvailable = placesAvailable;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(arrivalAirport, departureAirport, departureDate, departureTime, flightName, id,
				placesAvailable, price);
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
		return Objects.equals(arrivalAirport, other.arrivalAirport)
				&& Objects.equals(departureAirport, other.departureAirport)
				&& Objects.equals(departureDate, other.departureDate)
				&& Objects.equals(departureTime, other.departureTime) && Objects.equals(flightName, other.flightName)
				&& id == other.id && placesAvailable == other.placesAvailable && Objects.equals(price, other.price);
	}

	
	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightName=" + flightName + ", departureAirport=" + departureAirport
				+ ", arrivalAirport=" + arrivalAirport + ", departureDate=" + departureDate + ", departureTime="
				+ departureTime + ", placesAvailable=" + placesAvailable + ", price=" + price + "]";
	}

}