package it.mattiacaddeo.MyEXE_Airport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.mattiacaddeo.MyEXE_Airport.models.Client;
import it.mattiacaddeo.MyEXE_Airport.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepo;
	
	public List<Client> getClients() {
		return clientRepo.findAll();
	}
	
	public Optional<Client> getClient(String idClient) {
		return clientRepo.findById(idClient);
	}
	
	public Optional<Client> insertClient(Client client) {
		Optional<Client> existingClient = clientRepo.findById(client.getIdClient());
		if (existingClient.isPresent()) {
			return Optional.empty();
		}
		clientRepo.save(client);
		return Optional.ofNullable(client);
	}
	
	public boolean updateClient(Client client) {
		
//		SecurityContext context = SecurityContextHolder.getContext();
//
//        // Get the Authentication object from the SecurityContext
//        Authentication authentication = context.getAuthentication();
//        System.out.println(authentication.getName());
		Optional<Client> existingClient = clientRepo.findById(client.getIdClient());
		if (existingClient.isEmpty()) {
			return false;
		}
		if (client.equals(existingClient.get())) {
			return false;
		}
		clientRepo.save(client);
		return true;
	}
	
	public boolean deleteClient(String idClient) {
		if (clientRepo.existsById(idClient)) {
			clientRepo.deleteById(idClient);
			return true;
		}
		return false;
	}

}
