package com.e_commerceWebApp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.e_commerceWebApp.enums.TypePayment;
import jakarta.persistence.*;

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

	@ManyToMany
	@JoinTable(
			name = "articleOrder",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "article_id")
	)
	private List<Article> articles = new ArrayList<>();

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

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", user=" + user +
				", typePayment=" + typePayment +
				", state=" + state +
				", totalPrice=" + totalPrice +
				", articles=" + articles +
				'}';
	}
}