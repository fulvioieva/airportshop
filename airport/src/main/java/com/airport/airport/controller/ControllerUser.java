package com.airport.airport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.airport.dto.UserDTO;
import com.airport.airport.enums.StatusLogging;
import com.airport.airport.mapper.UserMapper;
import com.airport.airport.model.User;
import com.airport.airport.service.ServiceUser;

@RestController
@RequestMapping("airport")
public class ControllerUser {

	@Autowired
	private ServiceUser serviceUser;
	
	
//	LOGIN DELL UTENTE
	
	@PostMapping(value="login", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> login(@RequestParam String idUser, @RequestParam String password){
		
		 StatusLogging status = serviceUser.login(idUser, password);
		
		if(status != StatusLogging.LOGIN_SUCCESFULLY) {
			
			return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
			
		}
		
		User user = serviceUser.getUser(idUser).get();
		
		UserDTO userDTO = UserMapper.convertToUserDTO(user);
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
		
		
	}
	
}
