package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Flight;

public interface IFlightService {
	boolean   addFlight(Flight flight);
	boolean   updateFlight (Flight flight);
	List<Flight> getAllFlight();
	Optional<Flight> getFlight(int idFlight);
	boolean deleteFlight(int idFlight);
	List<Flight> getFlightsOrigin(String city);
	List<Flight> getFlightBetween(Date date, Date date2);
	List<Flight> getFlightsDestination(String city);
}
