package com.airport_database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport_database.entity.User;
import com.airport_database.services.UserServices;
@CrossOrigin(origins = {"http://localhost:5173/"})


@RestController
@RequestMapping(path="AirportServices/users")
public class UserController {

	@Autowired
	private UserServices usSer;
	
	//rotta per tutti gli user
	
	@GetMapping(value="/all", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> getAll(){
	List<User> users=usSer.getAllUsers();
	if (users.isEmpty()||users==null) {
		return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);

	}
	return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping(value="/{idUser}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getById(@PathVariable("idUser") String idUser ){
		if (usSer.existsById(idUser)) {
			return ResponseEntity.ofNullable(usSer.getUserById(idUser));			
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<?> login(@RequestParam("idUser") String idUser, 
									@RequestParam("password") String password) {
		
	    User user = usSer.getUserById(idUser);
	    if (user != null && password.equals(user.getPassword())) {
	        // Autenticazione riuscita, restituisci un token o una conferma
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        // Autenticazione fallita
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	}
}
