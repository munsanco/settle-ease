package main;

import java.util.Date;
import java.util.UUID;


abstract class Report {
    // abstract method
    public abstract void process();
}

abstract class PayrollDeduction {
    // abstract method
    public abstract void processRow();
}

class FuelDeduction extends PayrollDeduction {
    public void processRow() {
        System.out.println("Processing Fuel Deduction Row...");
    }
}