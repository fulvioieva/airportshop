package com.airport.model;

import com.airport.model.enums.State;
import com.airport.model.enums.TypePayment;
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
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @Column(name = "id_order", updatable = false)
    private String idOrder;

    //Many-to-one relationship with Client entity: many orders for single client
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    //Many-to-one relationship with Ticket entity: many tickets for single client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    @Column(name = "ticket_quantity")
    private int ticketQuantity;

    @Column (name = "type_payment")
    @Enumerated(EnumType.STRING)
    private TypePayment typePayment;

    @Column (name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "total_price")
    private double totalPrice;

    //TODO VERIFY
    /*
    @PrePersist //Generate identity  before persisting the entity
    public void generateId() {
        this.idOrder = IdGenerator.generateId();
    }*/

}
