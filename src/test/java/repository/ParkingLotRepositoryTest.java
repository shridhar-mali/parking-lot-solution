package repository;

import model.Ticket;
import model.Vehicle;
import org.junit.Test;

import java.time.LocalDateTime;

import static enums.SpotType.LARGE;
import static enums.SpotType.SMALL;
import static org.junit.Assert.assertEquals;

public class ParkingLotRepositoryTest {

    @Test
    public void shouldGiveNextSmallSpotNumber() {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        assertEquals("next spot number for small vehicle", 1, parkingLotRepository.nextSpotNumberFor(SMALL).intValue());
    }

    @Test
    public void shouldParkSmallVehicle() {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        parkingLotRepository.park(new Vehicle("motorcycle", SMALL, new Ticket(1l, 1, LocalDateTime.now())));

        assertEquals("Parked small vehicles count", 1, parkingLotRepository.parkedVehiclesCount(SMALL).intValue());
        assertEquals("next spot number for small vehicle", 2, parkingLotRepository.nextSpotNumberFor(SMALL).intValue());
    }

    @Test
    public void shouldParkLargeVehicle() {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        parkingLotRepository.park(new Vehicle("truck", LARGE, new Ticket(1l, 1, LocalDateTime.now())));

        assertEquals("Parked small vehicles count", 1, parkingLotRepository.parkedVehiclesCount(LARGE).intValue());
        assertEquals("next spot number for small vehicle", 2, parkingLotRepository.nextSpotNumberFor(LARGE).intValue());
    }

    @Test
    public void shouldUnParkSmallVehicle() {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        LocalDateTime entryDateTime = LocalDateTime.now();
        parkingLotRepository.park(new Vehicle("motorcycle", SMALL, new Ticket(1l, 1, entryDateTime)));
        Vehicle unparkedVehicle = parkingLotRepository.unPark(new Vehicle("motorcycle", SMALL, new Ticket(1l)));

        assertEquals("ticket number", 1, unparkedVehicle.getTicket().getTicketNumber().longValue());
        assertEquals("entry dateTime number", entryDateTime, unparkedVehicle.getTicket().getEntryDateTime());
        assertEquals("Parked small vehicles count", 0, parkingLotRepository.parkedVehiclesCount(SMALL).intValue());
        assertEquals("next spot number for small vehicle", 1, parkingLotRepository.nextSpotNumberFor(SMALL).intValue());
    }
}