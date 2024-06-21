package com.airport.repository;

import com.airport.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.client.idClient = :idClient")
    List<Order> findAllByClientId(@Param("idClient") String idClient);

    @Query("SELECT o FROM Order o WHERE o.ticket.idTicket = :idTicket")
    List<Order> findAllByTicketId(@Param("idTicket") int idTicket);

    Optional<Order> findByIdOrder(String idOrder);

    @Query("SELECT o FROM Order o WHERE o.client.idClient = :idClient AND o.ticket.idTicket = :idTicket")
    Optional<Order> findByClientIdAndTicketId(@Param("idClient") String idClient, @Param("idTicket") int idTicket);




}
