package com.airport.airport.interfaces;


import java.util.Optional;

import com.airport.airport.enums.StatusLogging;
import com.airport.airport.model.User;

public interface UserFunctions {

	StatusLogging login(String idUser, String password);
	
	Optional<User> getUser(String idUser);
	
}
