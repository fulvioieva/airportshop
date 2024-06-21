package com.example.demo.entity;

import java.util.Objects;

import come.example.demo.enums.StateOrder;
import come.example.demo.enums.TypePayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderhead")
public class OrderHead {

	@Id
	@Column(name = "idorder")
	private int idOrder;

	@Column(name = "idclient")
	private String idClient;

	@Column(name = "address")
	private String address;

	@Column(name = "typepayment")
	@Enumerated(EnumType.STRING)
	private TypePayment typePayment;

	@Column(name = "dtorder")
	private String dtOrder;

	@Column(name = "totalorderprice")
	private String totalOrderPrice;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private StateOrder stateOrder;

	public OrderHead() {
		super();
	}

	public OrderHead(int idOrder, String idClient, String address, TypePayment typePayment, String dtOrder,
			String totalOrderPrice, StateOrder stateOrder) {
		super();
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.address = address;
		this.typePayment = typePayment;
		this.dtOrder = dtOrder;
		this.totalOrderPrice = totalOrderPrice;
		this.stateOrder = stateOrder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dtOrder, idClient, idOrder, stateOrder, totalOrderPrice, typePayment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderHead other = (OrderHead) obj;
		return Objects.equals(address, other.address) && Objects.equals(dtOrder, other.dtOrder)
				&& Objects.equals(idClient, other.idClient) && idOrder == other.idOrder
				&& stateOrder == other.stateOrder && Objects.equals(totalOrderPrice, other.totalOrderPrice)
				&& typePayment == other.typePayment;
	}

	@Override
	public String toString() {
		return "OrderDetail [idOrder=" + idOrder + ", idClient=" + idClient + ", Address=" + address + ", typePayment="
				+ typePayment + "]";
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
	 * @return the idClient
	 */
	public String getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the typePayment
	 */
	public TypePayment getTypePayment() {
		return typePayment;
	}

	/**
	 * @param typePayment the typePayment to set
	 */
	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	/**
	 * @return the dtOrder
	 */
	public String getDtOrder() {
		return dtOrder;
	}

	/**
	 * @param dtOrder the dtOrder to set
	 */
	public void setDtOrder(String dtOrder) {
		this.dtOrder = dtOrder;
	}

	/**
	 * @return the totalOrderPrice
	 */
	public String getTotalOrderPrice() {
		return totalOrderPrice;
	}

	/**
	 * @param totalOrderPrice the totalOrderPrice to set
	 */
	public void setTotalOrderPrice(String totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	/**
	 * @return the stateOrder
	 */
	public StateOrder getStateOrder() {
		return stateOrder;
	}

	/**
	 * @param stateOrder the stateOrder to set
	 */
	public void setStateOrder(StateOrder stateOrder) {
		this.stateOrder = stateOrder;
	}

}
