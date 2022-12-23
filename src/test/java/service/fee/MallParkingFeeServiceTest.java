package service.fee;

import org.junit.Test;

import java.time.LocalDateTime;

import static enums.SpotType.*;
import static org.junit.Assert.assertEquals;

public class MallParkingFeeServiceTest {

    private MallParkingFeeService mallParkingFeeService = new MallParkingFeeService();

    @Test
    public void shouldReturnFeeForSmallVehicleSpot() {
        LocalDateTime entryDateTime  = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(6);

        assertEquals("Fee for small spot", 60, mallParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForMediumVehicleSpot() {
        LocalDateTime entryDateTime  = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(4);

        assertEquals("Fee for medium spot", 80, mallParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForLargeVehicleSpot() {
        LocalDateTime entryDateTime  = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(6);

        assertEquals("Fee for large spot", 300, mallParkingFeeService.calculateFee(LARGE, entryDateTime, exitDateTime).intValue());
    }
}