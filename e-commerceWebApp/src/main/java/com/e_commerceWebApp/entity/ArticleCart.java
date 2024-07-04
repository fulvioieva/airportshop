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


	public Integer getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(Integer qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

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

	public ArticleCartId getId() {
		return id;
	}

	public void setId(ArticleCartId id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArticleCart that = (ArticleCart) o;
		return Objects.equals(id, that.id) && Objects.equals(article, that.article) && Objects.equals(cart, that.cart) && Objects.equals(qtyOrdered, that.qtyOrdered);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, article, cart, qtyOrdered);
	}
}