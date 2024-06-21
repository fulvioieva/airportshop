package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer>{

}
