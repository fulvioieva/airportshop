package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	List<Flight> findByDestinationLike(String city);
	List<Flight> findByOriginLike(String city);
	List<Flight> findByDateBetween(Date date, Date date2);
}
