package com.airport_database.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.airport_database.entity.Flight;
import com.airport_database.services.FlightServices;
@CrossOrigin(origins = {"http://localhost:5173/"})

@RestController
@RequestMapping(path="AirportServices/flights" )
public class FlightController {
	
	@Autowired
	private FlightServices fs;

	
	//rotta per tutti i voli
	@GetMapping(value="/all", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Flight>> getAllFlights(){
		List<Flight> flights= fs.getAllFlight();
		if (flights == null || flights.isEmpty()) {
			return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	//rotta per un volo specifico tramite id
	@GetMapping(value="/{idFlight}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Flight> getFlightById(@PathVariable("idFlight") String idFlight){
		if (!fs.existsById(idFlight)) {
			return new ResponseEntity<Flight>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Flight>(fs.getFlightById(idFlight), HttpStatus.OK);
		
	}
	
	//rotta per i voli compresi tra due date
    @GetMapping(value="/dates", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> getFlightsIdByDates(
            @RequestParam("dateStart") String dateStart,
            @RequestParam("dateEnd") String dateEnd) {
        List<String> flightsIds = fs.getFlightsIdByDates(dateStart, dateEnd);
        if (flightsIds.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		}
        return ResponseEntity.ok(flightsIds);
    }
    
    //rotta per i voli con partenza da uno specifico aereoporto
    @GetMapping(value="/departureFrom", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Flight>> getflightsFrom(@RequestParam("airport") String airport){
		List<Flight> flights = fs.getFlightsByDepartureAirport(airport);
		if (flights == null || flights.isEmpty()) {
			return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
		}
    	return ResponseEntity.ok(flights);
    	
    }
	
    //rotta per i voli con partenza da uno specifico aereoporto
    @GetMapping(value="/departureTo", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Flight>> getflightsTo(@RequestParam("airport") String airport){
		List<Flight> flights = fs.getFlightsByDestinationAirport(airport);
		if (flights == null || flights.isEmpty()) {
			return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
		}
    	return ResponseEntity.ok(flights);
    	
    }
}
