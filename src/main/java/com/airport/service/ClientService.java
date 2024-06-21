package com.airport.service;

import com.airport.exception.ClientGenericsException;
import com.airport.exception.ClientNotFoundException;
import com.airport.exception.InvalidClientCodeException;
import com.airport.exception.InvalidPasswordException;
import com.airport.model.*;
import com.airport.model.enums.State;
import com.airport.model.enums.TypePayment;
import com.airport.repository.*;

import com.airport.service.interfaces.ClientFunctions;
import com.airport.utilities.IdGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientFunctions {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TicketRepository ticketRepository;



    @Override
    public boolean login(String idClient, String password) throws ClientGenericsException, InvalidClientCodeException, ClientNotFoundException, InvalidPasswordException {
        try {
            Client client = getClient(idClient).orElseThrow(() -> new ClientNotFoundException(idClient));
            if (!client.getIdClient().equals(idClient)) throw new InvalidClientCodeException(idClient);
            if (!client.getPassword().equals(password)) throw new InvalidPasswordException(idClient);

        } catch (ClientNotFoundException | InvalidPasswordException | InvalidClientCodeException e) {
            throw e;
        } catch (Exception e) {
            throw new ClientGenericsException("Login failed", e);
        }
        return true;
    }


    @Override
    public Optional<Client> getClient(String idClient) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findById(idClient);
        if (client.isEmpty())
            throw new ClientNotFoundException(idClient);
        return client;
    }


}

