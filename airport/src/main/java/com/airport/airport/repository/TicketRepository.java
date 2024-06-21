package com.airport.airport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.airport.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	Optional<Ticket> findByUserIdUserAndFlightIdFlight(String idUser, String idFlight);
	
	List<Ticket> findByUserIdUser(String idUser);
	
}
