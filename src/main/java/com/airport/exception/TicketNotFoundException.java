package com.airport.exception;

public class TicketNotFoundException extends Exception {

    public TicketNotFoundException(String idFlight) {
        super("Ticket with flight ID: " + idFlight + " Not Found");
    }
}
