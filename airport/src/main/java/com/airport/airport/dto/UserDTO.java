package com.airport.airport.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private String idUser;

	private String name;
	
	private String surname;
	
	private String password;

    private List<TicketDTO> tickets = new ArrayList<>();

	public UserDTO(String idUser, String name, String surname, String password, List<TicketDTO> tickets) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.tickets = tickets;
	}
	
	

	public UserDTO() {
		super();
	}



	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TicketDTO> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}
    
    
	
}
