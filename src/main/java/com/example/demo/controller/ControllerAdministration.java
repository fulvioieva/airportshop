package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.service.AdministrationFunctions;

@RestController
@RequestMapping(path = "admin")
public class ControllerAdministration {
	@Autowired
	private AdministrationFunctions administrationFunctions;

	@PostMapping(value = "addClient")
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		return administrationFunctions.addClient(client) ? new ResponseEntity<>("Insert!", HttpStatus.OK)
				: new ResponseEntity<>("false", HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping(value = "getClientById/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
		return !administrationFunctions.getClient(id).isEmpty()
				? new ResponseEntity<>(administrationFunctions.getClient(id).get(), HttpStatus.OK)
				: new ResponseEntity<>(new Client(), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "deleteClientById/{id}")
	public ResponseEntity<String> deleteClientById(@PathVariable("id") String id) {
		return !administrationFunctions.deleteClient(id) ? new ResponseEntity<>("Eliminato", HttpStatus.OK)
				: new ResponseEntity<>("Non trovato", HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "updateClient")
	public ResponseEntity<String> updateArticle(@RequestBody Client client) {
		return administrationFunctions.updateClient(client) ? new ResponseEntity<String>("Aggiornato", HttpStatus.OK)
				: new ResponseEntity<String>("Non aggiornato", HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "getAllClient")
	public ResponseEntity<List<Client>> getAllClient() {
		return !administrationFunctions.getAllClient().isEmpty()
				? new ResponseEntity<>(administrationFunctions.getAllClient(), HttpStatus.OK)
				: new ResponseEntity<>(administrationFunctions.getAllClient(), HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "getAllClientByDate/{dtS}/{dtE}")
	public ResponseEntity<List<Client>> getAllClientByDate(@PathVariable("dtS") String dtSignupStart,
			@PathVariable("dtE") String dtSignupEnd) {
		return !administrationFunctions.getAllClient(dtSignupStart, dtSignupEnd).isEmpty()
				? new ResponseEntity<>(administrationFunctions.getAllClient(dtSignupStart, dtSignupEnd),
						HttpStatus.OK)
				: new ResponseEntity<>(administrationFunctions.getAllClient(dtSignupStart, dtSignupEnd),
						HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "surnameLike/{surnameLike}")
	public ResponseEntity<List<Client>> surnameLike(@PathVariable("surnameLike") String surnameLike) {
		return !administrationFunctions.getAllClient(surnameLike).isEmpty()
				? new ResponseEntity<>(administrationFunctions.getAllClient(surnameLike), HttpStatus.OK)
				: new ResponseEntity<>(administrationFunctions.getAllClient(surnameLike),
						HttpStatus.NO_CONTENT);
	}

}
