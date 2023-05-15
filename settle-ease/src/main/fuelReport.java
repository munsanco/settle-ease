package main;

import java.util.UUID;

class FuelReport extends Report {
    private UUID reportId;

    public FuelReport() {
        reportId = UUID.randomUUID();
    }

    public void process() {
        System.out.println("Processing Fuel Report...");
        System.out.println("Report ID: " + reportId);
    }

    public void saveFuelReport() {
        System.out.println("Saving Fuel Report...");
    }

    public UUID getReportId() {
        return reportId;
    }
}
