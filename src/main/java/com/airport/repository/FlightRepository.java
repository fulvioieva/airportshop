package com.airport.repository;

import com.airport.model.Flight;
import com.airport.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    @Query("SELECT f FROM Flight f WHERE (f.dateFlight > CURRENT_DATE) OR (f.dateFlight = CURRENT_DATE AND f.timeFlight >= CURRENT_TIME)")
    List<Flight> findAllFromNow();
}
