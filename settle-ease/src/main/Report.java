package main;

import java.io.Serializable;
import java.util.UUID;

public class Report<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID reportId;
    private T reportData;
    // Constructs a Report object with the specified report data.
    public Report(T reportData) {
        this.reportId = UUID.randomUUID();
        this.reportData = reportData;
    }
    // Precondition: The report has been initialized with valid data.
    // Postcondition: The report has been processed.
    public void process() {
        System.out.println("Processing Report with ID: " + reportId);
    }
    // Precondition: The report has been processed.
    // Postcondition: The report has been saved.
    public void saveReport() {
        System.out.println("Saving Report with ID: " + reportId);
   
    }
    // Returns the ID of the report.
    public UUID getReportId() {
        return reportId;
    }
 // Returns the data of the report.
    public T getReportData() {
        return reportData;
    }

    // Implement the Serializable interface
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
    }
    // Reads the object's state from the input stream during deserialization.
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}