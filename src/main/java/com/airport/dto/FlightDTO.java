package com.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private String idFlight;
    private String airportDeparture;
    private String airportDestination;
    private String flightName;
    private LocalDate dateFlight;
    private LocalTime timeFlight;
    private int availablePlace;
    private double price;

}
