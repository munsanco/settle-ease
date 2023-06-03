package main;

import java.io.Serializable;
import java.util.UUID;

public class Report<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID reportId;
    private T reportData;

    public Report(T reportData) {
        this.reportId = UUID.randomUUID();
        this.reportData = reportData;
    }

    public void process() {
        System.out.println("Processing Report with ID: " + reportId);
    }

    public void saveReport() {
        System.out.println("Saving Report with ID: " + reportId);
    }

    public UUID getReportId() {
        return reportId;
    }

    public T getReportData() {
        return reportData;
    }

    // Implement the Serializable interface
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
