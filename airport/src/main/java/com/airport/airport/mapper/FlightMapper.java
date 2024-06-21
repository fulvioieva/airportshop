package com.airport.airport.mapper;

import com.airport.airport.dto.FlightDTO;
import com.airport.airport.model.Flight;

public class FlightMapper {

	public static FlightDTO convertToFlightDTO(Flight flight) {
		
		FlightDTO dto = new FlightDTO();
		
		dto.setIdFlight(flight.getIdFlight());
		dto.setAvailablePlaces(flight.getAvailablePlaces());
		dto.setDateDeparture(flight.getDateDeparture());
		dto.setTimeDeparture(flight.getTimeDeparture());
		dto.setPrice(flight.getPrice());
		dto.setDepartureAirport(flight.getDepartureAirport());
		dto.setDestinationAirport(flight.getDestinationAirport());
		
		return dto;
		
	}
	
}
