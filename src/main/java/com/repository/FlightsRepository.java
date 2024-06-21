package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.entity.Flight;


public interface FlightsRepository extends CrudRepository<Flight, Integer>{

}
