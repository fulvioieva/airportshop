package com.airport.service;

import com.airport.exception.*;
import com.airport.model.Client;
import com.airport.model.Flight;
import com.airport.model.Order;
import com.airport.model.Ticket;
import com.airport.model.enums.State;
import com.airport.model.enums.TypePayment;
import com.airport.repository.ClientRepository;
import com.airport.repository.FlightRepository;
import com.airport.repository.OrderRepository;
import com.airport.repository.TicketRepository;
import com.airport.service.interfaces.OrderFunctions;
import com.airport.utilities.IdGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderFunctions {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightService flightService;


    @Transactional
    @Override
    public Optional<Order> createAndConfirmOrder(String idClient, String idFlight, int quantity, TypePayment typePayment) throws TicketNotFoundException, InsuffiecientTicketsException, FlightNotFoundException, InsuffiecientAvaliablePlaceException, ClientNotFoundException, OrderNotFoundException {
        //Verify flight
        Optional<Flight> optFlight = flightService.updateAvaliablePlace(idFlight, quantity);


        //Verify client
        Optional<Client> optClient = clientService.getClient(idClient);

        //Find Ticket reference and update quantity in repository UPDATED
        Optional<Ticket> optUpdatedTicket = ticketService.updatedTicketsQuantity(idFlight, quantity);
        int idTicket = optUpdatedTicket.get().getIdTicket();

        //Find order, if exists updated quantity of tickets
        Optional<Order> optExistingOrder = orderRepository.findByClientIdAndTicketId(idClient, idTicket);
        if (optExistingOrder.isPresent()) {
            Order existingOrder = optExistingOrder.get();
            addTicketsToExistingOrder(existingOrder.getIdOrder(), quantity);
            existingOrder.setState(State.UPDATED);
            orderRepository.save(existingOrder);
            return Optional.of(existingOrder);
        }
        //Create new Order
        Order order = new Order();
        order.setIdOrder(IdGenerator.generateId());

        order.setClient(optClient.get());
        order.setTicketQuantity(quantity);
        order.setTicket(optUpdatedTicket.get());
        order.setTypePayment(typePayment);
        order.setState(State.CONFIRMED);
        order.setTotalPrice(optFlight.get().getPrice() * quantity);

        orderRepository.save(order);

        return Optional.of(order);
    }

    @Override
    public List<Order> viewClientOrders(String idClient) throws ClientNotFoundException, NoOrderForClientException {
        Optional<Client> client = clientService.getClient(idClient);
        List<Order> orders = orderRepository.findAllByClientId(client.get().getIdClient());
        if(!orders.isEmpty())
            return orders;

       throw new NoOrderForClientException(idClient);
    }

    @Transactional
    @Override
    public boolean addTicketsToExistingOrder(String idOrder, int quantity) throws OrderNotFoundException {
        Order existingOrder = getOrder(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        int newQuantity = existingOrder.getTicketQuantity() + quantity;
        existingOrder.setTicketQuantity(newQuantity);
        orderRepository.save(existingOrder);

        return true;
    }


    @Override
    public Optional<Order> getOrder(String idOrder) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findByIdOrder(idOrder);
        if (order.isEmpty())
            throw new OrderNotFoundException(idOrder);

        return order;
    }
}
