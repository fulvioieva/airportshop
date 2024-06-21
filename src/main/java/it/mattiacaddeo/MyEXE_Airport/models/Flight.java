package it.mattiacaddeo.MyEXE_Airport.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
create table if not exists Flights (
	id_flight varchar(50),
    airport_departure varchar(255),
    airport_arrival varchar(255),
    name varchar(255) not null,
    date_departure Date not null,
    time_departure Time not null,
    seats_available int not null,
    unit_price decimal(10, 2) not null,
    primary key(id_flight)
);
*/

@Entity
@Table(name = "Flights")
public class Flight {

	@Id
	private String idFlight;
	private String airportDeparture;
	private String airportArrival;
	private String name;
	private Date dateDeparture;
	private Time timeDeparture;
	private int seatsAvailable;
	private double unitPrice;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="flight", cascade={CascadeType.ALL})
	@JsonIgnore
	private List<Reservation> reservations = new ArrayList<>();

	public Flight() {
	}

	public String getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(String idFlight) {
		this.idFlight = idFlight;
	}

	public String getAirportDeparture() {
		return airportDeparture;
	}

	public void setAirportDeparture(String airportDeparture) {
		this.airportDeparture = airportDeparture;
	}

	public String getAirportArrival() {
		return airportArrival;
	}

	public void setAirportArrival(String airportArrival) {
		this.airportArrival = airportArrival;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public Time getTimeDeparure() {
		return timeDeparture;
	}

	public void setTimeDeparure(Time timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(Reservation reservation) {
		if (reservations == null) {
			reservations = new ArrayList<>();
		}
		if (!reservations.contains(reservation)) {
			reservations.add(reservation);
		}
	}
	
	public void removeReservation(Reservation reservation) {
		if (reservations.contains(reservation)) {
			reservations.remove(reservation);
			reservation.setFlight(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(airportArrival, airportDeparture, dateDeparture, idFlight, name, seatsAvailable,
				timeDeparture, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(airportArrival, other.airportArrival)
				&& Objects.equals(airportDeparture, other.airportDeparture)
				&& Objects.equals(dateDeparture, other.dateDeparture) && Objects.equals(idFlight, other.idFlight)
				&& Objects.equals(name, other.name) && seatsAvailable == other.seatsAvailable
				&& Objects.equals(timeDeparture, other.timeDeparture)
				&& Double.doubleToLongBits(unitPrice) == Double.doubleToLongBits(other.unitPrice);
	}

	@Override
	public String toString() {
		return "Flight [idFlight=" + idFlight + ", airportDeparture=" + airportDeparture + ", airportArrival="
				+ airportArrival + ", name=" + name + ", dateDeparture=" + dateDeparture + ", timeDeparture="
				+ timeDeparture + ", seatsAvailable=" + seatsAvailable + ", unitPrice=" + unitPrice + "]";
	}

}
