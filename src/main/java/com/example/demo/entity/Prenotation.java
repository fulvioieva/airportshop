package com.example.demo.entity;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prenotation")
public class Prenotation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cartEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cart getCartEntity() {
		return cartEntity;
	}

	public void setCartEntity(Cart cartEntity) {
		this.cartEntity = cartEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartEntity, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotation other = (Prenotation) obj;
		return Objects.equals(cartEntity, other.cartEntity) && id == other.id;
	}

	@Override
	public String toString() {
		return "Prenotation [id=" + id + ", cartEntity=" + cartEntity + "]";
	}

	public Prenotation(int id, Cart cartEntity) {
		super();
		this.id = id;
		this.cartEntity = cartEntity;
	}

	
}
