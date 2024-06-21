package com.airport.model;

import com.airport.utilities.IdGenerator;
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
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @Column (name = "id_client", updatable = false)
    private String idClient;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "password")
    private String password;

    // One-to-many relationship with Order entity: a client can have multiple orders
    @OneToMany (mappedBy = "client", fetch = FetchType.LAZY)
    private List<Order> orders;

    @PrePersist
    public void generateId() {
        this.idClient = IdGenerator.generateId();
    }

}
