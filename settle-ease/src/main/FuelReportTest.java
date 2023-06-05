package main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FuelReportTest {

    private FuelReport fuelReport;

    @BeforeEach
    public void setUp() {
        fuelReport = new FuelReport();
        fuelReport.saveFuelReport("12345", "2023-05-01", "City A", "State A", "50.00");
        fuelReport.saveFuelReport("67890", "2023-05-02", "City B", "State B", "75.00");
        fuelReport.saveFuelReport("12345", "2023-05-03", "City C", "State B", "100.00");
    }

    @Test
    // Precondition: The fuelReport object is properly initialized with fuelRows data.
    // Postcondition: The total fuel spent by the specified fuel card number and state is calculated correctly.
    
    public void testCalculateTotalFuelSpent() {
        double totalFuelSpent = fuelReport.calculateTotalFuelSpent("12345", "State B");
        Assertions.assertEquals(100.00, totalFuelSpent, 0.01);
    }

    @Test
    // Precondition: The fuelReport object is properly initialized with fuelRows data.
    // Postcondition: The validity of the specified fuel card number is determined correctly.
    public void testIsValidFuelCard() {
        boolean isValidCard = fuelReport.isValidFuelCard("12345");
        Assertions.assertTrue(isValidCard);
    }

    @Test
    // Precondition: The fuelReport object is properly initialized with fuelRows data.
    // Postcondition: The fuelRows are filtered by the specified state correctly, and the filtered rows are returned in a list.
    public void testFilterByState() {
        List<FuelRow> filteredRows = fuelReport.filterByState("State B");
        Assertions.assertEquals(2, filteredRows.size());
        Assertions.assertTrue(filteredRows.stream().allMatch(row -> row.getState().equalsIgnoreCase("State B")));
    }

    @Test
    // Precondition: The fuelReport object is properly initialized with fuelRows data.
    // Postcondition: The fuelRows are filtered by the specified fuel card number correctly, and the filtered rows are returned in a list.
    public void testFilterByFuelCardNumber() {
        List<FuelRow> filteredRows = fuelReport.filterByFuelCardNumber("67890");
        Assertions.assertEquals(1, filteredRows.size());
        Assertions.assertTrue(filteredRows.stream().allMatch(row -> row.getCard().equals("67890")));
    }
}
