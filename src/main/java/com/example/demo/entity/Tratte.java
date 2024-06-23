package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tratte")
public class Tratte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String origin;
	
	private String destination;
	
	@OneToMany(mappedBy = "tratteEntity")
    private Set<Flight> flights;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(destination, id, origin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratte other = (Tratte) obj;
		return Objects.equals(destination, other.destination) && id == other.id && Objects.equals(origin, other.origin);
	}

	@Override
	public String toString() {
		return "Tratte [id=" + id + ", origin=" + origin + ", destination=" + destination + ", flights=" + flights
				+ "]";
	}

	public Tratte(int id, String origin, String destination) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
	}

	
}
