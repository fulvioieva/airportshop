package com.airport.controller;

import com.airport.exception.ClientGenericsException;
import com.airport.exception.ClientNotFoundException;
import com.airport.exception.InvalidClientCodeException;
import com.airport.exception.InvalidPasswordException;
import com.airport.model.AuthRequest;
import com.airport.model.AuthResponse;
import com.airport.model.Client;
import com.airport.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/airport/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            AuthResponse authResponse = authService.authenticate(authRequest);
            String accessToken = authResponse.getToken();
            return new ResponseEntity<>(accessToken, HttpStatus.OK);
        } catch (ClientNotFoundException | InvalidClientCodeException | InvalidPasswordException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (ClientGenericsException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
