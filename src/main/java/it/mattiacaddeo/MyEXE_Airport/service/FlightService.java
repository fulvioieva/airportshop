package it.mattiacaddeo.MyEXE_Airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mattiacaddeo.MyEXE_Airport.models.Flight;
import it.mattiacaddeo.MyEXE_Airport.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	FlightRepository flightRepo;
	
	public List<Flight> getFlights() {
		return flightRepo.findAll();
	}
	
	public Optional<Flight> getFlight(String idFlight) {
		return flightRepo.findById(idFlight);
	}
	
	public Optional<Flight> insertFlight(Flight flight) {
		Optional<Flight> existingFlight = flightRepo.findById(flight.getIdFlight());
		if (existingFlight.isPresent()) {
			return Optional.empty();
		}
		flightRepo.save(flight);
		return Optional.ofNullable(flight);
	}
	
	public boolean updateFlight(Flight flight) {
		Optional<Flight> existingFlight = flightRepo.findById(flight.getIdFlight());
		if (existingFlight.isEmpty()) {
			return false;
		}
		if (flight.equals(existingFlight.get())) {
			return false;
		}
		flightRepo.save(flight);
		return true;
	}
	
	public boolean deleteFlight(String idFlight) {
		if (flightRepo.existsById(idFlight)) {
			flightRepo.deleteById(idFlight);
			return true;
		}
		return false;
	}

}
