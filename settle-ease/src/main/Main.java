package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFile = "/Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/TR123325.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // Read the header row
            String headerLine = reader.readLine();
            String[] header = headerLine.split("\\|");

            // Find the indices of the desired columns
            int cardIndex = findColumnIndex(header, "Card");
            int trxDateIndex = findColumnIndex(header, "Trx Date");
            int cityIndex = findColumnIndex(header, "City");
            int stateIndex = findColumnIndex(header, "State");
            int invoiceAmountIndex = findColumnIndex(header, "Invoice Amount");

            // Process each row of data
            String line;
            while ((line = reader.readLine()) != null) {
            	line = line.trim(); // Trim leading and trailing whitespace
                if (line.isEmpty()) {
                    continue; // Skip empty rows
                }
            	
            	String[] rowData = splitRowData(line);
                if (rowData.length >= 3) { // Check if the array has at least 3 elements
                    String card = rowData[cardIndex];
                    String trxDate = rowData[trxDateIndex];
                    String city = rowData[cityIndex];
                    String state = rowData[stateIndex];
                    String invoiceAmount = rowData[invoiceAmountIndex];

                    // Process the extracted data (e.g., reformatting, calculations, etc.)
                    // Replace the following print statement with your desired processing logic
                    System.out.println("Fuel Card Number: " + card +
                            ", Transaction Date: " + trxDate +
                            ", City: " + city +
                            ", State: " + state +
                            ", Invoice Amount: " + invoiceAmount);
                } else {
                    System.out.println("Invalid row data: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findColumnIndex(String[] header, String columnName) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private static String[] splitRowData(String line) {
        // Handle variations in delimiters: multiple pipe symbols ("| | |") and single pipe symbol ("|")
        String[] rowData;
        if (line.contains("| |")) {
            rowData = line.split("\\| \\|");
            for (int i = 0; i < rowData.length; i++) {
                rowData[i] = rowData[i].trim();
            }
        } else {
            rowData = line.split("\\|");
        }
        return rowData;
    }
}


