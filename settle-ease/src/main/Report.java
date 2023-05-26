package main;

import java.util.UUID;

public class Report<T> {
    private UUID reportId;
    private T reportData;

    public Report(T reportData) {
        this.reportId = UUID.randomUUID();
        this.reportData = reportData;
    }

    public void process() {
        System.out.println("Processing Report with ID: " + reportId);
        // Perform processing specific to the report data
    }

    public void saveReport() {
        System.out.println("Saving Report with ID: " + reportId);
        // Save the report data using the reportId
    }

    public UUID getReportId() {
        return reportId;
    }

    public T getReportData() {
        return reportData;
    }
}

