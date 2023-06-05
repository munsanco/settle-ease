package main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuelRowTest {

    private FuelRow fuelRow;

    @BeforeEach
    public void setUp() {
        fuelRow = new FuelRow("12345", "2023-05-01", "City A", "State A", "50.00");
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The getCard() method returns the correct fuel card number.
    public void testGetCard() {
        String card = fuelRow.getCard();
        Assertions.assertEquals("12345", card);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The setCard() method correctly updates the fuel card number.
    public void testSetCard() {
        fuelRow.setCard("67890");
        String card = fuelRow.getCard();
        Assertions.assertEquals("67890", card);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The getTrxDate() method returns the correct transaction date.
    public void testGetTrxDate() {
        String trxDate = fuelRow.getTrxDate();
        Assertions.assertEquals("2023-05-01", trxDate);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The setTrxDate() method correctly updates the transaction date.
    public void testSetTrxDate() {
        fuelRow.setTrxDate("2023-05-02");
        String trxDate = fuelRow.getTrxDate();
        Assertions.assertEquals("2023-05-02", trxDate);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The getCity() method returns the correct city.
    public void testGetCity() {
        String city = fuelRow.getCity();
        Assertions.assertEquals("City A", city);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The setCity() method correctly updates the city.
    public void testSetCity() {
        fuelRow.setCity("City B");
        String city = fuelRow.getCity();
        Assertions.assertEquals("City B", city);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The getState() method returns the correct state.
    public void testGetState() {
        String state = fuelRow.getState();
        Assertions.assertEquals("State A", state);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The setState() method correctly updates the state.
    public void testSetState() {
        fuelRow.setState("State B");
        String state = fuelRow.getState();
        Assertions.assertEquals("State B", state);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The getInvoiceAmount() method returns the correct invoice amount.
    public void testGetInvoiceAmount() {
        String invoiceAmount = fuelRow.getInvoiceAmount();
        Assertions.assertEquals("50.00", invoiceAmount);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The setInvoiceAmount() method correctly updates the invoice amount.
    public void testSetInvoiceAmount() {
        fuelRow.setInvoiceAmount("75.00");
        String invoiceAmount = fuelRow.getInvoiceAmount();
        Assertions.assertEquals("75.00", invoiceAmount);
    }

    @Test
    // Precondition: The fuelRow object is properly initialized with the provided values.
    // Postcondition: The toString() method returns the expected string representation of the fuelRow object.
    public void testToString() {
        String expectedString = "FuelRow{card='12345', trxDate='2023-05-01', city='City A', state='State A', invoiceAmount='50.00'}";
        String actualString = fuelRow.toString();
        Assertions.assertEquals(expectedString, actualString);
    }
}
