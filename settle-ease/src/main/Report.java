package main;

import java.util.UUID;

abstract class Report {
    protected static UUID reportId = UUID.randomUUID();

    public abstract void process();

    public void saveReport() {
        System.out.println("Saving Report...");
    }

    public UUID getReportId() {
        return reportId;
    }
}
