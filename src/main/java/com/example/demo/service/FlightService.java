package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Flight;
import com.example.demo.repository.FlightRepository;

@Service
public class FlightService implements IFlightService {
	
	@Autowired
	FlightRepository flightRepository;

	String[] valoriNonAmmessi = {".","-","_"};
	
	@Override
	public boolean addFlight(Flight flight) {
		List<String> listName = new ArrayList<>();
		List<Flight> listFlights = flightRepository.findAll();
		for (Flight flight2 : listFlights) {
			listName.add(flight2.getName());
		}
		if (listName.contains(flight.getName())) {
			return false;
		}
		for (String x : flight.getName().trim().split("")) {
			if (Arrays.asList(valoriNonAmmessi).contains(x)) {
				return false;
			}
		}
		flightRepository.save(flight);
		return false;
	}

	@Override
	public boolean updateFlight(Flight flight) {
		if (flightRepository.findById(flight.getId_flight()).isEmpty()) {
			return false;
		}
		for (String x : flight.getName().trim().split("")) {
			if (Arrays.asList(valoriNonAmmessi).contains(x)) {
				return false;
			}
		}
		flightRepository.save(flight);
		return true;
	}

	@Override
	public List<Flight> getAllFlight() {
		List<Flight> listFlight = new ArrayList<Flight>();
		listFlight = flightRepository.findAll();
		if (listFlight.isEmpty()) {
			throw new NullPointerException();
		}
		return listFlight;
	}

	@Override
	public Optional<Flight> getFlight(int idFlight) {
		return flightRepository.findById(idFlight);
	}

	@Override
	public boolean deleteFlight(int idFlight) {
		Optional<Flight> flight = flightRepository.findById(idFlight);
		if (flight.isEmpty()) {
			return false;
		}
		flightRepository.deleteById(idFlight);
		return true;
	}

	@Override
	public List<Flight> getFlightsOrigin(String city) {
		List<Flight> listFlight = new ArrayList<Flight>();
		listFlight = flightRepository.findByOriginLike(city);
		if (listFlight.isEmpty()) {
			throw new NullPointerException();
		}
		return listFlight;
	}

	@Override
	public List<Flight> getFlightBetween(Date date, Date date2) {
		List<Flight> listFlight = new ArrayList<Flight>();
		listFlight = flightRepository.findByDateBetween(date,date2);
		if (listFlight.isEmpty()) {
			throw new NullPointerException();
		}
		return listFlight;
	}

	@Override
	public List<Flight> getFlightsDestination(String city) {
		List<Flight> listFlight = new ArrayList<Flight>();
		listFlight = flightRepository.findByDestinationLike(city);
		if (listFlight.isEmpty()) {
			throw new NullPointerException();
		}
		return listFlight;
	}

}
