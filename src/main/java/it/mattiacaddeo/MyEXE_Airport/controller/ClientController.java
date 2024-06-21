package it.mattiacaddeo.MyEXE_Airport.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.mattiacaddeo.MyEXE_Airport.models.Client;
import it.mattiacaddeo.MyEXE_Airport.models.Reservation;
import it.mattiacaddeo.MyEXE_Airport.service.ClientService;

@RestController
@RequestMapping("/airport")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping(value = "/clients", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Client>> getClients() {
		return new ResponseEntity<List<Client>>(clientService.getClients(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/client/{idClient}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getClientById(@PathVariable String idClient) {
		Optional<Client> existingClient = clientService.getClient(idClient);
		if (existingClient.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Optional<Client>>(existingClient, HttpStatus.OK);
	}
	
	@GetMapping(value = "/client/reservations/{idClient}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getClientReservations(@PathVariable String idClient) {
		Optional<Client> existingClient = clientService.getClient(idClient);
		if (existingClient.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Reservation>>(
				existingClient
					.get()
					.getReservations(), 
				HttpStatus.OK);
	}
	
	@PostMapping(value = "/client", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> postClient(@RequestBody Client client) {
		Optional<Client> newClient = clientService.insertClient(client);
		if (newClient.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Client>(newClient.get(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/client/{idClient}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> deleteClientById(@PathVariable String idClient) {
		if (clientService.deleteClient(idClient)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/client", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> putClient(@RequestBody Client client) {
		if (clientService.updateClient(client)) {
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Id not present or no update to do.", HttpStatus.BAD_REQUEST);
	}

}
