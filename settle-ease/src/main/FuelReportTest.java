package main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FuelReportTest {
    private FuelReport fuelReport;

    @BeforeEach
    void setUp() {
        fuelReport = new FuelReport();
        fuelReport.saveFuelReport("012", "05/11/23 10:37:00 AM", "Remington", "IN", "389.97");
        fuelReport.saveFuelReport("116", "05/11/23 4:08:00 PM", "Oak Creek", "WI", "246.48");
        fuelReport.saveFuelReport("104", "05/11/23 10:01:00 AM", "Daleville", "IN", "370.77");
        fuelReport.saveFuelReport("089", "05/11/23 5:35:00 AM", "New Haven", "IN", "464.07");
        fuelReport.saveFuelReport("140", "05/11/23 8:07:00 PM", "Roberts", "WI", "450.63");
        fuelReport.saveFuelReport("155", "05/11/23 5:01:00 PM", "Marengo", "OH", "433.18");
        fuelReport.saveFuelReport("017", "05/11/23 9:37:00 AM", "Ocala", "FL", "332.02");
        fuelReport.saveFuelReport("097", "05/11/23 9:43:00 PM", "Whiteland", "IN", "248.13");
        fuelReport.saveFuelReport("128", "05/11/23 10:18:00 AM", "Berkshire", "OH", "508.46");
        fuelReport.saveFuelReport("020", "05/11/23 9:08:00 AM", "Wytheville", "VA", "243.77");
    }

    @Test
    void testGetFuelCardRank() {
        int rank = fuelReport.getFuelCardRank("012");
        Assertions.assertEquals(5, rank);
    }
}
