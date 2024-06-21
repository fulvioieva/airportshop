package com.mr.Airport.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")  // Serve per evitare di avere un JSON infinito, funziona, ma li fa nestati uno dentro l'altro
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public User() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // La strategia GenerationType.IDENTITY è quella più comunemente utilizzata per ottenere ID generati automaticamente dal database.
	@Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
	private long id;
	
	@Column(name = "client_code")
	private String clientCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "logged")
	private int logged;
	
	
	/* L'attributo "mappedBy" specifica il lato inverso della relazione. Indica che
	 * l'attributo user della classe Ticket è quello che possiede la chiave esterna.
	 * In altre parole, dice a JPA che il campo user nell'entità Ticket è
	 * responsabile della gestione della relazione. 
	 * */
//	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Ticket> tickets;

	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLogged() {
		return logged;
	}

	public void setLogged(int logged) {
		this.logged = logged;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	@Override
	public int hashCode() {
		return Objects.hash(clientCode, email, id, logged, name, password, surname, tickets);
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
		return Objects.equals(clientCode, other.clientCode) && Objects.equals(email, other.email) && id == other.id
				&& logged == other.logged && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname)
				&& Objects.equals(tickets, other.tickets);
	}

	

	
	@Override
	public String toString() {
		return "User [id=" + id + ", clientCode=" + clientCode + ", name=" + name + ", surname=" + surname + ", email="
				+ email + ", password=" + password + ", logged=" + logged + ", tickets=" + tickets + "]";
	}

}