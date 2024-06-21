package com.airport.airport.service;


import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.airport.enums.StatusLogging;
import com.airport.airport.interfaces.UserFunctions;
import com.airport.airport.model.User;
import com.airport.airport.repository.UserRepository;

@Service
public class ServiceUser implements UserFunctions {

	@Autowired
	private UserRepository userRepository;

	@Override
	public StatusLogging login(String idUser, String password) {
		
		User user = getUser(idUser).get();
		
		if(user == null) {
			
			return StatusLogging.WRONG_MAIL;
			
		}
		
		if(!user.getPassword().equals(password)) {
			
			return StatusLogging.WRONG_PASSWORD;
			
		}
		
		return StatusLogging.LOGIN_SUCCESFULLY;
	}

	@Override
	public Optional<User> getUser(String idUser) {
		
		return userRepository.findById(idUser);
		
	}
	

}
