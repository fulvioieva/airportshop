package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.entity.UserLogged;
import com.example.demo.service.AdministrationFunctions;
import com.example.demo.service.ServiceSystem;
import com.example.demo.utils.JwtUtils;

import come.example.demo.enums.StatusLogging;
import come.example.demo.enums.StatusRegistration;
import come.example.demo.enums.UserRole;

@RestController
@RequestMapping(path = "Index")
public class ControllerSystem {
	@Autowired
	private ServiceSystem serviceSystem;
	
	@Autowired
	private AdministrationFunctions administrationFunctions;

	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping(value = "login/{idClient}/{password}/{role}")
	public ResponseEntity<?> login(@PathVariable("idClient") String idClient,
			@PathVariable("password") String password, @PathVariable("role") String role) {
		UserRole userRole;
		try {
			userRole = UserRole.valueOf(role.toUpperCase());
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(StatusLogging.WRONG_ROLE, HttpStatus.BAD_REQUEST);
		}
		StatusLogging log = serviceSystem.login(idClient, password, userRole);
		if (log.equals(StatusLogging.LOGIN_SUCCESFULLY)) {
			return new ResponseEntity<>(jwtUtils.generateAccessToken(administrationFunctions.getClient(idClient).get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(log, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "logoff/{idClient}")
	public ResponseEntity<StatusLogging> logoff(@PathVariable("idClient") String idClient) {
		StatusLogging log = serviceSystem.logoff(idClient);
		return log.equals(StatusLogging.LOGIN_OFF_SUCCESFULLY) ? new ResponseEntity<>(log, HttpStatus.OK)
				: new ResponseEntity<>(log, HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "signup")
	public ResponseEntity<StatusRegistration> signup(@RequestBody Client client) {
		StatusRegistration reg = serviceSystem.signup(client);
		return reg.equals(StatusRegistration.SIGNED_UP_SUCCESFULLY) ? new ResponseEntity<>(reg, HttpStatus.OK)
				: new ResponseEntity<>(reg, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "signoff/{idClient}")
	public ResponseEntity<StatusRegistration> signoff(@PathVariable("idClient") String idClient) {
		StatusRegistration reg = serviceSystem.signoff(idClient);
		return reg.equals(StatusRegistration.SIGNED_OFF_SUCCESFULLY) ? new ResponseEntity<>(reg, HttpStatus.OK)
				: new ResponseEntity<>(reg, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "isUserLoggedOn/{idClient}")
	public ResponseEntity<String> isUserLoggedOn(@PathVariable("idClient") String idClient) {
		return serviceSystem.isUserLoggedOn(idClient) ? new ResponseEntity<>("Esiste", HttpStatus.OK)
				: new ResponseEntity<>("Non esiste", HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getUserLoggedOn/{idUser}")
	public ResponseEntity<UserLogged> getUserLoggedOn(@PathVariable("idUser") String idUser) {
		return serviceSystem.getUserLoggedOn(idUser).isPresent()
				? new ResponseEntity<>(serviceSystem.getUserLoggedOn(idUser).get(), HttpStatus.OK)
				: new ResponseEntity<>(new UserLogged(), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "deleteUserLoggedOn/{idUserLogged}")
	public ResponseEntity<String> deleteUserLoggedOn(@PathVariable("idUserLogged") String idUserLogged) {
		return serviceSystem.deleteUserLoggedOn(idUserLogged) ? new ResponseEntity<>("Eliminato", HttpStatus.OK)
				: new ResponseEntity<>("Non trovato", HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getAllUsersLoggedOn")
	public ResponseEntity<List<UserLogged>> getAllUsersLoggedOn() {
		return !serviceSystem.getAllUsersLoggedOn().isEmpty()
				? new ResponseEntity<>(serviceSystem.getAllUsersLoggedOn(), HttpStatus.OK)
				: new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
	}

}
