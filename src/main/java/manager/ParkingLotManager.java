package manager;

import enums.FeeModel;
import enums.SpotType;
import factory.SimpleParkingFeeServiceFactory;
import model.Receipt;
import model.Ticket;
import model.Vehicle;
import repository.ParkingLotRepository;
import service.ReceiptService;
import service.TicketService;
import service.fee.MallParkingFeeService;
import service.fee.ParkingFeeService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static enums.FeeModel.MALL;

public class ParkingLotManager {
    private ParkingLotRepository parkingLotRepository;
    private FeeModel feeModel;
    private Map<SpotType, Integer> spotSizes;
    private TicketService ticketService;
    private ReceiptService receiptService;
    private ParkingFeeService parkingFeeService;

    public ParkingLotManager() {
        this.parkingLotRepository = new ParkingLotRepository();
        this.feeModel = MALL;
        this.spotSizes = new HashMap<>();
        ticketService = new TicketService();
        receiptService = new ReceiptService();
        parkingFeeService = new MallParkingFeeService();
    }

    public void initParkingLot(List<String> allLines) {
        FeeModel feeModel = FeeModel.valueOf(allLines.get(0).toUpperCase());
        setSpotTypeSize(allLines.get(1));
        setSpotTypeSize(allLines.get(2));
        setSpotTypeSize(allLines.get(3));
        this.feeModel = feeModel;
        this.parkingFeeService = SimpleParkingFeeServiceFactory.get(feeModel);
    }

    public Integer freeSpotsCount(SpotType spotType) {
        return spotSizes.get(spotType) - parkingLotRepository.parkedVehiclesCount(spotType);
    }

    public FeeModel getFeeModel() {
        return feeModel;
    }

    private void setSpotTypeSize(String spotString) {
        String[] spotParams = spotString.split(" ");
        SpotType spotType = SpotType.valueOf(spotParams[0].toUpperCase());
        Integer spotSize = Integer.valueOf(spotParams[1]);

        spotSizes.put(spotType, spotSize);
    }

    public void handleAction(String actionDetails) {
        String[] actionParams = actionDetails.split(" ");

        switch (actionParams[0].toUpperCase()) {
            case "PARK":
                handleParkAction(actionParams);
                break;

            case "UNPARK":
                handleUnParkAction(actionDetails, actionParams[1]);
                break;

            default:
                throw new IllegalArgumentException("Invalid action");
        }

    }

    private void handleParkAction(String[] actionParams) {
        SpotType spotType = SpotType.getSpotType(actionParams[1]);
        if (isParkingAvailable(spotType)) {
            Ticket ticket = ticketService.generateTicketFor(parkingLotRepository.nextSpotNumberFor(spotType));
            ticketService.printTicket(ticket);
            Vehicle vehicle = new Vehicle(actionParams[1], spotType, ticket);
            parkingLotRepository.park(vehicle);
        } else {
            System.out.println("No space available");
        }
    }

    private boolean isParkingAvailable(SpotType spotType) {
        return freeSpotsCount(spotType) > 0;
    }

    private void handleUnParkAction(String actionDetails, String actionParam) {
        Long ticketNumber = Long.valueOf(actionDetails.substring(actionDetails.indexOf("ticket number ")).replace("ticket number ", ""));
        SpotType currentSpotType = SpotType.getSpotType(actionParam.replace(",", ""));
        Vehicle vehicle = new Vehicle(actionParam, currentSpotType, new Ticket(ticketNumber));
        Vehicle unParkedVehicle = parkingLotRepository.unPark(vehicle);
        LocalDateTime exitDateTime = LocalDateTime.now();
        Integer fees = parkingFeeService.calculateFee(currentSpotType, unParkedVehicle.getTicket().getEntryDateTime(), exitDateTime);
        Receipt receipt = receiptService.generateReceipt(unParkedVehicle.getTicket().getEntryDateTime(), exitDateTime, fees);
        receiptService.printReceipt(receipt);
    }

}
