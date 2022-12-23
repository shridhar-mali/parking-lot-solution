package model;

import java.time.LocalDateTime;

public class Ticket {
    private Long ticketNumber;
    private Integer spotNumber;
    private LocalDateTime entryDateTime;

    public Ticket(Long ticketNumber, Integer spotNumber, LocalDateTime entryDateTime) {
        this.ticketNumber = ticketNumber;
        this.spotNumber = spotNumber;
        this.entryDateTime = entryDateTime;
    }

    public Ticket(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    @Override
    public String toString() {
        return "Parking Ticket:\n" +
                "\tTicket Number: " + ticketNumber +
                "\n\tSpot Number: " + spotNumber +
                "\n\tEntry Date-time:" + entryDateTime;
    }
}
