package manager;

import org.junit.Test;

import java.util.Arrays;

import static enums.FeeModel.MALL;
import static enums.SpotType.*;
import static org.junit.Assert.assertEquals;

public class ParkingLotManagerTest {

    @Test
    public void shouldInitializeParkingLotProperly() {

        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.initParkingLot(Arrays.asList("Mall", "SMALL 50", "medium 30", "LArge 20"));

        assertEquals("Fee model", MALL, parkingLotManager.getFeeModel());
        assertEquals("Free small spots", Integer.valueOf(50), parkingLotManager.freeSpotsCount(SMALL));
        assertEquals("Free medium spots", Integer.valueOf(30), parkingLotManager.freeSpotsCount(MEDIUM));
        assertEquals("Free large spots", Integer.valueOf(20), parkingLotManager.freeSpotsCount(LARGE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForBadInput() {
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.initParkingLot(Arrays.asList("some wrong string", "SMALL 50", "medium 30", "LArge 20"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForBadAction() {
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.initParkingLot(Arrays.asList("Mall", "SMALL 50", "medium 30", "LArge 20"));
        parkingLotManager.handleAction("UNKOWN_ACTION motorcycle");
    }

    @Test
    public void shouldIPrintTicket() {

        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.initParkingLot(Arrays.asList("Mall", "SMALL 2", "medium 3", "LArge 1"));

        parkingLotManager.handleAction("Park motorcycle");
        parkingLotManager.handleAction("Park scooter");
    }

    @Test
    public void shouldIPrintReceipt() {

        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.initParkingLot(Arrays.asList("Mall", "SMALL 2", "medium 3", "LArge 1"));

        parkingLotManager.handleAction("Park motorcycle");
        parkingLotManager.handleAction("Unpark motorcycle, ticket number 001");
    }
}