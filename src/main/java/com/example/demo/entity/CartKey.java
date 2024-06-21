package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;


public class CartKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idOrder;
	private String idArticle;
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
	@Override
	public int hashCode() {
		return Objects.hash(idArticle, idOrder);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartKey other = (CartKey) obj;
		return Objects.equals(idArticle, other.idArticle) && idOrder == other.idOrder;
	}
	public CartKey(int idOrder, String idArticle) {
		super();
		this.idOrder = idOrder;
		this.idArticle = idArticle;
	}
	public CartKey() {
		super();
	}
	
	
	
	
	

}
