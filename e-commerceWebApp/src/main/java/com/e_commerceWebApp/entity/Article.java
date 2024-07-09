package com.e_commerceWebApp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="articles")
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String idArticle;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="unitPrice", precision=10, scale=2)
	private BigDecimal price;
	
	@Column(name="qtyAvailable")
	private int qtyAvailable;

	@ManyToMany
	@JsonIgnore
	@JoinTable(
			name = "articleCart",
			joinColumns = @JoinColumn(name = "article_id"),
			inverseJoinColumns = @JoinColumn(name = "cart_id")
	)
	@JsonManagedReference
	private List<Cart> carts = new ArrayList<>();

	@ManyToMany
	@JsonIgnore
	@JoinTable(
			name = "articleOrder",
			joinColumns = @JoinColumn(name = "article_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	@JsonManagedReference
	private List<Order> orders = new ArrayList<>();

	public String getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQtyAvailable() {
		return qtyAvailable;
	}

	public void setQtyAvailable(int qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Article{" +
				"idArticle='" + idArticle + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", qtyAvailable=" + qtyAvailable +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Article article = (Article) o;
		return qtyAvailable == article.qtyAvailable && Objects.equals(idArticle, article.idArticle) && Objects.equals(name, article.name) && Objects.equals(description, article.description) && Objects.equals(price, article.price) && Objects.equals(carts, article.carts) && Objects.equals(orders, article.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArticle, name, description, price, qtyAvailable, carts, orders);
	}
}

