package main;

import java.util.ArrayList;
import java.util.List;

public class PayrollReport<T> extends Report<T> {
    private List<T> payrollData;

    public PayrollReport(T reportData) {
        super(reportData); // Invoke the superclass constructor with the reportData parameter
        payrollData = new ArrayList<>();
        // Preconditions: reportData should not be null
    }

    public void addPayrollData(T data) {
        payrollData.add(data);
       // Preconditions: Data should not be null
    }

    @Override
    public void process() {
        System.out.println("Processing Payroll Report...");
        // Perform specific processing for PayrollReport
        // Postconditions: The payroll report processing is completed
    }

    // Additional methods and properties specific to PayrollReport
}
