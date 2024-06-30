package com.mr.Airport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mr.Airport.apiresponse.ApiResponse;
import com.mr.Airport.entity.Ticket;
import com.mr.Airport.entity.User;
import com.mr.Airport.interfaces.UserFunctions;
@CrossOrigin(origins = {"http://localhost:5173/"})

@RestController
@RequestMapping(path= "Airport")
public class UserController {
	
	@Autowired
	private UserFunctions userService;  // DEPENDENCY INJECTION di UserService (mettiamo come tipo quello dell'interfaccia per generalizzare, diciamo che è una classe che implementa quell'interfaccia.
	

	// GET User Tickets
	@GetMapping(value= "users/{userId}/tickets", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<List<Ticket>>> getTicketsByUserId(@PathVariable("userId") long userId) {
		
		List<Ticket> userTickets = new ArrayList<Ticket>();
		try {
			userTickets = userService.getTicketsByUserId(userId);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(userTickets, "User Not Found", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		if (userTickets.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(userTickets, "No Tickets", HttpStatus.OK.value()), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse<>(userTickets, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
		
	
	
	// EXIST User
	@GetMapping(value= "users/{userId}/check", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> ifExistUser(@PathVariable("userId") long userId) {
		
		boolean existUser = userService.ifExistUser(userId);
		if (!existUser) {
			return new ResponseEntity<>(new ApiResponse<>(existUser, "User Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ApiResponse<>(existUser, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// PATCH Login
	/* Siccome questa richiesta HTTP contiene dati sensibili come la password,
	viene utilizzato il metodo POST e dati vengono impostati come parametri
	della richiesta invece di essere inseriti come URI. */
	@PatchMapping(value= "login", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> login(@RequestParam String clientCode, @RequestParam String password) {
		
		boolean logingStatus = userService.login(clientCode, password);
		if (!logingStatus) {
			return new ResponseEntity<>(new ApiResponse<>(logingStatus, "Wrong client code or password", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(logingStatus, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// PATCH Logout
	@PatchMapping(value= "logout", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> logout(@RequestParam String clientCode) {
		
		boolean logingStatus = false;
		try {
			logingStatus = userService.logout(clientCode);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(logingStatus, "Client Code Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		if (!logingStatus) {
			return new ResponseEntity<>(new ApiResponse<>(logingStatus, "Alredy Logged Out", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(logingStatus, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// POST Signin
	@PostMapping(value= "signin", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> signin(@RequestParam String name,
													   @RequestParam String surname,
													   @RequestParam String email,
										 		   	   @RequestParam String password) {
		
		boolean signStatus = false;
		try {
			signStatus = userService.signin(name, surname, email, password);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(signStatus, "Client Code not created, client does not exist", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		if (!signStatus) {
			return new ResponseEntity<>(new ApiResponse<>(signStatus, "Email Already Exists", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(signStatus, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// DELETE Signout
	@DeleteMapping(value= "signout", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> signout(@RequestParam("clientCode") String clientCode) {
		
		boolean signStatus = false;
		try {
			signStatus = userService.signout(clientCode);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(signStatus, "User Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		if (!signStatus) {
			return new ResponseEntity<>(new ApiResponse<>(signStatus, "User Not Signed Out", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ApiResponse<>(signStatus, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// SHOW User by Id
	@GetMapping(value= "users/{userId}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable("userId") long userId) {
		
		Optional<User> user = userService.getUserById(userId);
		if (user.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(null, "User Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ApiResponse<>(user.get(), "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	
	// SHOW User by clientCode
	@GetMapping(value= "users/{clientCode}/clientcode", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<User>> getUserByClientCode(@PathVariable("clientCode") String clientCode) {
		
		Optional<User> user = userService.getUserByClientCode(clientCode);
		if (user.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(null, "User Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ApiResponse<>(user.get(), "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	
	// POST ClientCode Generator
	@PostMapping(value= "clientcode/generate/{userId}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ApiResponse<Boolean>> generateClientCode(@PathVariable("userId") long userId) {
		
		boolean result = false;
		try {
			result = userService.generateClientCode(userId);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(result, "User Not Found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ApiResponse<>(result, "Success", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
}
