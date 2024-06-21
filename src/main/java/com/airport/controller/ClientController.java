package com.airport.controller;

import com.airport.exception.ClientGenericsException;
import com.airport.exception.ClientNotFoundException;
import com.airport.exception.InvalidClientCodeException;
import com.airport.exception.InvalidPasswordException;
import com.airport.model.Client;
import com.airport.model.LoginRequest;
import com.airport.service.interfaces.ClientFunctions;
import com.airport.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientFunctions clientFunctions;

    @Autowired
    private JwtUtility jwtUtility;


    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) throws ClientNotFoundException, InvalidClientCodeException, InvalidPasswordException, ClientGenericsException {

        String idClient = loginRequest.getIdClient();
        String password = loginRequest.getPassword();

        boolean login = clientFunctions.login(idClient, password);

        // GET CLIENT TO GENERATE ACCESS TOKEN
        Optional<Client> client = clientFunctions.getClient(idClient);
        HashMap response = new HashMap<>();
        response.put("accessToken", jwtUtility.generateAccessToken(client.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
