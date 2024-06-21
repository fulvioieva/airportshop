package com.airport.service.interfaces;


import com.airport.exception.InsuffiecientTicketsException;
import com.airport.exception.OrderNotFoundException;
import com.airport.exception.TicketNotFoundException;
import com.airport.model.Ticket;

import java.util.Optional;

public interface TicketFunctions {
    public Optional<Ticket> updatedTicketsQuantity(String idFlight, int quantity) throws TicketNotFoundException, InsuffiecientTicketsException;
}
