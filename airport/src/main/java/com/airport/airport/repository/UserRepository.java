package com.airport.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.airport.model.User;



public interface UserRepository extends JpaRepository<User, String> {

	
}
