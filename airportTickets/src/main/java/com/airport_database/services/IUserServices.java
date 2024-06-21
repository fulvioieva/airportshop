package com.airport_database.services;

import java.util.List;

import com.airport_database.entity.User;

public interface IUserServices {
	
	List<User> getAllUsers();
	
	boolean existsById(String userId);
	
	User getUserById(String userId);
}
