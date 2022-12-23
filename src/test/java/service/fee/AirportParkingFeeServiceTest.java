package service.fee;

import org.junit.Test;

import java.time.LocalDateTime;

import static enums.SpotType.*;
import static org.junit.Assert.assertEquals;

public class AirportParkingFeeServiceTest {

    private AirportParkingFeeService airportParkingFeeService = new AirportParkingFeeService();

    @Test
    public void shouldReturnFeeForMotorcycleParkedForLessThanHour() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusMinutes(55);

        assertEquals("Fee for small spot", 0, airportParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForMotorcycleParkedForMoreThanSixHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(6).plusMinutes(55);

        assertEquals("Fee for small spot", 40, airportParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }


    @Test
    public void shouldReturnFeeForScooterParkedForFourteenPlusHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(14).plusMinutes(59);

        assertEquals("Fee for small spot", 60, airportParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForScooterParkedForMoreThanOneDay() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusDays(1).plusHours(12);

        assertEquals("Fee for small spot", 160, airportParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForCarParkedForLessThanHour() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusMinutes(50);

        assertEquals("Fee for medium spot", 60, airportParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForSuvParkedForLessThanDay() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(23).plusMinutes(59);

        assertEquals("Fee for large spot", 80, airportParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForSuvParkedForMoreThanThreeDays() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusDays(3).plusHours(1);

        assertEquals("Fee for large spot", 400, airportParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionForLargeSpot() {
        airportParkingFeeService.calculateFee(LARGE, LocalDateTime.now(), LocalDateTime.now());
    }

}