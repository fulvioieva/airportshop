package it.mattiacaddeo.MyEXE_Airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.mattiacaddeo.MyEXE_Airport.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
