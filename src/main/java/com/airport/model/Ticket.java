package com.airport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")

public class Ticket implements Serializable {
    @Id
    @Column(name = "id_ticket")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    // Many-to-one relationship with Flight entity: many tickets for single flight
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_flight")
    private Flight flight;

    //TODO CASCADE ECC
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @Column(name = "available_quantity")
    private int availableQuantity;
}
