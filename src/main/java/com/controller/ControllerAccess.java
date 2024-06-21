package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enums.StatusAccess;
import com.service.AccessFunctions;

@RestController
@RequestMapping(path = "Index")
public class ControllerAccess {

	
	@Autowired
	private AccessFunctions accessFunctions;
	
	@PostMapping(value = "access/{client_id}/{password}/{role}")
	public ResponseEntity<?> login(@PathVariable("client_id") String client_id,
			@PathVariable("password") String password) {
		StatusAccess log = accessFunctions.accessOn(client_id, password);
		if (log.equals(StatusAccess.ACCESS_SUCCESFULLY)) {
			return new ResponseEntity<>(log, HttpStatus.OK);
		}
		return new ResponseEntity<>(log, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
