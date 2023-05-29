package main;

import java.util.UUID;

public class Report<T> {
    private UUID reportId;
    private T reportData;

    public Report(T reportData) {
    	// Precondition: reportData parameter is not null
        this.reportId = UUID.randomUUID();
        this.reportData = reportData;
    }

    public void process() {
    	// Precondition: reportData is set and not null
        System.out.println("Processing Report with ID: " + reportId);
        // Perform processing specific to the report data
       // Postcondition: The processing of the report data is completed
    }

    public void saveReport() {
    	// Precondition: reportData is set and not null
        System.out.println("Saving Report with ID: " + reportId);
        // Save the report data using the reportId
       // Postcondition: The report data is saved
    }

    public UUID getReportId() {
        return reportId;
       // Postcondition: Returns the UUID assigned to the report
    }

    public T getReportData() {
        return reportData;
      // Postcondition: Returns the report data
    }
}

