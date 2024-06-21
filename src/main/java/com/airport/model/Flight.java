package com.airport.model;

import com.airport.utilities.IdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight implements Serializable {
    @Id
    @Column(name = "id_flight", updatable = false)
    private String idFlight;

    @Column(name = "airport_departure")
    private String airportDeparture;

    @Column(name = "airport_destination")
    private String airportDestination;

    @Column(name = "flight_name")
    private String flightName;

    @Column(name = "date_flight")
    private LocalDate dateFlight;

    @Column(name = "time_flight")
    private LocalTime timeFlight;

    @Column(name = "available_place")
    private int availablePlace;

    @Column(name = "price")
    private double price;

    // One-to-many relationship with Ticket entity: a flight can have multiple tickets
    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @PrePersist
    public void generateId() {
        this.idFlight = IdGenerator.generateId();
    }

}
