package service;

import model.Ticket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketServiceTest {

    @Test
    public void shouldGenerateIncrementalTickets() {

        TicketService ticketService = new TicketService();

        Ticket ticket1 = ticketService.generateTicketFor(10);
        Ticket ticket2 = ticketService.generateTicketFor(11);

        assertEquals("Ticket Number one", 1, ticket1.getTicketNumber().intValue());
        assertEquals("Ticket Number two", 2, ticket2.getTicketNumber().intValue());
    }
}