package com.airport.dto;

import com.airport.model.enums.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String idClient;
    private String name;
    private String surname;
    private String clientCode;
    private TypePayment typePayment;
    private LocalDate dtLastLogin;
    private LocalTime tmLastLogin;


}
