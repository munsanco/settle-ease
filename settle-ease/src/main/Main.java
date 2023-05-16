package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	
    	// Precondition: The user provides a valid file path as input
        // Important Note: Please update the file path below to match the location of the CSV file on your system
    	// For example mine is /Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/TR123325.csv
    	
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path: ");
        String csvFile = scanner.nextLine();
        scanner.close();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
           
        	// Precondition: the file path should contain a valid file from input
        	// Read the header row and split out columns to handle for original CSV formatting
            String headerLine = reader.readLine();
            String[] header = headerLine.split("\\|");

            // Find the indices of the desired columns
            int cardIndex = findColumnIndex(header, "Card");
            int trxDateIndex = findColumnIndex(header, "Trx Date");
            int cityIndex = findColumnIndex(header, "City");
            int stateIndex = findColumnIndex(header, "State");
            int invoiceAmountIndex = findColumnIndex(header, "Invoice Amount");

            //Postcondition: the headerLine variable holds the first line in the CSV to be reformatted with desired renaming
            // Polymorphism: FuelReport object is assigned to Report as FuelReport can now call on polymorphic methods 
            
            Report fuelReport = new FuelReport();
            fuelReport.process();
            
         
            // Precondition: the index variables should contain valid column indexes based upon the header
            
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
                    ((FuelReport) fuelReport).saveFuelReport(card, trxDate, city, state, invoiceAmount);
                    System.out.println("Fuel Card Number: " + card +
                            ", Transaction Date: " + trxDate +
                            ", City: " + city +
                            ", State: " + state +
                            ", Invoice Amount: " + invoiceAmount);
                } else {
                    System.out.println("Invalid row data: " + line);
                }
            }

            // Polymorphism: method invocation of FuelReport object through a reference of the Report type
            
            fuelReport.saveReport();
            
            // Postcondition: Each non-empty row of data is processed from the CSV file and saved
            
            System.out.println("Report ID: " + fuelReport.getReportId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findColumnIndex(String[] header, String columnName) {
    	
    	  // Precondition: header and columnName is not null
    	
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
