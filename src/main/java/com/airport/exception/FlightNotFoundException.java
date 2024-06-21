package com.airport.exception;

public class FlightNotFoundException extends Exception{

    public FlightNotFoundException(String idFlight) {
    super("Client with ID: " + idFlight + " not found");
}
}
