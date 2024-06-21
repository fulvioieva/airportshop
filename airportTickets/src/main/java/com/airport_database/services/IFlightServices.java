package com.airport_database.services;

import java.util.List;

import com.airport_database.entity.Flight;

public interface IFlightServices {
	
	List<Flight> getAllFlight();
	
	boolean existsById(String idFlight);
	
	Flight getFlightById(String idFlight);
	
	public List<String> getFlightsIdByDates(String dateStart, String dateEnd);
	
	public List<Flight> getFlightsByDepartureAirport(String Airport);

	public List<Flight> getFlightsByDestinationAirport(String Airport);
	
		
}
