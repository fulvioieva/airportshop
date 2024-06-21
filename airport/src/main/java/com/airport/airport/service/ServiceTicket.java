package com.airport.airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airport.airport.enums.StatusOrder;
import com.airport.airport.enums.TypePayment;
import com.airport.airport.interfaces.TicketFunctions;
import com.airport.airport.model.Flight;
import com.airport.airport.model.Ticket;
import com.airport.airport.model.User;
import com.airport.airport.repository.TicketRepository;

@Service 
public class ServiceTicket implements TicketFunctions{
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private ServiceFlight serviceFlight;

	@Override
	@Transactional
	public StatusOrder addTicket(String idUser, String idFlight, int ticketsQty, TypePayment typePayment) {
		
//		CONTROLLO POSTI DISPONIBILI
		
		if(!serviceFlight.checkAvailablePlaces(idFlight, ticketsQty)) {
			
			return StatusOrder.QTY_NOT_AVAILABLE;
			
		}
		
		
		Flight flight = serviceFlight.getFlight(idFlight).get();
		
		User user = serviceUser.getUser(idUser).get();
		
//		CONTROLLO SE ESISTE GIA UNA PRENOTAZIONE DI QUESTO VOLO PER QUESTO UTENTE IN TAL CASO AGGIORNO SOLO LA QUANTITA E IL PREZZO TOTALE
		
		if(existTicket(idUser, idFlight)) {
			
			Ticket ticketToUpdate = getTicket(idUser, idFlight).get();
			
			ticketToUpdate.setTicketsQty(ticketToUpdate.getTicketsQty() + ticketsQty);
			ticketToUpdate.setTotalPrice(ticketToUpdate.getTotalPrice() + flight.getPrice() * ticketsQty);
			ticketRepository.save(ticketToUpdate);
			
//			AGGIORNO ANCHE I POSTI DISPONIBILI DEL VOLO
			
			serviceFlight.updateAvailablePlaces(idFlight, ticketsQty);
			
			return StatusOrder.UPDATED_SUCCESSFULY;
			
		}else {
			
//			IN CASO CONTRARIO NE CREO UNA NUOVA
			
			Ticket newTicket = new Ticket();
			
			newTicket.setUser(user);
			newTicket.setFlight(flight);
			newTicket.setTicketsQty(ticketsQty);
			newTicket.setTotalPrice(flight.getPrice() * ticketsQty);
			newTicket.setTypePayment(typePayment);
			
			ticketRepository.save(newTicket);
			
//			AGGIORNO ANCHE I POSTI DISPONIBILI DEL VOLO
			
			serviceFlight.updateAvailablePlaces(idFlight, ticketsQty);
			
			return StatusOrder.ADDED_SUCCESSFULY;
			
		}
		
		
	}

	@Override
	public boolean existTicket(String idUser, String idFlight) {
		
		return getTicket(idUser, idFlight).isPresent();
		
	}

	@Override
	public Optional<Ticket> getTicket(String idUser, String idFlight) {

		return ticketRepository.findByUserIdUserAndFlightIdFlight(idUser, idFlight);
		
	}

	@Override
	public List<Ticket> getTickets(String idUser) {
		
		return ticketRepository.findByUserIdUser(idUser);
		
	}




}
