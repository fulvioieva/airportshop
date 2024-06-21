package com.mr.Airport.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.Airport.entity.Flight;
import com.mr.Airport.entity.Ticket;
import com.mr.Airport.interfaces.FlightFunctions;
import com.mr.Airport.repository.FlightRepository;

@Service
public class FlightService implements FlightFunctions {
	
	@Autowired
	FlightRepository flightRepository;

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Optional<Flight> getFlightById(long flightId) {
		return flightRepository.findById(flightId);
	}

	@Override
	public List<Flight> getFlightsByDepartureArrivalDatesTimes(String departureAirport, String arrivalAirport,
			LocalDate departureDate, LocalTime departureTime) {
		return flightRepository.findFlightsByDepartureArrivalAfterDateTime(departureAirport, arrivalAirport, departureDate, departureTime);
	}

	// Questo metodo ritorna true se la qta richiesta è disponibile
	@Override
	public boolean checkPlacesAvailable(long flightId, int qta) {
		if (!flightRepository.existsById(flightId)) { return false; }
		int placesAvailable = this.getFlightById(flightId).get().getPlacesAvailable();
		if (qta > placesAvailable) { return false; }
		return true;
	}

	@Override
	public boolean addFlightPlaces(long flightId, int places) {
		if (!flightRepository.existsById(flightId)) { return false; }
		
		// Prendo i posti disponibili del volo
		int placesAvailable = this.getPlacesAvailable(flightId);
		
		// Aggiorno i posti disponibili sommandoli a quelli già presenti, se ok torno true
		int updatedRows = flightRepository.updateFlightPlacesAvailable(flightId, placesAvailable + places);
		if (updatedRows < 1) { return false; }
		return true;
	}

	@Override
	public boolean removeFlightPlaces(long flightId, int places) {
		if (!flightRepository.existsById(flightId)) { return false; }
		
		// Se i posti da rimuovere non sono disponibili return false
		if (!this.checkPlacesAvailable(flightId, places)) { return false; }
		
		// Prendo i posti disponibili del volo
		int placesAvailable = this.getPlacesAvailable(flightId);
		
		// Aggiorno i posti disponibili sottraendoli a quelli già presenti, se ok torno true
		int updatedRows = flightRepository.updateFlightPlacesAvailable(flightId, placesAvailable - places);
		if (updatedRows < 1) { return false; }
		return true;
	}
	
	private int getPlacesAvailable(long flightId) {
		if (!flightRepository.existsById(flightId)) { return 0; }
		return flightRepository.findById(flightId).get().getPlacesAvailable();
	}

}
