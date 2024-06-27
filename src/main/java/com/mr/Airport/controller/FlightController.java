package com.mr.Airport.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mr.Airport.apiresponse.ApiResponse;
import com.mr.Airport.entity.Flight;
import com.mr.Airport.interfaces.FlightFunctions;

@RestController
@RequestMapping(path= "Airport")
public class FlightController {
	
	@Autowired
	private FlightFunctions flightService;
	
	
	// READ All Flights
	@GetMapping(value= "flights", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<List<Flight>>> getAllFlights() {
		
		List<Flight> allFlights = flightService.getAllFlights();
		if (allFlights.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(allFlights, "No Content", HttpStatus.OK.value()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse<>(allFlights, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// READ Flight by Id
	@GetMapping(value= "flights/{flightId}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Optional<Flight>>> getFlightById(@PathVariable("flightId") long flightId) {
		
		Optional<Flight> flight = flightService.getFlightById(flightId);
		if (flight.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(null, "Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ApiResponse<>(flight, "Success", HttpStatus.OK.value()), HttpStatus.OK);
		
	}
	
	
	
	// TODO Rendi i parametri opzionali, in pratica in base a quelli che ti arrivano, fai una if e richiami i metodi precisi,
	// ad esempio potrebbero essere questi: ricerca tramite aeroporto di partenza, solo di arrivo, partenza e arrivo e fanno vedere
	// tutti quelli che partono da quel giorno in poi, quei dati con solo il giorno mostra tutti i voli disponibili per quelle dest.
	// in quel giorno, solo data mostri tutti i voli disponibili in quel giorno ecc...
	// TODO Inserisci anche la paginazione per non avere troppi voli quando si seleziona solo 1 informazione
	// READ Flight by Departure, Arrival, Dep date, Dep time
	@GetMapping(value= "flights/filter", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<List<Flight>>> getFlightsByDepartureArrivalDatesTimes(@RequestParam String departureAirport,
																							@RequestParam String arrivalAirport,
																							@RequestParam LocalDate departureDate,
																							@RequestParam LocalTime departureTime) {
		
		List<Flight> filteredFlights = flightService.getFlightsByDepartureArrivalDatesTimes(departureAirport, arrivalAirport, departureDate, departureTime);
		if (filteredFlights.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(filteredFlights, "No flights found that match these filters", HttpStatus.OK.value()), HttpStatus.OK); // Se metti NO_CONTENT, in automatico non ritorna l'oggetto response
		}
		return new ResponseEntity<>(new ApiResponse<>(filteredFlights, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// CHECK Flight Places Availability
	@GetMapping(value= "flights/{flightId}/checkplaces/{qta}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> checkPlacesAvailable(@PathVariable("flightId") long flightId,
																	 @PathVariable("qta") int qta) {
		
		boolean available = false;
		try {
			available = flightService.checkPlacesAvailable(flightId, qta);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(available, "Invalid quantity", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		if (!available) {
			return new ResponseEntity<>(new ApiResponse<>(available, "Selected Quantity Not Available", HttpStatus.OK.value()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse<>(available, "Success", HttpStatus.OK.value()), HttpStatus.OK);
		
	}
	
}
