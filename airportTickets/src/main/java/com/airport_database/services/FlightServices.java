package com.airport_database.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airport_database.entity.Flight;
import com.airport_database.repository.FlightRepository;

@Service
public class FlightServices implements IFlightServices {

	@Autowired
	private FlightRepository flightRepo;
	
	
	@Override
	public List<Flight> getAllFlight() {
		return flightRepo.findAll();
	}

	@Override
	public boolean existsById(String idFlight) {
		return flightRepo.existsById(idFlight);
	}

	@Override
	public Flight getFlightById(String idFlight) {
		return flightRepo.findById(idFlight).get();
	}

	@Override
	public List<String> getFlightsIdByDates(String dateStart, String dateEnd) {
		LocalDateTime dateStartLcl = LocalDateTime.parse(dateStart + "T00:00:00");
	    LocalDateTime dateEndLcl = LocalDateTime.parse(dateEnd + "T23:59:59");
		List<String> flightsIds= flightRepo.findByDatesBetween(dateStartLcl,dateEndLcl);
		return flightsIds;
	}

	@Override
	public List<Flight> getFlightsByDepartureAirport(String airport) {
		return flightRepo.findByDepartureAirport(airport);
	}

	@Override
	public List<Flight> getFlightsByDestinationAirport(String airport) {
		return flightRepo.findByDestinationAirport(airport);
	}

}
