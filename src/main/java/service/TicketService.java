package service;

import model.Ticket;

import static java.time.LocalDateTime.now;

public class TicketService {
    private Long ticketNumber;

    public TicketService() {
        ticketNumber = 1l;
    }

    public Ticket generateTicketFor(Integer spotNumber) {
        return new Ticket(ticketNumber++, spotNumber, now());
    }

    public void printTicket(Ticket ticket) {
        System.out.println(ticket);
    }
}
