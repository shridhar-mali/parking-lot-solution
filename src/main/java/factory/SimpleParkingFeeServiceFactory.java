package factory;

import enums.FeeModel;
import service.fee.AirportParkingFeeService;
import service.fee.MallParkingFeeService;
import service.fee.ParkingFeeService;
import service.fee.StadiumParkingFeeService;

import static enums.FeeModel.MALL;
import static enums.FeeModel.STADIUM;

public final class SimpleParkingFeeServiceFactory {

    public static ParkingFeeService get(FeeModel feeModel) {
        if (MALL == feeModel) {
            return new MallParkingFeeService();
        } else if (STADIUM == feeModel) {
            return new StadiumParkingFeeService();
        } else {
            return new AirportParkingFeeService();
        }
    }
}
