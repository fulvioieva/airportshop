package it.mattiacaddeo.MyEXE_Airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mattiacaddeo.MyEXE_Airport.models.Reservation;
import it.mattiacaddeo.MyEXE_Airport.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepo;
	
	public List<Reservation> getReservations() {
		return reservationRepo.findAll();
	}
	
	public Optional<Reservation> getReservation(Integer idReservation) {
		return reservationRepo.findById(idReservation);
	}
	
	public Optional<Reservation> insertReservation(Reservation reservation) {
		Optional<Reservation> existingReservation = reservationRepo.findById(reservation.getIdReservation());
		if (existingReservation.isPresent()) {
			return Optional.empty();
		}
		reservationRepo.save(reservation);
		return Optional.ofNullable(reservation);
	}
	
	public boolean updateReservation(Reservation reservation) {
		Optional<Reservation> existingReservation = reservationRepo.findById(reservation.getIdReservation());
		if (existingReservation.isEmpty()) {
			return false;
		}
		if (reservation.equals(existingReservation.get())) {
			return false;
		}
		reservationRepo.save(reservation);
		return true;
	}
	
	public boolean deleteReservation(Integer idReservation) {
		if (reservationRepo.existsById(idReservation)) {
			reservationRepo.deleteById(idReservation);
			return true;
		}
		return false;
	}

}
