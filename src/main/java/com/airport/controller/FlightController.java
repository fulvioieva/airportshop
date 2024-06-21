package com.airport.controller;

import com.airport.dto.FlightDTO;
import com.airport.exception.FlightNotFoundException;
import com.airport.model.Flight;
import com.airport.service.interfaces.FlightFunctions;
import com.airport.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/")
public class FlightController {

    @Autowired
    private FlightFunctions flightFunctions;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;



    @GetMapping("flights/available")
    public ResponseEntity<List<FlightDTO>> viewAvailableFlightsFromNow(@RequestHeader("Authorization") String token) {
        // CHECK TO VERIFY THAT THE TOKEN HAS BEEN VALIDATED
        try {
            Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
            String clientId = claims.getSubject();

            List<Flight> flights = flightFunctions.viewAvailableFlightsFromNow();

            // LOGIC TO FETCH AVAILABLE FLIGHTS
            List<FlightDTO> flightsDTO = flights.stream()
                                        .map(flight -> convertToDTO(flight, FlightDTO.class))
                                        .collect(Collectors.toList());

            return !flightsDTO.isEmpty()
                    ? new ResponseEntity<>(flightsDTO, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("flight/{idFlight}")
    public ResponseEntity<FlightDTO> getFly(@PathVariable("idFlight") String idFlight,
                                            @RequestHeader("Authorization") String token) throws FlightNotFoundException {
        try {
            Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));
            String clientId = claims.getSubject();

            Optional<Flight> flight = flightFunctions.getFlight(idFlight);

            FlightDTO flightDTO = convertToDTO(flight, FlightDTO.class);

            return !flight.isEmpty()
                    ? new ResponseEntity<>(flightDTO, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JwtException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
