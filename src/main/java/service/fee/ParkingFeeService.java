package service.fee;

import enums.SpotType;

import java.time.LocalDateTime;

public interface ParkingFeeService {

    Integer calculateFee(SpotType spotType, LocalDateTime entryDateTime, LocalDateTime exitDateTime);
}
