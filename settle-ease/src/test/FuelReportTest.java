package test;
import main.FuelReport;
import main.FuelRow;

import org.junit.Test;
import org.junit.Assert;


public class FuelReportTest {

    @Test
    public void testSaveFuelReport() {
        FuelReport fuelReport = new FuelReport();
        String card = "123456";
        String trxDate = "2023-05-01";
        String city = "New York";
        String state = "NY";
        String invoiceAmount = "100.00";

        fuelReport.saveFuelReport(card, trxDate, city, state, invoiceAmount);

        Assert.assertEquals(1, fuelReport.getFuelRows().size());
        FuelRow fuelRow = fuelReport.getFuelRows().get(0);
        Assert.assertEquals(card, fuelRow.getCard());
        Assert.assertEquals(trxDate, fuelRow.getTrxDate());
        Assert.assertEquals(city, fuelRow.getCity());
        Assert.assertEquals(state, fuelRow.getState());
        Assert.assertEquals(invoiceAmount, fuelRow.getInvoiceAmount());
    }

    @Test
    public void testSaveReport() {
        FuelReport fuelReport = new FuelReport();
        String card = "123456";
        String trxDate = "2023-05-01";
        String city = "New York";
        String state = "NY";
        String invoiceAmount = "100.00";

        fuelReport.saveFuelReport(card, trxDate, city, state, invoiceAmount);

        System.out.println("Expected Output:");
        System.out.println("Saving Fuel Report...");
        System.out.println("Report ID: " + fuelReport.getReportId());
        System.out.println("Fuel Report Rows: ");
        System.out.println("Fuel Card Number: " + card +
                ", Transaction Date: " + trxDate +
                ", City: " + city +
                ", State: " + state +
                ", Invoice Amount: " + invoiceAmount);

        System.out.println("Actual Output:");
        fuelReport.saveReport();
    }

    @Test
    public void testProcess() {
        FuelReport fuelReport = new FuelReport();

        System.out.println("Expected Output:");
        System.out.println("Processing Fuel Report...");
        System.out.println("Report ID: " + fuelReport.getReportId());

        System.out.println("Actual Output:");
        fuelReport.process();
    }
}
