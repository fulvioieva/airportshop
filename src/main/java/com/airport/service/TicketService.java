package com.airport.service;

import com.airport.exception.InsuffiecientTicketsException;
import com.airport.exception.TicketNotFoundException;
import com.airport.model.Ticket;
import com.airport.repository.TicketRepository;
import com.airport.service.interfaces.TicketFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TicketService implements TicketFunctions{

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> updatedTicketsQuantity(String idFlight, int quantity) throws TicketNotFoundException, InsuffiecientTicketsException {
        Optional<Ticket> optExistingTicket = ticketRepository.findByIdFlight(idFlight);
        if(optExistingTicket.isEmpty()) {
            throw new TicketNotFoundException(idFlight);
        }

        Ticket ticket = optExistingTicket.get();

        if(ticket.getAvailableQuantity() < quantity) {
            throw new InsuffiecientTicketsException("Insuffiecient quantity");
        }

        ticket.setAvailableQuantity(ticket.getAvailableQuantity() - quantity);
        ticketRepository.save(ticket);

        return Optional.ofNullable(ticket);
    }


}
