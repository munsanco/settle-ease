package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FuelRowTest {

    @Test
    public void testToString() {
        // Create a FuelRow object with sample data
        FuelRow fuelRow = new FuelRow("1234567890", "2023-05-16", "New York", "NY", "100.00");

        // Define the expected string representation
        String expectedString = "Fuel Card Number: 1234567890, Transaction Date: 2023-05-16, City: New York, State: NY, Invoice Amount: 100.00";

        // Call the toString() method
        String actualString = fuelRow.toString();

        // Compare the expected and actual strings
        Assertions.assertEquals(expectedString, actualString);
    }

    // Add additional test methods for other functionalities of the FuelRow class if needed

}
