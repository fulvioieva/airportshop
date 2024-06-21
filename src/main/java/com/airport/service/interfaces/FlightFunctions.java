package com.airport.service.interfaces;

import com.airport.exception.FlightNotFoundException;
import com.airport.exception.InsuffiecientAvaliablePlaceException;
import com.airport.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightFunctions {
    List<Flight> viewAvailableFlightsFromNow();
    Optional<Flight> getFlight(String idFlight) throws FlightNotFoundException;
    Optional<Flight> updateAvaliablePlace(String idFlight, int quantity) throws FlightNotFoundException, InsuffiecientAvaliablePlaceException;
}
