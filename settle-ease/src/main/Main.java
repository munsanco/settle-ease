package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Precondition: The user provides a valid file path as input
        // Important Note: Please update the file path below to match the location of the CSV file on your system
        // For example, mine is /Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/TR123325.csv

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the file path: ");
            String csvFile = scanner.nextLine();

            BufferedReader reader = null;
            FuelReport fuelReport = null;
            List<FuelRow> fuelRows = null;

            try {
                reader = new BufferedReader(new FileReader(csvFile));

                // Precondition: the file path should contain a valid file from input
                // Read the header row and split out columns to handle the original CSV formatting
                String headerLine = reader.readLine();
                String[] header = headerLine.split("\\|");

                // Find the indices of the desired columns
                int cardIndex = findColumnIndex(header, "Card");
                int trxDateIndex = findColumnIndex(header, "Trx Date");
                int cityIndex = findColumnIndex(header, "City");
                int stateIndex = findColumnIndex(header, "State");
                int invoiceAmountIndex = findColumnIndex(header, "Invoice Amount");

                // Postcondition: the headerLine variable holds the first line in the CSV to be reformatted with desired renaming
                // Polymorphism: FuelReport object is assigned to Report as FuelReport can now call on polymorphic methods
                // Upcasting: creating a new instance of FuelReport
                fuelReport = new FuelReport();
                fuelReport.process();

                // Precondition: the index variables should contain valid column indexes based on the header

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim(); // Trim leading and trailing whitespace
                    if (line.isEmpty()) {
                        continue; // Skip empty rows
                    }

                    String[] rowData = splitRowData(line);
                    if (rowData.length >= 3) { // Check if the array has at least 3 elements for error handling
                        String card = rowData[cardIndex];
                        String trxDate = rowData[trxDateIndex];
                        String city = rowData[cityIndex];
                        String state = rowData[stateIndex];
                        String invoiceAmount = rowData[invoiceAmountIndex];

                        // Process the extracted data (e.g., reformatting, calculations, etc.)
                        fuelReport.saveFuelReport(card, trxDate, city, state, invoiceAmount);
                        System.out.println("Fuel Card Number: " + card +
                                ", Transaction Date: " + trxDate +
                                ", City: " + city +
                                ", State: " + state +
                                ", Invoice Amount: " + invoiceAmount);
                    } else {
                        System.out.println("Invalid row data: " + line);
                    }
                }

                // Postcondition: Each non-empty row of data is processed from the CSV file

            } catch (IOException csvError) {
                System.out.println("Processing failed. An error occurred while reading the CSV file.");
                csvError.printStackTrace();
            } finally {
                // Close the BufferedReader in the finally block
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException closeError) {
                        System.out.println("An error occurred while closing the CSV file.");
                        closeError.printStackTrace();
                    }
                }
            }

            // Precondition: object used below should not be null
            // Save the report if fuelReport is not null
            if (fuelReport != null) {
                fuelReport.saveReport();
                fuelRows = fuelReport.getFuelRows();
            }

            // Prompt the user to enter a fuel card number
            System.out.println("Enter a fuel card number:");
            String fuelCardNumber = scanner.nextLine();

            // Calculate the total invoice amount for the entered fuel card number
            double totalInvoiceAmount = 0.0;
            boolean fuelCardExists = false; // Track if the fuel card number exists

            for (FuelRow fuelRow : fuelRows) {
                if (fuelRow.getCard().equals(fuelCardNumber)) {
                    fuelCardExists = true; // Set the flag to true if the fuel card number exists
                    totalInvoiceAmount += Double.parseDouble(fuelRow.getInvoiceAmount());
                }
            }

            // Display the total invoice amount or throw an IOException if the fuel card number does not exist
            if (fuelCardExists) {
                System.out.printf("Total Invoice Amount for fuel card %s: $%.2f%n", fuelCardNumber, totalInvoiceAmount);
            } else {
                throw new IOException("Fuel card number does not exist in the file.");
            }

        } catch (IOException e) {
            System.out.println("Processing failed. " + e.getMessage());
        } finally {
            // Close the Scanner in the finally block
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static int findColumnIndex(String[] header, String columnName) {
        // Precondition: header and columnName are not null
        for (int i = 0; i < header.length; i++) {
            if (header[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        // Postcondition: Returns the index of the desired column or -1 if not found
        return -1;
    }

    private static String[] splitRowData(String line) {
        // Precondition: line is not null
        // The method assumes that the line contains data to be split into row elements.
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
        // Postcondition: The method returns an array containing the row data extracted from the given line.
        // Each element in the array represents an individual data element from the row, with delimiters properly handled.
        return rowData;
    }
}
