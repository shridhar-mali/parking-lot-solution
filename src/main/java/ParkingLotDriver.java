import manager.ParkingLotManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ParkingLotDriver {

    public static void main(String[] args) throws IOException {

        String inputFilePath = args[0];

        List<String> allLines = Files.readAllLines(Paths.get(inputFilePath))
                                     .stream()
                                     .filter(Predicate.not(String::isBlank))
                                     .collect(toList());

        ParkingLotManager parkingLotManager = new ParkingLotManager();

        parkingLotManager.initParkingLot(allLines.stream().limit(4).collect(toList()));

        allLines.stream()
                .skip(4)
                .forEach(action -> parkingLotManager.handleAction(action));

    }




}
