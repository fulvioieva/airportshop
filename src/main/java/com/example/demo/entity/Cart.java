package com.example.demo.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cart")
@IdClass(CartKey.class)
public class Cart {
	
	@Id
	@Column(name = "idorder")
	private int idOrder;
	
	@Id
	@Column(name = "idarticle")
	private String idArticle;
	
	@Column(name = "qtaordered")
	private int qtaOrdered;
	
	@Column(name = "unitprice")
	private int unitPrice;
	
	@Column(name = "totalprice")
	private int totalPrice;

	@Override
	public String toString() {
		return "Cart [idOrder=" + idOrder + ", idArticle=" + idArticle + ", qtaOrdered=" + qtaOrdered + ", unitPrice="
				+ unitPrice + ", totalPrice=" + totalPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArticle, idOrder, qtaOrdered, totalPrice, unitPrice);
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
		return idArticle == other.idArticle && idOrder == other.idOrder && qtaOrdered == other.qtaOrdered
				&& totalPrice == other.totalPrice && unitPrice == other.unitPrice;
	}

	/**
	 * @return the idOrder
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder the idOrder to set
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	/**
	 * @return the idArticle
	 */
	public String getIdArticle() {
		return idArticle;
	}

	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @return the qtaOrdered
	 */
	public int getQtaOrdered() {
		return qtaOrdered;
	}

	/**
	 * @param qtaOrdered the qtaOrdered to set
	 */
	public void setQtaOrdered(int qtaOrdered) {
		this.qtaOrdered = qtaOrdered;
	}

	/**
	 * @return the unitPrice
	 */
	public int getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	 

	public Cart(int idOrder, String idArticle, int qtaOrdered, int unitPrice, int totalPrice) {
		super();
		this.idOrder = idOrder;
		this.idArticle = idArticle;
		this.qtaOrdered = qtaOrdered;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}

	public Cart() {
		super();
	}
	
	

}
