package main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FuelReportTest {
    private FuelReport fuelReport;

    @Before
    public void setUp() {
        fuelReport = new FuelReport();
    }

    @Test
    public void testSaveReport() {
        // Preconditions:
        // - FuelReport instance is created in the setUp method

        // Save fuel report rows
        fuelReport.saveFuelReport("123456", "2023-05-15", "City1", "State1", "50.00");
        fuelReport.saveFuelReport("789012", "2023-05-16", "City2", "State2", "75.00");

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call saveReport method
        fuelReport.saveReport();

        // Postconditions:
        // - The fuel report rows are printed to the console in the expected format

        // Verify the output
        String expectedOutput = "Saving Fuel Report...\n" +
                "Report ID: " + fuelReport.getReportId() + "\n" +
                "Fuel Report Rows: \n" +
                "Fuel Card Number: 123456, Transaction Date: 2023-05-15, City: City1, State: State1, Invoice Amount: 50.00\n" +
                "Fuel Card Number: 789012, Transaction Date: 2023-05-16, City: City2, State: State2, Invoice Amount: 75.00\n";
        Assert.assertEquals(expectedOutput, outContent.toString());
    }
}
