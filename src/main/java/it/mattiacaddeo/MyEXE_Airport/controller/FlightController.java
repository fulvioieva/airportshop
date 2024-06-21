package it.mattiacaddeo.MyEXE_Airport.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.mattiacaddeo.MyEXE_Airport.models.Flight;
import it.mattiacaddeo.MyEXE_Airport.service.FlightService;

@RestController
@RequestMapping("/airport")
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	@GetMapping(value = "/flights", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Flight>> getFlights() {
		return new ResponseEntity<List<Flight>>(flightService.getFlights(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/flight/{idFlight}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getFlightById(@PathVariable String idFlight) {
		Optional<Flight> existingFlight = flightService.getFlight(idFlight);
		if (existingFlight.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Optional<Flight>>(existingFlight, HttpStatus.OK);
	}
	
	@PostMapping(value = "/flight", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> postFlight(@RequestBody Flight flight) {
		Optional<Flight> newFlight = flightService.insertFlight(flight);
		if (newFlight.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Flight>(newFlight.get(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/flight/{idFlight}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> deleteFlightById(@PathVariable String idFlight) {
		if (flightService.deleteFlight(idFlight)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/flight", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> putFlight(@RequestBody Flight flight) {
		if (flightService.updateFlight(flight)) {
			return new ResponseEntity<Flight>(flight, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Id not present or no update to do.", HttpStatus.BAD_REQUEST);
	}

}
