package model;

import enums.SpotType;

import java.util.Objects;

public class Vehicle {

    private String vehicleType;
    private SpotType spotType;
    private Ticket ticket;

    public Vehicle(String vehicleType, SpotType spotType, Ticket ticket) {
        this.vehicleType = vehicleType;
        this.spotType = spotType;
        this.ticket = ticket;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleType.equals(vehicle.vehicleType) &&
                ticket.getTicketNumber().equals(vehicle.ticket.getTicketNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, ticket.getTicketNumber());
    }
}
