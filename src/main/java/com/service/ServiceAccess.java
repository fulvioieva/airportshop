package com.service;
import com.entity.Client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.enums.StatusAccess;
import com.repository.ClientRepository;

public class ServiceAccess implements AccessFunctions {
	
	@Autowired
	private ClientRepository clientRepository;
	

	@Override
	public StatusAccess accessOn(String client_id, String password) {
		Optional<Client> c = clientRepository.findById(client_id);
		if(c.isPresent()) {		
			if(c.get().getClient_id().equals(client_id) && c.get().getPassword().equals(password)) {
				return StatusAccess.ACCESS_SUCCESFULLY;
			}else {
				return StatusAccess.WRONG_PASSWORD;
			}
		}
		return StatusAccess.WRONG_ID_CLIENT;
	}

}
