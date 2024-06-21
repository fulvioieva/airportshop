package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.entity.Reservation;


public interface ReservationRepository extends CrudRepository<Reservation, Integer>{

}
