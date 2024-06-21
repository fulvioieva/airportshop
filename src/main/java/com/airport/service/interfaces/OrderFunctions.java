package com.airport.service.interfaces;

import com.airport.exception.*;
import com.airport.model.Order;
import com.airport.model.enums.TypePayment;

import java.util.List;
import java.util.Optional;

public interface OrderFunctions {

    Optional<Order> createAndConfirmOrder(String idClient, String idFlight, int quantity, TypePayment typePayment) throws TicketNotFoundException, InsuffiecientTicketsException, FlightNotFoundException, InsuffiecientAvaliablePlaceException, ClientNotFoundException, OrderNotFoundException;
    List<Order> viewClientOrders(String idClient) throws ClientNotFoundException, NoOrderForClientException;

    boolean addTicketsToExistingOrder(String idOrder, int quantity) throws OrderNotFoundException;

    Optional<Order> getOrder(String idOrder) throws OrderNotFoundException;
}
