package main;

import java.io.*;

public class FuelCardTotalIO {
    public static void writeFuelCardTotal(FuelReport fuelReport, String filePath) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(fuelReport);
        }
    }

    public static FuelReport readFuelCardTotal(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (FuelReport) inputStream.readObject();
        }
    }
}
