package com.e_commerceWebApp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "totalPrice", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
	@JsonIgnore
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

	@ManyToMany
	@JoinTable(
			name = "articleCart",
			joinColumns = @JoinColumn(name = "cart_id"),
			inverseJoinColumns = @JoinColumn(name = "article_id")
	)
	@JsonBackReference
	private List<Article> articles = new ArrayList<>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cart cart = (Cart) o;
		return Objects.equals(id, cart.id) && Objects.equals(userId, cart.userId) && Objects.equals(totalPrice, cart.totalPrice) && Objects.equals(user, cart.user) && Objects.equals(articles, cart.articles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, totalPrice, user, articles);
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", userId=" + userId +
				", totalPrice=" + totalPrice +
				", user=" + user +
				", articles=" + articles +
				'}';
	}
}