package com.example.demo.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Article")
public class Article {
	
	@Id
	@Column(name = "idarticle")
	private String idArticle;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "descr")
	private String descr;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "qtaavailable")
	private int qtaAvailable;

	@Override
	public int hashCode() {
		return Objects.hash(descr, idArticle, name, price, qtaAvailable);
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
		return Objects.equals(descr, other.descr) && Objects.equals(idArticle, other.idArticle)
				&& Objects.equals(name, other.name) && price == other.price && qtaAvailable == other.qtaAvailable;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", name=" + name + ", descr=" + descr + ", price=" + price
				+ ", qtaAvailable=" + qtaAvailable + "]";
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the qtaAvailable
	 */
	public int getQtaAvailable() {
		return qtaAvailable;
	}

	/**
	 * @param qtaAvailable the qtaAvailable to set
	 */
	public void setQtaAvailable(int qtaAvailable) {
		this.qtaAvailable = qtaAvailable;
	}

}
