package it.mattiacaddeo.MyEXE_Airport.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
create table if not exists Clients (
	id_client varchar(255),
	name varchar(255) not null,
    surname varchar(255) not null,
    client_code varchar(255) not null unique,
    password char(12) not null,
    primary key(id_client)
);
*/
@Entity
@Table(name = "Clients")
public class Client {

	@Id
	private String idClient;
	private String name;
	private String surname;
	private String clientCode;
	private String password;
	private String roles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="client", cascade={CascadeType.ALL})
	@JsonIgnore
	private List<Reservation> reservations;

	public Client() {
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
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

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(Reservation reservation) {
		if (reservations == null) {
			reservations = new ArrayList<>();
		}
		if (!reservations.contains(reservation)) {
			reservations.add(reservation);
		}
	}
	
	public void removeReservation(Reservation reservation) {
		if (reservations.contains(reservation)) {
			reservations.remove(reservation);
			reservation.setClient(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientCode, idClient, name, password, surname);
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
		return Objects.equals(clientCode, other.clientCode) && Objects.equals(idClient, other.idClient)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", name=" + name + ", surname=" + surname + ", clientCode=" + clientCode
				+ ", password=" + password + "]";
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
