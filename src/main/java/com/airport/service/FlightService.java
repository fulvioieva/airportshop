package com.airport.service;

import com.airport.exception.FlightNotFoundException;
import com.airport.exception.InsuffiecientAvaliablePlaceException;
import com.airport.model.Flight;
import com.airport.repository.FlightRepository;
import com.airport.service.interfaces.FlightFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements FlightFunctions {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> viewAvailableFlightsFromNow() {
        return flightRepository.findAllFromNow();
    }

    @Override
    public Optional<Flight> getFlight(String idFlight) throws FlightNotFoundException {
        Optional<Flight> flight = flightRepository.findById(idFlight);
        if (!flight.isPresent()) {
            throw new FlightNotFoundException(idFlight);
        }
        return flight;
    }

    @Override
    public Optional<Flight> updateAvaliablePlace(String idFlight, int quantity) throws FlightNotFoundException, InsuffiecientAvaliablePlaceException {
        Optional<Flight> optExistingFlight = getFlight(idFlight);

        Flight flight = optExistingFlight.get();

        if (flight.getAvailablePlace() < quantity) {
            throw new InsuffiecientAvaliablePlaceException("Insufficient quantity of places ");
        }

        flight.setAvailablePlace(flight.getAvailablePlace() - quantity);
        flightRepository.save(flight);

        return Optional.ofNullable(flight);
    }
}
