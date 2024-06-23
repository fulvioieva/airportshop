package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="flight")
public class Flight implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	
	
	private LocalTime time;
	
	
	private Date date;
	
	
	private int price;
	
	
	private int placesAvailable;
	
	@OneToMany(mappedBy = "flightEntity")
    private Set<Cart> carts = new HashSet<>();;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratte_id")
    private Tratte tratteEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPlacesAvailable() {
		return placesAvailable;
	}

	public void setPlacesAvailable(int placesAvailable) {
		this.placesAvailable = placesAvailable;
	}

	public Tratte getTratteEntity() {
		return tratteEntity;
	}

	public void setTratteEntity(Tratte tratteEntity) {
		this.tratteEntity = tratteEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, name, placesAvailable, price, time, tratteEntity);
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
		return Objects.equals(date, other.date) && id == other.id && Objects.equals(name, other.name)
				&& placesAvailable == other.placesAvailable && price == other.price && Objects.equals(time, other.time)
				&& Objects.equals(tratteEntity, other.tratteEntity);
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", time=" + time + ", date=" + date + ", price=" + price
				+ ", placesAvailable=" + placesAvailable + ", carts=" + carts + ", tratteEntity=" + tratteEntity + "]";
	}

	public Flight(int id, String name, LocalTime time, Date date, int price, int placesAvailable, Tratte tratteEntity) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.date = date;
		this.price = price;
		this.placesAvailable = placesAvailable;
		this.tratteEntity = tratteEntity;
	}

	
}
