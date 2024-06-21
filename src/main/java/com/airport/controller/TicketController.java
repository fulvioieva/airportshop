package com.airport.controller;

import com.airport.service.interfaces.TicketFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TicketController {
    @Autowired
    private TicketFunctions ticketFunctions;



}
