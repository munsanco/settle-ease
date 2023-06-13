package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String csvFile = null;
            final FuelReport[] fuelReport = {null};
            BufferedReader reader = null;

            // Precondition: The user provides a valid file path as input
            // Important Note: Please update the file path below to match the location of the CSV file on your system
            // For example, mine is /Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/TR123325.csv
            System.out.println("Enter the file path: ");
            csvFile = scanner.nextLine();

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

                fuelReport[0] = new FuelReport();
                fuelReport[0].process();

                ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                // Create an ExecutorService instance with a fixed number of threads based on the available processors
                // This executorService will be responsible for executing concurrent tasks
                // Postcondition: An ExecutorService instance is created with the specified number of threads and assigned to the executorService variable
                String line;
                // Process each line from the input reader, skipping empty lines
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }

                    String[] rowData = splitRowData(line);
                    // Precondition: The line is not empty and has been trimmed
                    // The method splitRowData is called to extract individual data elements from the line

                    if (rowData.length >= 3) {
                    	// Precondition: If the row data has at least 3 elements, it is considered valid for processing
                        String card = rowData[cardIndex];
                        String trxDate = rowData[trxDateIndex];
                        String city = rowData[cityIndex];
                        String state = rowData[stateIndex];
                        String invoiceAmount = rowData[invoiceAmountIndex];

                        // Submit a new task to the executorService for concurrent execution
                        executorService.submit(() -> {
                        	// Save the fuel report data using the extracted values
                            fuelReport[0].saveFuelReport(card, trxDate, city, state, invoiceAmount);                        
                            // Print the fuel report information to the console
                            System.out.println("Fuel Card Number: " + card +
                                    ", Transaction Date: " + trxDate +
                                    ", City: " + city +
                                    ", State: " + state +
                                    ", Invoice Amount: " + invoiceAmount);
                            
                        });
                        // Postcondition: A new task is submitted to the executorService
                        // The saveFuelReport method is called with the extracted data, and the information is printed to the console
                    } else {
                        System.out.println("Invalid row data: " + line);
                        // Postcondition: If the row data has less than 3 elements, it is considered invalid and an error message is printed
                    }
                }

                executorService.shutdown();
                // Initiates an orderly shutdown of the executorService
                // After this call, the executorService will no longer accept new tasks
                // Previously submitted tasks will continue to execute
                while (!executorService.isTerminated()) {
                    // Wait for all tasks to finish
                }

                // Postcondition: Each non-empty row of data is processed from the CSV file

            } catch (IOException csvError) {
                System.out.println("Processing failed. An error occurred while reading the CSV file.");
                csvError.printStackTrace();
                // Exception handling for IOException that may occur while reading the CSV file
                // Prints an error message and the stack trace of the exception
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                        // Close the reader to release system resources
                    } catch (IOException closeError) {
                        System.out.println("An error occurred while closing the CSV file.");
                        closeError.printStackTrace();
                        // Exception handling for IOException that may occur while closing the CSV file
                        // Prints an error message and the stack trace of the exception
                    }
                }
            }

            if (fuelReport[0] == null) {
            	 // Precondition: The fuelReport[0] object should not be null
            	// Checks if there is any data to process in the fuelReport array
                System.out.println("No data to process. Exiting the program.");
                return;
            }

            String fuelCardNumber = null;
            boolean exitProgram = false;
            while (!exitProgram) {
                System.out.println("Enter the fuel card number (or 'exit' to quit): ");
                fuelCardNumber = scanner.nextLine();

                if (fuelCardNumber.equalsIgnoreCase("exit")) {
                    System.out.println("You have successfully exited the program.");
                    exitProgram = true;
                    // Postcondition: The exitProgram flag is set to true
                } else {
                    // Check if the fuel card number exists
                    boolean cardExists = fuelReport[0].isValidFuelCard(fuelCardNumber);
                    // Postcondition: The cardExists flag indicates whether the fuel card number exists or not
                    if (!cardExists) {
                        System.out.println("This fuel card number does not exist. Please try again with a valid fuel card number.");
                    } else {
                        // Calculate the total fuel spent by fuel card number and state
                        double fuelCardTotalSpent = fuelReport[0].calculateTotalFuelSpent(fuelCardNumber);

                        // Calculate the overall ranking based on total fuel spent
                        int overallRank = getOverallRankConcurrently(fuelReport[0], fuelCardTotalSpent);

                        // Output the breakdown of the total fuel spent by fuel card number and state
                        System.out.println("Total Fuel Spent Breakdown:");
                        fuelReport[0].filterByFuelCardNumber(fuelCardNumber).forEach(fuelRow ->
                                System.out.printf("%s - %s: $%.2f%n", fuelRow.getCard(), fuelRow.getState(), Double.parseDouble(fuelRow.getInvoiceAmount())));
                        System.out.println(overallRank + getRankSuffix(overallRank) + " highest ranked total based upon total fuel spend.");

                        // Output the overall total fuel spent
                        System.out.printf("Overall Total Fuel Spent: $%.2f%n", fuelCardTotalSpent);
                    }
                }
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

    private static int getOverallRankConcurrently(FuelReport fuelReport, double fuelCardTotalSpent) {
    	// Precondition: The fuelReport object and fuelCardTotalSpent are not null
    	// Group the fuel rows by fuel card and calculate the total fuel spent by each card
    	Map<String, Double> totalFuelSpentByFuelCard = fuelReport.getFuelRows().stream()
                .collect(Collectors.groupingBy(FuelRow::getCard,
                        Collectors.summingDouble(row -> Double.parseDouble(row.getInvoiceAmount()))));
    					// Postcondition: The totalFuelSpentByFuelCard map contains the total fuel spent for each fuel card
        List<Double> totalFuelSpentValues = new ArrayList<>(totalFuelSpentByFuelCard.values());
        // Postcondition: The totalFuelSpentValues list contains the total fuel spent values for each fuel card
        // Concurrent sorting of totalFuelSpentValues
        totalFuelSpentValues = totalFuelSpentValues.parallelStream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        // Postcondition: The totalFuelSpentValues list is sorted concurrently in descending order
        int overallRank = totalFuelSpentValues.indexOf(fuelCardTotalSpent) + 1;
        // Postcondition: The overallRank holds the rank of the fuel card's total fuel spent
        return overallRank;
    }

    private static String getRankSuffix(int rank) {
    	// Precondition: The rank is a positive integer
        if (rank == 1) {
            return "st";
        } else if (rank == 2) {
            return "nd";
        } else if (rank == 3) {
            return "rd";
        } else {
            return "th";
        }
    }
    	// Postcondition: The suffix string corresponding to the rank is returned
}
