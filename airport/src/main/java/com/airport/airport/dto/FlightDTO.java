package com.airport.airport.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class FlightDTO {
	
	private String idFlight;

	private int availablePlaces;
	
	private LocalDate dateDeparture;
	
	private LocalTime timeDeparture;
	
	private int price;

	private String departureAirport;

	private String destinationAirport;

	public FlightDTO(String idFlight, int availablePlaces, LocalDate dateDeparture, LocalTime timeDeparture, int price,
			String departureAirport, String destinationAirport) {
		super();
		this.idFlight = idFlight;
		this.availablePlaces = availablePlaces;
		this.dateDeparture = dateDeparture;
		this.timeDeparture = timeDeparture;
		this.price = price;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
	}

	public FlightDTO() {
		super();
	}

	public String getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(String idFlight) {
		this.idFlight = idFlight;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}

	public LocalDate getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(LocalDate dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public LocalTime getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(LocalTime timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availablePlaces, dateDeparture, departureAirport, destinationAirport, idFlight, price,
				timeDeparture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightDTO other = (FlightDTO) obj;
		return availablePlaces == other.availablePlaces && Objects.equals(dateDeparture, other.dateDeparture)
				&& Objects.equals(departureAirport, other.departureAirport)
				&& Objects.equals(destinationAirport, other.destinationAirport)
				&& Objects.equals(idFlight, other.idFlight) && price == other.price
				&& Objects.equals(timeDeparture, other.timeDeparture);
	}

	


}
