package com.e_commerceWebApp.entity;

import java.util.Objects;

import com.e_commerceWebApp.enums.TypePayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private TypePayment  typePayment ;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;


    public enum State {
        ACTIVE, CLOSED
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public TypePayment getTypePayment() {
		return typePayment;
	}


	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cart, id, state, typePayment, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(id, other.id) && state == other.state
				&& typePayment == other.typePayment && Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", cart=" + cart + ", user=" + user + ", typePayment=" + typePayment + ", state="
				+ state + "]";
	}

	
    
    
}