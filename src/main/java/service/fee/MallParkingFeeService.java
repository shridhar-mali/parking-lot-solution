package service.fee;

import enums.SpotType;

import java.time.LocalDateTime;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;

public class MallParkingFeeService implements ParkingFeeService {

    private static final double HOUR = 60.0;
    private Map<SpotType, Integer> spotToFeeMap;

    public MallParkingFeeService() {
        this.spotToFeeMap = Map.of(SpotType.SMALL, 10,
                SpotType.MEDIUM, 20,
                SpotType.LARGE, 50);
    }

    @Override
    public Integer calculateFee(SpotType spotType, LocalDateTime entryDateTime, LocalDateTime exitDateTime) {
        long totalMinutes = MINUTES.between(entryDateTime, exitDateTime);
        int totalHours = (int) Math.ceil(totalMinutes / HOUR);
        return spotToFeeMap.get(spotType) * totalHours;
    }
}
