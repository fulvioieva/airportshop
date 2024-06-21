package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import come.example.demo.enums.TypePayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idclient" , unique=true)
	private String idClient;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "address")
	private String address;

	@Column(name = "mail")
	private String mail;

	@Column(name = "password")
	private String password;

	@Column(name = "typepayment")
	@Enumerated(EnumType.STRING)
	private TypePayment typePayment;

	@Column(name = "dtsignup")
	private String dtSignup;
	
	@Column(name = "dtsignoff")
	private String dtSignoff;
	
	@Column(name = "dtlastlogin")
	private String dtLastLogin;
	
	@Column(name = "tmlastlogin")
	private String tmLastLogin;

	public Client() {
		super();
	}
	
	

	public Client(String idClient, String name, String surname, String address, String mail, String password,
			TypePayment typePayment, String dtSignup, String dtSignoff, String dtLastLogin, String tmLastLogin) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.mail = mail;
		this.password = password;
		this.typePayment = typePayment;
		this.dtSignup = dtSignup;
		this.dtSignoff = dtSignoff;
		this.dtLastLogin = dtLastLogin;
		this.tmLastLogin = tmLastLogin;
	}



	@Override
	public int hashCode() {
		return Objects.hash(address, idClient, mail, name, password, surname, typePayment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(address, other.address) && Objects.equals(idClient, other.idClient)
				&& Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname)
				&& typePayment == other.typePayment;
	}

	@Override
	public String toString() {
		return "idClient=" + idClient + ", name=" + name + ", surname=" + surname + ", address=" + address
				+ ", mail=" + mail + ", password=" + password + ", typePayment=" + typePayment;
	}
	
	
	

	/**
	 * @return the dtSignup
	 */
	public String getDtSignup() {
		return dtSignup;
	}

	/**
	 * @param dtSignup the dtSignup to set
	 */
	public void setDtSignup(String dtSignup) {
		this.dtSignup = dtSignup;
	}

	/**
	 * @return the dtSignoff
	 */
	public String getDtSignoff() {
		return dtSignoff;
	}

	/**
	 * @param dtSignoff the dtSignoff to set
	 */
	public void setDtSignoff(String dtSignoff) {
		this.dtSignoff = dtSignoff;
	}

	/**
	 * @return the dtLastLogin
	 */
	public String getDtLastLogin() {
		return dtLastLogin;
	}

	/**
	 * @param dtLastLogin the dtLastLogin to set
	 */
	public void setDtLastLogin(String dtLastLogin) {
		this.dtLastLogin = dtLastLogin;
	}

	/**
	 * @return the tmLastLogin
	 */
	public String getTmLastLogin() {
		return tmLastLogin;
	}

	/**
	 * @param tmLastLogin the tmLastLogin to set
	 */
	public void setTmLastLogin(String tmLastLogin) {
		this.tmLastLogin = tmLastLogin;
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
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
