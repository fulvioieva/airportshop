package com.e_commerceWebApp.entity;

import java.math.BigDecimal;
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
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType", nullable = false)
    private TypePayment  typePayment ;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

	@Column(name = "totalPrice", nullable = false)
	private BigDecimal totalPrice;

    public enum State {
        ACTIVE, CLOSED
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id) && Objects.equals(user, order.user) && typePayment == order.typePayment && state == order.state && Objects.equals(totalPrice, order.totalPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, typePayment, state, totalPrice);
	}
}