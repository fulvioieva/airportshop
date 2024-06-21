package com.airport_database.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airport_database.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String>{

	@Query("SELECT f.idFlight FROM Flight f WHERE f.dateDeparture BETWEEN :start AND :end")
	List<String> findByDatesBetween(@Param("start") LocalDateTime start,  @Param("end") LocalDateTime end);

	List<Flight> findByDepartureAirport(String departureAirport);
	
	List<Flight> findByDestinationAirport(String destinationAirport);
	
}
