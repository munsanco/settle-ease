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

    public List<FuelRow> getFuelRows() {
		return fuelRows;
	}

	public void setFuelRows(List<FuelRow> fuelRows) {
		this.fuelRows = fuelRows;
	}
}

