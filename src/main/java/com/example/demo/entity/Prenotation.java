package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="prenotation")
public class Prenotation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int id_ticket;
	
	@Column
	private int user_has_flight_user_id_user;
	
	@Column
	private int user_has_flight_flight_id_flight;
	
	@Column
	private String name_client;
	
	@Column
	private String surname_client;
	
	@Column
	private String name_flight;
	
	@Column
	private String origin_flight;
	
	@Column
	private String destination_flight;
	
	@Column
	private LocalTime time;
	
	@Column
	private Date date;
	
	@Column
	private String client_id_client;
	
	public Prenotation(int id_ticket, int user_has_flight_user_id_user, int user_has_flight_flight_id_flight,
			String name_client, String surname_client, String name_flight, String origin_flight,
			String destination_flight, LocalTime time, Date date, String client_id_client) {
		super();
		this.id_ticket = id_ticket;
		this.user_has_flight_user_id_user = user_has_flight_user_id_user;
		this.user_has_flight_flight_id_flight = user_has_flight_flight_id_flight;
		this.name_client = name_client;
		this.surname_client = surname_client;
		this.name_flight = name_flight;
		this.origin_flight = origin_flight;
		this.destination_flight = destination_flight;
		this.time = time;
		this.date = date;
		this.client_id_client = client_id_client;
	}

	@Override
	public String toString() {
		return "Prenotation [id_ticket=" + id_ticket + ", user_has_flight_user_id_user=" + user_has_flight_user_id_user
				+ ", user_has_flight_flight_id_flight=" + user_has_flight_flight_id_flight + ", name_client="
				+ name_client + ", surname_client=" + surname_client + ", name_flight=" + name_flight
				+ ", origin_flight=" + origin_flight + ", destination_flight=" + destination_flight + ", time=" + time
				+ ", date=" + date + ", client_id_client=" + client_id_client + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(client_id_client, date, destination_flight, id_ticket, name_client, name_flight,
				origin_flight, surname_client, time, user_has_flight_flight_id_flight, user_has_flight_user_id_user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotation other = (Prenotation) obj;
		return Objects.equals(client_id_client, other.client_id_client) && Objects.equals(date, other.date)
				&& Objects.equals(destination_flight, other.destination_flight) && id_ticket == other.id_ticket
				&& Objects.equals(name_client, other.name_client) && Objects.equals(name_flight, other.name_flight)
				&& Objects.equals(origin_flight, other.origin_flight)
				&& Objects.equals(surname_client, other.surname_client) && Objects.equals(time, other.time)
				&& user_has_flight_flight_id_flight == other.user_has_flight_flight_id_flight
				&& user_has_flight_user_id_user == other.user_has_flight_user_id_user;
	}

	public int getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}

	public int getUser_has_flight_user_id_user() {
		return user_has_flight_user_id_user;
	}

	public void setUser_has_flight_user_id_user(int user_has_flight_user_id_user) {
		this.user_has_flight_user_id_user = user_has_flight_user_id_user;
	}

	public int getUser_has_flight_flight_id_flight() {
		return user_has_flight_flight_id_flight;
	}

	public void setUser_has_flight_flight_id_flight(int user_has_flight_flight_id_flight) {
		this.user_has_flight_flight_id_flight = user_has_flight_flight_id_flight;
	}

	public String getName_client() {
		return name_client;
	}

	public void setName_client(String name_client) {
		this.name_client = name_client;
	}

	public String getSurname_client() {
		return surname_client;
	}

	public void setSurname_client(String surname_client) {
		this.surname_client = surname_client;
	}

	public String getName_flight() {
		return name_flight;
	}

	public void setName_flight(String name_flight) {
		this.name_flight = name_flight;
	}

	public String getOrigin_flight() {
		return origin_flight;
	}

	public void setOrigin_flight(String origin_flight) {
		this.origin_flight = origin_flight;
	}

	public String getDestination_flight() {
		return destination_flight;
	}

	public void setDestination_flight(String destination_flight) {
		this.destination_flight = destination_flight;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClient_id_client() {
		return client_id_client;
	}

	public void setClient_id_client(String client_id_client) {
		this.client_id_client = client_id_client;
	}

	
}
