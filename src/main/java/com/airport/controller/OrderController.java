package com.airport.controller;
import com.airport.dto.OrderDTO;
import com.airport.exception.*;
import com.airport.model.Order;
import com.airport.model.enums.TypePayment;
import com.airport.service.OrderService;
import com.airport.service.interfaces.OrderFunctions;
import com.airport.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
//@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderFunctions orderFunctions;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;



    @PostMapping("{idClient}/{idFlight}/{quantity}/{typePayment}")
    public ResponseEntity<OrderDTO> createAndConfirmOrder(@RequestHeader("Authorization") String token,
                                                                     @PathVariable("idClient") String idClient,
                                                                     @PathVariable ("idFlight") String idFlight,
                                                                     @PathVariable ("quantity") int quantity,
                                                                     @PathVariable ("typePayment") TypePayment typePayment) throws FlightNotFoundException, TicketNotFoundException, InsuffiecientTicketsException, ClientNotFoundException, InsuffiecientAvaliablePlaceException, OrderNotFoundException {
        try {
            Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
            String clientId = claims.getSubject();

            Optional<Order> order = orderFunctions.createAndConfirmOrder(idClient, idFlight, quantity, typePayment);
            OrderDTO orderDTO = convertToDTO(order.get(), OrderDTO.class);

            return order.isPresent()
                    ? new ResponseEntity<>(orderDTO, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("{idOrder}/{quantity}")
    public ResponseEntity<Void> addTicketToOrder(@RequestHeader("Authorization") String token,
                                                 @PathVariable("idOrder") String idOrder,
                                                 @PathVariable("quantity") int quantity) throws OrderNotFoundException {

        try {
            Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
            String clientId = claims.getSubject();

            boolean ticketAdded = orderFunctions.addTicketsToExistingOrder(idOrder, quantity);


            return ticketAdded
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping(value = "orders/{idClient}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> getOrders(@PathVariable("idClient") String idCLient,
                                                      @RequestHeader("Authorization") String token) throws FlightNotFoundException, NoOrderForClientException, ClientNotFoundException {
        //TODO: modify
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
        String clientId = claims.getSubject();

        List<Order> orders = orderFunctions.viewClientOrders(idCLient);

        List<OrderDTO> ordersDTO = orders.stream()
                                    .map(order -> convertToDTO(order, OrderDTO.class))
                                    .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("orders:", ordersDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }



    }
