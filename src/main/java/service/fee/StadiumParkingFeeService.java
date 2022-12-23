package service.fee;

import enums.SpotType;

import java.time.LocalDateTime;

import static enums.SpotType.MEDIUM;
import static enums.SpotType.SMALL;
import static java.time.temporal.ChronoUnit.MINUTES;

public class StadiumParkingFeeService implements ParkingFeeService {

    private static final double HOUR = 60.0;
    private static final int FOUR_HOURS_MINUTES = 4 * 60;
    private static final int TWELVE_HOURS_MINUTES = 12 * 60;

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
        int totalHours = (int) Math.ceil(totalMinutes / HOUR);

        if (totalMinutes < FOUR_HOURS_MINUTES) {
            return 30;
        } else if (totalMinutes < TWELVE_HOURS_MINUTES) {
            return 90;
        } else {
            return 90 + (totalHours - 12) * 100;
        }
    }

    private Integer calculateFeeForMediumSpot(LocalDateTime entryDateTime, LocalDateTime exitDateTime) {
        long totalMinutes = MINUTES.between(entryDateTime, exitDateTime);
        int totalHours = (int) Math.ceil(totalMinutes / HOUR);

        if (totalMinutes < FOUR_HOURS_MINUTES) {
            return 60;
        } else if (totalMinutes < TWELVE_HOURS_MINUTES) {
            return 180;
        } else {
            return 180 + (totalHours - 12) * 200;
        }
    }
}
