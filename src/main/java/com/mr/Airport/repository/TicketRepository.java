package com.mr.Airport.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mr.Airport.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	// Query JPQL per aggiornare il numero di biglietti prenotati.
	@Modifying
    @Transactional
    @Query("UPDATE Ticket u SET u.ticketsQta = :ticketsQta WHERE u.id = :ticketId")
	int updateTicketsQta(@Param("ticketId") long ticketId, @Param("ticketsQta") int ticketsQta);
	
	// Query JPQL per aggiornare il prezzo totale della prenotazione.
	@Modifying
    @Transactional
    @Query("UPDATE Ticket u SET u.totalPrice = :totalPrice WHERE u.id = :ticketId")
	int updateTicketsTotalPrice(@Param("ticketId") long ticketId, @Param("totalPrice") BigDecimal totalPrice);
	
}
