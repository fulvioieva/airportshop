package com.mr.Airport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mr.Airport.entity.Ticket;
import com.mr.Airport.entity.User;
import com.mr.Airport.interfaces.UserFunctions;


@RestController
@RequestMapping(path= "Airport")
public class UserController {
	
	@Autowired
	private UserFunctions userService;  // DEPENDENCY INJECTION di UserService (mettiamo come tipo quello dell'interfaccia per generalizzare, diciamo che è una classe che implementa quell'interfaccia.
	
	
	// EXIST User
	@GetMapping(value= "user/{userId}/tickets", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable("userId") Long userId) {
		
		List<Ticket> userTickets = new ArrayList<Ticket>();
		try {
			userTickets = userService.getTicketsByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Ticket>>(userTickets, HttpStatus.BAD_REQUEST);
		}
		if (userTickets.isEmpty()) {
			return new ResponseEntity<List<Ticket>>(userTickets, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Ticket>>(userTickets, HttpStatus.OK);
	}
		
	
	
	// EXIST User
	@GetMapping(value= "user/check/{userId}", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> ifExistUser(@PathVariable("userId") Long userId) {
		
		boolean existUser = userService.ifExistUser(userId);
		if (!existUser) {
			return new ResponseEntity<Boolean>(existUser, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(existUser, HttpStatus.OK);
	}
	
	
	
	// PATCH Login
	/* Siccome questa richiesta HTTP contiene dati sensibili come la password,
	viene utilizzato il metodo POST e dati vengono impostati come parametri
	della richiesta invece di essere inseriti come URI. */
	@PatchMapping(value= "login", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> login(@RequestParam String clientCode, @RequestParam String password) {
		
		boolean logingStatus = userService.login(clientCode, password);
		if (!logingStatus) {
			return new ResponseEntity<Boolean>(logingStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(logingStatus, HttpStatus.OK);
	}
	
	
	
	// PATCH Logout
	@PatchMapping(value= "logout", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> logout(@RequestParam String clientCode) {
		
		boolean logingStatus = userService.logout(clientCode);
		if (!logingStatus) {
			return new ResponseEntity<Boolean>(logingStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(logingStatus, HttpStatus.OK);
	}
	
	
	
	// POST Signin
	@PostMapping(value= "signin", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> signin(@RequestParam String name, @RequestParam String surname,
										  @RequestParam String email, @RequestParam String password) {
		
		boolean signStatus = false;
		try {
			signStatus = userService.signin(name, surname, email, password);
		} catch (Exception e) {
//			e.printStackTrace();
			return new ResponseEntity<Boolean>(signStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(signStatus, HttpStatus.OK);
	}
	
	
	
	// DELETE Signout
	@DeleteMapping(value= "signout/{clientCode}", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> signout(@PathVariable("clientCode") String clientCode) {
		
		boolean signStatus = userService.signout(clientCode);
		if (!signStatus) {
			return new ResponseEntity<Boolean>(signStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(signStatus, HttpStatus.OK);
	}
	
	
	
	// SHOW User by Id
	@GetMapping(value= "user/id", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Optional<User>> getUserById(@RequestParam("userId") Long userId) {
		
		Optional<User> user = userService.getUserById(userId);
		if (user.isEmpty()) {
			return new ResponseEntity<Optional<User>>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
	}
	
	
	
	// SHOW User by clientCode
	@GetMapping(value= "user/clientcode", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Optional<User>> getUserByClientCode(@RequestParam("clientCode") String clientCode) {
		
		Optional<User> user = userService.getUserByClientCode(clientCode);
		if (user.isEmpty()) {
			return new ResponseEntity<Optional<User>>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value= "clientcode/{userId}", produces= { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Boolean> generateClientCode(@PathVariable("userId") Long userId) {
		
		boolean result = false;
		try {
			result = userService.generateClientCode(userId);
		} catch (Exception e) {
//			e.printStackTrace();
			return new ResponseEntity<Boolean>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	
	
	
}
