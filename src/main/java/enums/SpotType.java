package enums;

public enum SpotType {
    SMALL("Motorcycles/Scooters"),
    MEDIUM("Cars/SUVs"),
    LARGE("Buses/Trucks");

    private String supportedVehicles;

    SpotType(String supportedVehicles) {
        this.supportedVehicles = supportedVehicles;
    }

    public static SpotType getSpotType(String vehicle) {
        SpotType spotType;
        switch (vehicle.toUpperCase()) {
            case "MOTORCYCLE":
            case "SCOOTER":
                spotType = SMALL;
                break;

            case "CAR":
            case "SUV":
                spotType = MEDIUM;
                break;

            case "BUS":
            case "TRUCK":
                spotType = LARGE;
                break;

            default:
                throw new IllegalArgumentException("vehicle not supported");

        }
        return spotType;
    }
}
