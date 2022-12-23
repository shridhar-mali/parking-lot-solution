package service.fee;

import enums.SpotType;

import java.time.LocalDateTime;

import static enums.SpotType.MEDIUM;
import static enums.SpotType.SMALL;
import static java.time.temporal.ChronoUnit.MINUTES;

public class AirportParkingFeeService implements ParkingFeeService {

    private static final double HOUR_MINUTES = 60.0;
    private static final double ONE_DAY_HOURS = 24.0;
    private static final int TWELVE_HOURS_MINUTES = 12 * 60;
    private static final int TWENTY_FOUR_HOURS_MINUTES = 24 * 60;
    private static final int EIGHT_HOURS_MINUTES = 8 * 60;

    @Override
    public Integer calculateFee(SpotType spotType, LocalDateTime entryDateTime, LocalDateTime exitDateTime) {
        if (spotType == SMALL) {
            return calculateFeeForSmallSpot(entryDateTime, exitDateTime);
        } else if (spotType == MEDIUM) {
            return calculateFeeForMediumSpot(entryDateTime, exitDateTime);
        } else {
            throw new IllegalStateException("Vehicle not supported");
        }
    }


    private Integer calculateFeeForSmallSpot(LocalDateTime entryDateTime, LocalDateTime exitDateTime) {
        long totalMinutes = MINUTES.between(entryDateTime, exitDateTime);
        int totalHours = (int) Math.ceil(totalMinutes / HOUR_MINUTES);
        int totalDays = (int) Math.ceil(totalHours / 24.0);

        if (totalMinutes < HOUR_MINUTES) {
            return 0;
        } else if (totalMinutes < EIGHT_HOURS_MINUTES) {
            return 40;
        } else if (totalMinutes < TWENTY_FOUR_HOURS_MINUTES) {
            return 60;
        } else {
            return totalDays * 80;
        }
    }

    private Integer calculateFeeForMediumSpot(LocalDateTime entryDateTime, LocalDateTime exitDateTime) {
        long totalMinutes = MINUTES.between(entryDateTime, exitDateTime);
        int totalHours = (int) Math.ceil(totalMinutes / HOUR_MINUTES);
        int totalDays = (int) Math.ceil(totalHours / ONE_DAY_HOURS);

        if (totalMinutes < TWELVE_HOURS_MINUTES) {
            return 60;
        } else if (totalMinutes < TWENTY_FOUR_HOURS_MINUTES) {
            return 80;
        } else {
            return totalDays * 100;
        }
    }
}
