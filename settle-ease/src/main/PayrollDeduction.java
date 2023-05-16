package main;
public abstract class PayrollDeduction {
    public abstract void processRow();
}

class FuelDeduction extends PayrollDeduction {
    public void processRow() {
        System.out.println("Processing Fuel Deduction Row...");
    }
}