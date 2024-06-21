package com.airport.exception;

public class OrderAlreadyConfirmedException extends Exception{

    public OrderAlreadyConfirmedException(String idOrder) {
    super("Order with ID: " + idOrder + " is already confirmed");
    }
}
