package main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FuelCardTotalIOTest {

    private FuelReport fuelReport;

    @BeforeEach
    public void setUp() {
        fuelReport = new FuelReport();
    }

    @Test
    public void testWriteAndReadFuelReport() throws IOException, ClassNotFoundException {
        // Precondition: fuelReport is not null
        String filePath = "fuel_report.dat";
        FuelCardTotalIO.writeFuelReportToFile(fuelReport, filePath);

        FuelReport readFuelReport = FuelCardTotalIO.readFuelReportFromFile(filePath);
        // Postcondition: The FuelReport object is successfully written to a file and read back without any data loss
        Assertions.assertEquals(fuelReport.getReportId(), readFuelReport.getReportId());
    }

    @Test
    public void testWriteAndReadBytes() throws IOException {
        // Precondition: filePath is a valid file path
        String filePath = "bytes.dat";
        byte[] testData = {0, 1, 2, 3, 4};
        FuelCardTotalIO.writeBytesToFile(testData, filePath);

        byte[] readData = FuelCardTotalIO.readBytesFromFile(filePath);
        // Postcondition: The byte array is successfully written to a file and read back without any data loss
        Assertions.assertArrayEquals(testData, readData);
    }

    @Test
    public void testWriteAndReadPrimitiveTypesAndStrings() throws IOException {
        // Precondition: filePath is a valid file path
        String filePath = "primitive_types.dat";
        int intValue = 42;
        double doubleValue = 3.14;
        boolean booleanValue = true;
        String stringValue = "Hello, World!";
        FuelCardTotalIO.writePrimitiveTypesAndStringsToFile(filePath, intValue, doubleValue, booleanValue, stringValue);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(byteArrayOutputStream));

        FuelCardTotalIO.readPrimitiveTypesAndStringsFromFile(filePath);
        String consoleOutput = byteArrayOutputStream.toString();

        // Postcondition: The primitive types and strings are successfully written to a file and read back without any data loss
        Assertions.assertTrue(consoleOutput.contains("Int value: " + intValue));
        Assertions.assertTrue(consoleOutput.contains("Double value: " + doubleValue));
        Assertions.assertTrue(consoleOutput.contains("Boolean value: " + booleanValue));
        Assertions.assertTrue(consoleOutput.contains("String value: " + stringValue));
    }
}
