package com.e_commerceWebApp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	@JoinTable(
			name = "articleCart",
			joinColumns = @JoinColumn(name = "article_id"),
			inverseJoinColumns = @JoinColumn(name = "cart_id")
	)
	private List<Cart> carts = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "articleOrder",
			joinColumns = @JoinColumn(name = "article_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	private List<Order> orders = new ArrayList<>();

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", name=" + name + ", description=" + description + ", price="
				+ price + ", qtyAvailable=" + qtyAvailable + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, idArticle, name, price, qtyAvailable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(description, other.description) && Objects.equals(idArticle, other.idArticle)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& qtyAvailable == other.qtyAvailable;
	}

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

}

