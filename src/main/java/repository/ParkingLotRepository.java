package repository;

import enums.SpotType;
import model.Vehicle;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class ParkingLotRepository {
    private Map<SpotType, Map<Long, Vehicle>> spots = new HashMap<>();

    public Integer parkedVehiclesCount(SpotType spotType) {
        return spots.getOrDefault(spotType, emptyMap()).keySet().size();
    }

    public Integer nextSpotNumberFor(SpotType spotType) {
        return parkedVehiclesCount(spotType) + 1;
    }

    public void park(Vehicle vehicle) {
        if(spots.get(vehicle.getSpotType()) == null) {
            spots.put(vehicle.getSpotType(), new HashMap<>());
        }
        spots.get(vehicle.getSpotType()).put(vehicle.getTicket().getTicketNumber(), vehicle);
    }

    public Vehicle unPark(Vehicle vehicle) {
        if (spots.getOrDefault(vehicle.getSpotType(), emptyMap()).containsKey(vehicle.getTicket().getTicketNumber())) {
            return spots.getOrDefault(vehicle.getSpotType(), emptyMap()).remove(vehicle.getTicket().getTicketNumber());
        } else {
            throw new IllegalArgumentException("invalid ticket number");
        }

    }
}
