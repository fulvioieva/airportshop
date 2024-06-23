package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.enums.Status;
import com.example.demo.enums.TypePayment;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	
	private String surname;
	
	private TypePayment typePayment;
	
	
	private String password;
	
	
	private String address;
	
	
	private String mail;
	
	
	private Date dateBirth;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "userEntity")
    private Set<Cart> carts = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, dateBirth, id, mail, name, password, status, surname, typePayment);
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
		return Objects.equals(address, other.address) && Objects.equals(dateBirth, other.dateBirth) && id == other.id
				&& Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && status == other.status
				&& Objects.equals(surname, other.surname) && typePayment == other.typePayment;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", typePayment=" + typePayment
				+ ", password=" + password + ", address=" + address + ", mail=" + mail + ", dateBirth=" + dateBirth
				+ ", status=" + status + ", carts=" + carts + "]";
	}

	public User(int id, String name, String surname, TypePayment typePayment, String password, String address,
			String mail, Date dateBirth, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.typePayment = typePayment;
		this.password = password;
		this.address = address;
		this.mail = mail;
		this.dateBirth = dateBirth;
		this.status = status;
	};

	public User() {
		super();
	};
	
}
