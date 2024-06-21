package com.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String idOrder;
    private String idClient;
    private int ticketQuantity;
    private String typePayment;
    private String state;
    private double totalPrice;


}
