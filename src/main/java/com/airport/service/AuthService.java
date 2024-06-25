package com.airport.service;

import com.airport.exception.ClientGenericsException;
import com.airport.exception.ClientNotFoundException;
import com.airport.exception.InvalidClientCodeException;
import com.airport.exception.InvalidPasswordException;
import com.airport.model.AuthRequest;
import com.airport.model.AuthResponse;
import com.airport.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airport.service.interfaces.ClientFunctions;
import com.airport.utilities.JwtUtility;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private ClientFunctions clientFunctions;

    public AuthResponse authenticate(AuthRequest authRequest) throws InvalidClientCodeException, ClientNotFoundException, InvalidPasswordException, ClientGenericsException {
        String idClient = authRequest.getIdClient();
        String password = authRequest.getPassword();

        boolean isAuthenticated = clientFunctions.login(idClient, password);

        if(isAuthenticated) {
            Optional<Client> optClient = clientFunctions.getClient(idClient);
            if(optClient.isPresent()) {
            }

            if(optClient.isEmpty()) throw new ClientNotFoundException(idClient);

            String token = jwtUtility.generateAccessToken(optClient.get());
            return new AuthResponse(token);
        }

       throw new InvalidPasswordException(idClient);
    }
}
