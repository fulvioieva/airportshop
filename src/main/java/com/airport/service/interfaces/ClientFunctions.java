package com.airport.service.interfaces;

import com.airport.exception.ClientGenericsException;
import com.airport.exception.ClientNotFoundException;
import com.airport.exception.InvalidClientCodeException;
import com.airport.exception.InvalidPasswordException;
import com.airport.model.Client;
import com.airport.model.Order;

import java.util.List;
import java.util.Optional;


public interface ClientFunctions {
    boolean login(String idClient, String password) throws ClientNotFoundException, ClientGenericsException, InvalidClientCodeException, InvalidPasswordException;
    Optional<Client> getClient(String idClient) throws ClientNotFoundException;
}
