package service.fee;

import org.junit.Test;

import java.time.LocalDateTime;

import static enums.SpotType.*;
import static org.junit.Assert.assertEquals;

public class StadiumParkingFeeServiceTest {

    private StadiumParkingFeeService stadiumParkingFeeService = new StadiumParkingFeeService();

    @Test
    public void shouldReturnFeeForMotorcycleParkedForThreePlusHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(3).plusMinutes(40);

        assertEquals("Fee for small spot", 30, stadiumParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForMotorcycleParkedForSevenPlusHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(7).plusMinutes(40);

        assertEquals("Fee for small spot", 90, stadiumParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForScooterParkedForFourteenPlusHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(14).plusMinutes(59);

        assertEquals("Fee for small spot", 390, stadiumParkingFeeService.calculateFee(SMALL, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForCarParkedForElevenPlusHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(11).plusMinutes(30);

        assertEquals("Fee for medium spot", 180, stadiumParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForCarParkedForLessThanFourHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(3).plusMinutes(59);

        assertEquals("Fee for medium spot", 60, stadiumParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test
    public void shouldReturnFeeForSuvParkedForThirteenPlusHours() {
        LocalDateTime entryDateTime = LocalDateTime.now();
        LocalDateTime exitDateTime = entryDateTime.plusHours(13).plusMinutes(5);

        assertEquals("Fee for large spot", 580, stadiumParkingFeeService.calculateFee(MEDIUM, entryDateTime, exitDateTime).intValue());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionForLargeSpot() {
        stadiumParkingFeeService.calculateFee(LARGE, LocalDateTime.now(), LocalDateTime.now());
    }
}