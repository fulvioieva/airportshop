package com.airport.airport.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.airport.interfaces.FlightFunctions;
import com.airport.airport.model.Flight;
import com.airport.airport.repository.FlightRepository;

@Service 
public class ServiceFlight implements FlightFunctions{
	
	@Autowired
	private FlightRepository flightRepository;
	

	@Override
	public List<Flight> getAllFlights() {
		
		return flightRepository.findAll();
		
	}

	@Override
	public Optional<Flight> getFlight(String idFlight) {
		
		return flightRepository.findById(idFlight);
		
	}

	@Override
	public List<Flight> getFlights(String departureAirport, String destinationAirport, LocalDate dateDeparture,
			LocalTime timeDeparture) {
		
		return flightRepository.findByDepartureAirportAndDestinationAirportAndDateDepartureAndTimeDeparture(departureAirport, destinationAirport, dateDeparture, timeDeparture);
		
	}

	@Override
	public Flight updateAvailablePlaces(String idFlight, int ticketsQty) {
		
		Flight flightToUpdate = getFlight(idFlight).get();
		
		flightToUpdate.setAvailablePlaces(flightToUpdate.getAvailablePlaces() - ticketsQty);
		
		flightRepository.save(flightToUpdate);
		
		return flightToUpdate;
		
	}
	
	@Override
	public boolean checkAvailablePlaces(String idFlight, int ticketsQty) {
		
		Flight flight = getFlight(idFlight).get();
		
		return flight.getAvailablePlaces() - ticketsQty <= -1 ? false : true;
		
	}

	@Override
	public List<Flight> getFlights() {
		
		
		return flightRepository.findAllFromNow();
		
	}



}
