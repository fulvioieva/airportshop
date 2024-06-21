package com.airport.airport.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.airport.airport.model.Flight;

public interface FlightFunctions {

	List<Flight> getAllFlights();
	
	Optional<Flight> getFlight(String idFlight);
	
	List<Flight> getFlights();
	
	Flight updateAvailablePlaces(String idFlight, int ticketsQty);
	
	boolean checkAvailablePlaces(String idFlight, int ticketsQty);

	List<Flight> getFlights(String departureAirport, String destinationAirport, LocalDate dateDeparture,
			LocalTime timeDeparture);
}
