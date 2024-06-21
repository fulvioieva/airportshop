package com.airport.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String idClient) {
        super("Invalid Password for Client with ID: " + idClient);
    }
}
