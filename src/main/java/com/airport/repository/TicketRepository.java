package com.airport.repository;

import com.airport.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.flight.idFlight = :idFlight")
    Optional<Ticket> findByIdFlight(@Param("idFlight") String idFlight);
/*
    @Query("SELECT t FROM Ticket t WHERE t.order.id = :orderId")
    List<Ticket> findByOrderId(@Param("orderId") String orderId);*/

}
