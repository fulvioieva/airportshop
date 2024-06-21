package com.airport.airport.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.airport.airport.model.Flight;



public interface FlightRepository extends JpaRepository<Flight, String> {

	public List<Flight> findByDepartureAirportAndDestinationAirportAndDateDepartureAndTimeDeparture(String departureAirport, String destinationAirport,
			LocalDate dateDeparture, LocalTime timeDeparture);
	
	@Query("SELECT f FROM Flight f WHERE (f.dateDeparture > CURRENT_DATE) OR (f.dateDeparture = CURRENT_DATE AND f.timeDeparture >= CURRENT_TIME)")
    List<Flight> findAllFromNow();



	
	
}
