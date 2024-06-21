package com.airport_database.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airport_database.entity.Flight;
import com.airport_database.entity.Ticket;
import com.airport_database.enums.TypePayment;
import com.airport_database.repository.FlightRepository;
import com.airport_database.repository.TicketRepository;
import com.airport_database.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class TicketServices implements ITicketServices {

	@Autowired
	private TicketRepository tRepo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private FlightRepository fRepo;
	
	@Override
	public List<Ticket> getAllTickets() {
		return tRepo.findAll();
	}

	@Override
	public boolean existsById(int idTicket) {
		return tRepo.existsById(idTicket);
	}

	@Override
	public Ticket getTicketById(int idTicket) {
		return tRepo.findById(idTicket).get();
	}

	@Override
	public List<Ticket> getTicketsByFlightId(String idFlight) {
		return tRepo.findByFlightId(idFlight);
	}

	@Override
	public List<Ticket> getTicketsByUserId(String idUser) {
		return tRepo.findByUserId(idUser);
	}

	@Override
	public Ticket addTicket(String idFlight, String idUser, int qty, TypePayment pay) throws Throwable {

 		if (fRepo.existsById(idFlight)&&uRepo.existsById(idUser)) {
			
			//verifico la presenza di posti liberi
			Flight flight=fRepo.findById(idFlight).get();

			if (qty>flight.getAvailablePlaces()) {
				throw new Exception("Non ci sono abbastanza posti liberi disponibili");
			}
			
			//creo un nuovo biglietto
			Ticket ticket= new Ticket();
			ticket.setFlightId(idFlight);
			ticket.setUserId(idUser);
			BigDecimal sPrice=fRepo.findById(idFlight).get().getPrice();
			ticket.setTotalPrice(sPrice.multiply(BigDecimal.valueOf(qty)));
			ticket.setPaymentType(pay);
			
			//modifico la quantità odi biglietti totali del volo
			flight.setAvailablePlaces(flight.getAvailablePlaces()-qty);
			
			fRepo.save(flight);
			tRepo.save(ticket);

			return ticket;
			
		}
		throw new Exception("Impossibile creare il biglietto: volo o utente non trovato.");
	}

	@Override
	@Transactional
	public boolean updateTicketQuantity(int idTicket, int newQty) throws Exception {
		if (!tRepo.existsById(idTicket)||tRepo.findById(idTicket)==null) {
			
			throw new Exception("Impossibile, biglietto non trovato.");
			}
		
		Ticket ticket=tRepo.findById(idTicket).get();
		//recupero il volo per modificarne i posti liberi
		Flight refFlight=fRepo.findById(ticket.getFlightId()).get();
		BigDecimal sPrice=refFlight.getPrice();
		int qtyAvailable=refFlight.getAvailablePlaces();
		
		int oldQty=ticket.getTicketsQty();
		int diff;
		
		//aggiorno quantità biglietti e volo in caso di nuova quantità biglietti minore
		if (oldQty>newQty) {
			 diff=oldQty-newQty;
			refFlight.setAvailablePlaces(qtyAvailable+diff);
			ticket.setTicketsQty(newQty);
			ticket.setTotalPrice(sPrice.multiply(BigDecimal.valueOf(newQty)));
			fRepo.save(refFlight);
			tRepo.save(ticket);
			return true;
		}
		//aggiorno quantità biglietti e volo in caso di nuova quantità biglietti maggiore
		diff=newQty-oldQty;
		
		// nel caso in cui non ci sono abbastanza posti disponibili
	    if (qtyAvailable < diff) {
	    	throw new Exception("Quantità superiore ai posti disponibili.");
	    }
	    refFlight.setAvailablePlaces(qtyAvailable-diff);
		ticket.setTicketsQty(newQty);
		ticket.setTotalPrice(sPrice.multiply(BigDecimal.valueOf(newQty)));
		fRepo.save(refFlight);
		tRepo.save(ticket);
		return true;
	}

}
