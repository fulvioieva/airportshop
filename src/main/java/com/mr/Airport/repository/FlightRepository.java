package com.mr.Airport.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mr.Airport.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	/* Query custom per prendermi la lista dei voli che hanno partenza e arrivo uguali a quelli richiesti,
	e hanno anche la data di partenza uguale o successiva a quella richiesta */
	@Query(value = "SELECT * FROM flights "
			+ "WHERE departure_airport = :departureAirport "
			+ "AND arrival_airport = :arrivalAirport "
			+ "AND departure_date = :departureDate "
			+ "AND departure_time >= :departureTime", nativeQuery = true)
	List<Flight> findFlightsByDepartureArrivalAfterDateTime(String departureAirport, String arrivalAirport,
			LocalDate departureDate, LocalTime departureTime);
	
	// Query JPQL per aggiornare i posti disponibili di un volo.
	@Modifying
    @Transactional
    @Query("UPDATE Flight u SET u.placesAvailable = :placesAvailable WHERE u.id = :flightId")
	int updateFlightPlacesAvailable(@Param("flightId") long flightId, @Param("placesAvailable") int placesAvailable);
}



// findFlightsByDepartureArrivalAfterDateTime
/*
 * SELECT *
 * FROM flights
 * WHERE departure_airport = :departureAirport
 * AND arrival_airport = :arrivalAirport
 * AND departure_date = :departureDate
 * AND departure_time >= :departureTime
 * */