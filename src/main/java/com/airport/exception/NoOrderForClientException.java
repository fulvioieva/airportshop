package com.airport.exception;

public class NoOrderForClientException extends Exception {
    public NoOrderForClientException(String idClient) {
        super("Client with ID: " + idClient + " hasn't any orders");
    }
}
