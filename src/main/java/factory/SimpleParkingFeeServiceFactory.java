package factory;

import enums.FeeModel;
import service.fee.AirportParkingFeeService;
import service.fee.MallParkingFeeService;
import service.fee.ParkingFeeService;
import service.fee.StadiumParkingFeeService;

import static enums.FeeModel.*;

public final class SimpleParkingFeeServiceFactory {

    public static ParkingFeeService get(FeeModel feeModel) {
        if (MALL == feeModel) {
            return new MallParkingFeeService();
        } else if (STADIUM == feeModel) {
            return new StadiumParkingFeeService();
        } else if (AIRPORT == feeModel) {
            return new AirportParkingFeeService();
        } else {
            throw new IllegalStateException("Fee model not supported");
        }
    }
}
