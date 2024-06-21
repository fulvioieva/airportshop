package com.airport_database.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airport_database.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	List<Ticket> findByFlightId(String flightId);
	
	List<Ticket> findByUserId(String userId);
	
}
