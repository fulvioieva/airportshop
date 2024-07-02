package com.e_commerceWebApp.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "articleCart"/*, schema = "ecommerce_on_db"*/)
public class ArticleCart {

	@EmbeddedId
	private ArticleCartId id;

	@MapsId("articleId")
	@ManyToOne
	@JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
	private Article article;

	@MapsId("cartId")
	@ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
	private Cart cart;

	@Column(name = "qty_ordered", nullable = false)
	private Integer qtyOrdered;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(Integer qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	@Override
	public int hashCode() {
		return Objects.hash(article, cart, qtyOrdered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleCart other = (ArticleCart) obj;
		return Objects.equals(article, other.article) && Objects.equals(cart, other.cart)
				&& Objects.equals(qtyOrdered, other.qtyOrdered);
	}

	@Override
	public String toString() {
		return "ArticleCart [article=" + article + ", cart=" + cart + ", qtyOrdered=" + qtyOrdered + "]";
	}



}