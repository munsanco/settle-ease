package main;

import java.util.ArrayList;
import java.util.List;

public class FuelReport extends Report {
	
	// Inheritance: FuelReport inherits the properties and methods from Report
	
    private List<FuelRow> fuelRows;

    public FuelReport() {
    	
    	// Precondition: method requires non-null values for specified parameters
    	
        fuelRows = new ArrayList<>();
    }

    public void saveFuelReport(String card, String trxDate, String city, String state, String invoiceAmount) {
        FuelRow fuelRow = new FuelRow(card, trxDate, city, state, invoiceAmount);
        fuelRows.add(fuelRow);
    }

    public void saveReport() {
        System.out.println("Saving Fuel Report...");
        System.out.println("Report ID: " + getReportId());
        System.out.println("Fuel Report Rows: ");
        for (FuelRow fuelRow : fuelRows) {
            System.out.println(fuelRow);
        }
    }

    @Override
    public void process() {
        System.out.println("Processing Fuel Report...");
        System.out.println("Report ID: " + getReportId());
        // Perform any additional processing specific to FuelReport
    }

    private class FuelRow {
        private String card;
        private String trxDate;
        private String city;
        private String state;
        private String invoiceAmount;

        public FuelRow(String card, String trxDate, String city, String state, String invoiceAmount) {
            this.card = card;
            this.trxDate = trxDate;
            this.city = city;
            this.state = state;
            this.invoiceAmount = invoiceAmount;
        }

        @Override
        // Postcondition: method of FuelRow returns a string representation of FuelRow object included renamed desired output fields
        
        public String toString() {
            return "Fuel Card Number: " + card +
                    ", Transaction Date: " + trxDate +
                    ", City: " + city +
                    ", State: " + state +
                    ", Invoice Amount: " + invoiceAmount;
        }
    }
}

