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

import it.mattiacaddeo.MyEXE_Airport.models.Reservation;
import it.mattiacaddeo.MyEXE_Airport.service.ReservationService;

@RestController
@RequestMapping("/airport")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping(value = "/reservations", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Reservation>> getReservations() {
		return new ResponseEntity<List<Reservation>>(reservationService.getReservations(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/reservation/{idReservation}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getReservationById(@PathVariable Integer idReservation) {
		Optional<Reservation> existingReservation = reservationService.getReservation(idReservation);
		if (existingReservation.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Optional<Reservation>>(existingReservation, HttpStatus.OK);
	}
	
	@PostMapping(value = "/reservation", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> postReservation(@RequestBody Reservation reservation) {
		Optional<Reservation> newReservation = reservationService.insertReservation(reservation);
		if (newReservation.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Reservation>(newReservation.get(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/reservation/{idReservation}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> deleteReservationById(@PathVariable Integer idReservation) {
		if (reservationService.deleteReservation(idReservation)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/reservation", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> putReservation(@RequestBody Reservation reservation) {
		if (reservationService.updateReservation(reservation)) {
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Id not present or no update to do.", HttpStatus.BAD_REQUEST);
	}

}
