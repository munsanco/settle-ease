package main;

import java.util.ArrayList;
import java.util.List;

public class FuelReport extends Report<List<FuelRow>> {

    private List<FuelRow> fuelRows;

    public FuelReport() {
    	// Precondition: The superclass constructor initializes the reportData with an empty ArrayList
        super(new ArrayList<>());
        fuelRows = getReportData();
        // Postcondition: The fuelRows reference is set to the reportData instance
    }

    public void saveFuelReport(String card, String trxDate, String city, String state, String invoiceAmount) {
        FuelRow fuelRow = new FuelRow(card, trxDate, city, state, invoiceAmount);
        fuelRows.add(fuelRow);
       // Postcondition: The fuelRow is added to the fuelRows list
    }

    public void saveFuelReport(FuelRow fuelRow) {
    	// Precondition: The fuelRow object is not null
        fuelRows.add(fuelRow);
        // Postcondition: The fuelRow is added to the fuelRows list
    }

    @Override
    public void saveReport() {
        System.out.println("Saving Fuel Report...");
        System.out.println("Report ID: " + getReportId());
        System.out.println("Fuel Report Rows: ");
        for (FuelRow fuelRow : fuelRows) {
            System.out.println(fuelRow);
         // Postcondition: The fuel report is saved and displayed
        }
    }

    @Override
    public void process() {
        System.out.println("Processing Fuel Report...");
        System.out.println("Report ID: " + getReportId());
        // Perform any additional processing specific to FuelReport
        // Postcondition: The fuel report processing is completed
    }

    public List<FuelRow> getFuelRows() {
        return fuelRows;
    }

    public void setFuelRows(List<FuelRow> fuelRows) {
    	// Precondition: The fuelRows list is not null
        this.fuelRows = fuelRows;
        // Postcondition: The fuelRows list is set
    }
}
