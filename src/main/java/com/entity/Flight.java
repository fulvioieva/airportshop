package com.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {
	@Id
	@Column(name = "flight_id")
	private Integer flight_id;
	
	@Column(name = "flight_name")
	private String flight_name;
	
	@Column(name = "departure_airport")
	private String departure_airport;
	
	@Column(name = "destination_airport")
	private String destination_airport;
	
	@Column(name = "flight_date")
	private Date flight_date;
	
	@Column(name = "flight_time")
	private Time flight_time;
	
	@Column(name = "seats_available")
	private Integer seats_available;
	
	@Column(name = "price_per_unit")
	private Integer price_per_unit;

	public Integer getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(Integer flight_id) {
		this.flight_id = flight_id;
	}

	public String getFlight_name() {
		return flight_name;
	}

	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}

	public String getDeparture_airport() {
		return departure_airport;
	}

	public void setDeparture_airport(String departure_airport) {
		this.departure_airport = departure_airport;
	}

	public String getDestination_airport() {
		return destination_airport;
	}

	public void setDestination_airport(String destination_airport) {
		this.destination_airport = destination_airport;
	}

	public Date getFlight_date() {
		return flight_date;
	}

	public void setFlight_date(Date flight_date) {
		this.flight_date = flight_date;
	}

	public Time getFlight_time() {
		return flight_time;
	}

	public void setFlight_time(Time flight_time) {
		this.flight_time = flight_time;
	}

	public Integer getSeats_available() {
		return seats_available;
	}

	public void setSeats_available(Integer seats_available) {
		this.seats_available = seats_available;
	}

	public Integer getPrice_per_unit() {
		return price_per_unit;
	}

	public void setPrice_per_unit(Integer price_per_unit) {
		this.price_per_unit = price_per_unit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departure_airport, destination_airport, flight_date, flight_id, flight_name, flight_time,
				price_per_unit, seats_available);
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
		return Objects.equals(departure_airport, other.departure_airport)
				&& Objects.equals(destination_airport, other.destination_airport)
				&& Objects.equals(flight_date, other.flight_date) && Objects.equals(flight_id, other.flight_id)
				&& Objects.equals(flight_name, other.flight_name) && Objects.equals(flight_time, other.flight_time)
				&& Objects.equals(price_per_unit, other.price_per_unit)
				&& Objects.equals(seats_available, other.seats_available);
	}

	@Override
	public String toString() {
		return "Flights [flight_id=" + flight_id + ", flight_name=" + flight_name + ", departure_airport="
				+ departure_airport + ", destination_airport=" + destination_airport + ", flight_date=" + flight_date
				+ ", flight_time=" + flight_time + ", seats_available=" + seats_available + ", price_per_unit="
				+ price_per_unit + "]";
	}

	public Flight(Integer flight_id, String flight_name, String departure_airport, String destination_airport,
			Date flight_date, Time flight_time, Integer seats_available, Integer price_per_unit) {
		super();
		this.flight_id = flight_id;
		this.flight_name = flight_name;
		this.departure_airport = departure_airport;
		this.destination_airport = destination_airport;
		this.flight_date = flight_date;
		this.flight_time = flight_time;
		this.seats_available = seats_available;
		this.price_per_unit = price_per_unit;
	}

	public Flight() {
		super();
	}



	
}
