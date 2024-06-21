package com.airport.exception;

public class OrderNotFoundException extends Exception{

    public OrderNotFoundException(String idOrder) {
    super("Order with ID: " + idOrder + " not found");
}
}
