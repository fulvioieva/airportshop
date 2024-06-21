package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.example.demo.enums.Status;
import com.example.demo.enums.TypePayment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_user")
	private int idUser;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type_payment")
	private TypePayment typePayment;
	
	@Column(name="password")
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name = "mail", unique = true)
	private String mail;
	
	@Column(name="date_birth")
	private Date date_birth;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDate_birth() {
		return date_birth;
	}

	public void setDate_birth(Date date_birth) {
		this.date_birth = date_birth;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, date_birth, idUser, mail, name, password, status, surname, typePayment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(date_birth, other.date_birth)
				&& idUser == other.idUser && Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && status == other.status
				&& Objects.equals(surname, other.surname) && typePayment == other.typePayment;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", surname=" + surname + ", typePayment=" + typePayment
				+ ", password=" + password + ", address=" + address + ", mail=" + mail + ", date_birth=" + date_birth
				+ ", status=" + status + "]";
	}

	public User(int idUser, String name, String surname, TypePayment typePayment, String password, String address,
			String mail, Date date_birth, Status status) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.typePayment = typePayment;
		this.password = password;
		this.address = address;
		this.mail = mail;
		this.date_birth = date_birth;
		this.status = status;
	}

		
}
