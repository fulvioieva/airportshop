package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="flight")
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int id_flight;
	
	@Column
	private String name;
	
	@Column
	private String origin;
	
	@Column
	private String destination;
	
	@Column
	private LocalTime time;
	
	@Column
	private Date date;
	
	@Column
	private int price;
	
	@Column 
	private int places_available;

	public Flight(int id_flight, String name, String origin, String destination, LocalTime time, Date date, int price,
			int places_available) {
		super();
		this.id_flight = id_flight;
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.time = time;
		this.date = date;
		this.price = price;
		this.places_available = places_available;
	}

	@Override
	public String toString() {
		return "Flight [id_flight=" + id_flight + ", name=" + name + ", origin=" + origin + ", destination="
				+ destination + ", time=" + time + ", date=" + date + ", price=" + price + ", places_available="
				+ places_available + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, destination, id_flight, name, origin, places_available, price, time);
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
		return Objects.equals(date, other.date) && Objects.equals(destination, other.destination)
				&& id_flight == other.id_flight && Objects.equals(name, other.name)
				&& Objects.equals(origin, other.origin) && places_available == other.places_available
				&& price == other.price && Objects.equals(time, other.time);
	}

	public int getId_flight() {
		return id_flight;
	}

	public void setId_flight(int id_flight) {
		this.id_flight = id_flight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPlaces_available() {
		return places_available;
	}

	public void setPlaces_available(int places_available) {
		this.places_available = places_available;
	}
}
