package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.enums.State;

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
@Table(name="cart")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int nPlaces;

	private int totalPrice;
	
	private State state;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flightEntity;
	
	@OneToMany(mappedBy = "cartEntity")
    private Set<Prenotation> prenotations = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getnPlaces() {
		return nPlaces;
	}

	public void setnPlaces(int nPlaces) {
		this.nPlaces = nPlaces;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(User userEntity) {
		this.userEntity = userEntity;
	}

	public Flight getFlightEntity() {
		return flightEntity;
	}

	public void setFlightEntity(Flight flightEntity) {
		this.flightEntity = flightEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flightEntity, id, nPlaces, prenotations, state, totalPrice, userEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(flightEntity, other.flightEntity) && id == other.id && nPlaces == other.nPlaces
				&& Objects.equals(prenotations, other.prenotations) && state == other.state
				&& totalPrice == other.totalPrice && Objects.equals(userEntity, other.userEntity);
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", nPlaces=" + nPlaces + ", totalPrice=" + totalPrice + ", state=" + state
				+ ", userEntity=" + userEntity + ", flightEntity=" + flightEntity + ", prenotations=" + prenotations
				+ "]";
	}

	public Cart(int id, int nPlaces, int totalPrice, State state, User userEntity, Flight flightEntity,
			Set<Prenotation> prenotations) {
		super();
		this.id = id;
		this.nPlaces = nPlaces;
		this.totalPrice = totalPrice;
		this.state = state;
		this.userEntity = userEntity;
		this.flightEntity = flightEntity;
		this.prenotations = prenotations;
	};

	
}
