package main;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FuelCardTotalIO {
    public static void writeFuelReportToFile(FuelReport fuelReport, String filePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            List<FuelRow> fuelRows = fuelReport.getFuelRows();
            int size = fuelRows.size();

            // Write the size of the fuelRows list
            dataOutputStream.writeInt(size);

            // Write each FuelRow object
            for (FuelRow fuelRow : fuelRows) {
                // Write the card string
                dataOutputStream.writeUTF(fuelRow.getCard());

                // Write the trxDate string
                dataOutputStream.writeUTF(fuelRow.getTrxDate());

                // Write the city string
                dataOutputStream.writeUTF(fuelRow.getCity());

                // Write the state string
                dataOutputStream.writeUTF(fuelRow.getState());

                // Write the invoiceAmount string
                dataOutputStream.writeUTF(fuelRow.getInvoiceAmount());
            }
        }
    }

    public static FuelReport readFuelReportFromFile(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {

            List<FuelRow> fuelRows = new ArrayList<>();

            // Read the size of the fuelRows list
            int size = dataInputStream.readInt();

            // Read each FuelRow object
            for (int i = 0; i < size; i++) {
                // Read the card string
                String card = dataInputStream.readUTF();

                // Read the trxDate string
                String trxDate = dataInputStream.readUTF();

                // Read the city string
                String city = dataInputStream.readUTF();

                // Read the state string
                String state = dataInputStream.readUTF();

                // Read the invoiceAmount string
                String invoiceAmount = dataInputStream.readUTF();

                // Create a new FuelRow object and add it to the list
                FuelRow fuelRow = new FuelRow(card, trxDate, city, state, invoiceAmount);
                fuelRows.add(fuelRow);
            }

            FuelReport fuelReport = new FuelReport();
            fuelReport.setFuelRows(fuelRows);
            return fuelReport;
        }
    }
}
