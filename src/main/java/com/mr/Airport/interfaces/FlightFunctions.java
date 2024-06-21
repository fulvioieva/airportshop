package com.mr.Airport.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.mr.Airport.entity.Flight;
import com.mr.Airport.entity.Ticket;

public interface FlightFunctions {
	/* 
	 * getAllFlights -
	 * getFlightById -
	 * createFlight
	 * updateFlight
	 * deleteFlight
	 * getFlightsByDepartureArrivalDatesTimes -
	 * checkPlacesAvailable -
	 * addFlightPlaces -
	 * removeFlightPlaces - */
	
	List<Flight> getAllFlights();
	Optional<Flight> getFlightById(long flightId);
	List<Flight> getFlightsByDepartureArrivalDatesTimes(String departureAirport, String arrivalAirport, LocalDate departureDate, LocalTime departureTime);
	boolean checkPlacesAvailable(long flightId, int qta);
	boolean addFlightPlaces(long flightId, int places);
	boolean removeFlightPlaces(long flightId, int places);
}
