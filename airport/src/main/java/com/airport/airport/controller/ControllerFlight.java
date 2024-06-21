package com.airport.airport.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airport.airport.dto.FlightDTO;
import com.airport.airport.mapper.FlightMapper;
import com.airport.airport.model.Flight;
import com.airport.airport.service.ServiceFlight;

@RestController
@RequestMapping("airport")
public class ControllerFlight {

	@Autowired
	private ServiceFlight serviceFlight;
	
//	TUTTI I VOLI
	
	@GetMapping(value="flights", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getAllFlights(){
		
		List<Flight> flights = serviceFlight.getAllFlights();
		
		List<FlightDTO> flightsDTO = new ArrayList<>();
		
		for (Flight flight : flights) {
			
			FlightDTO flightDTO = FlightMapper.convertToFlightDTO(flight);
			
			flightsDTO.add(flightDTO);
			
		}
		
		return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
		
	}
	
//	TUTTI I VOLI DALLA DATA E ORARIO ODIERNI
	
	@GetMapping(value="flights-available", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getFlights(){
		
		List<Flight> flights = serviceFlight.getFlights();
		
		List<FlightDTO> flightsDTO = new ArrayList<>();
		
		for (Flight flight : flights) {
			
			FlightDTO flightDTO = FlightMapper.convertToFlightDTO(flight);
			
			flightsDTO.add(flightDTO);
			
		}
		
		return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
		
	}
	
//	VOLO SINGOLO 
	
	@GetMapping(value="flight/{id_flight}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getFlight(@PathVariable("id_flight") String idFlight){
		
		Optional<Flight> flight = serviceFlight.getFlight(idFlight);
		
		if(flight.isEmpty()) {
			
			return new ResponseEntity<>(flight.get(), HttpStatus.NOT_FOUND);
			
		}
		
		FlightDTO flightDTO = FlightMapper.convertToFlightDTO(flight.get());
		
		return new ResponseEntity<>(flightDTO, HttpStatus.OK);
		
	}
	
//	VOLI FILTRATI IN BASE ALLA RICERCA
	
	@GetMapping(value="flight/{departure_airport}/{destination_airport}/{date_departure}/{time_departure}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getFlights(@PathVariable("departure_airport") String departureAirport, 
										@PathVariable("destination_airport") String destinationAirport,
										@PathVariable("date_departure") LocalDate dateDeparture,
										@PathVariable("time_departure") LocalTime timeDeparture){
		
		List<Flight> flights = serviceFlight.getFlights(departureAirport, destinationAirport, dateDeparture, timeDeparture);
		
		if(flights.isEmpty()) {
			
			return new ResponseEntity<>(flights, HttpStatus.NOT_FOUND);
			
		}
		
		List<FlightDTO> flightsDTO = new ArrayList<>();
		
		for (Flight flight : flights) {
			
			FlightDTO flightDTO = FlightMapper.convertToFlightDTO(flight);
			
			flightsDTO.add(flightDTO);
			
		}
		
		return new ResponseEntity<>(flightsDTO, HttpStatus.OK);
		
	}
}
