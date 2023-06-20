
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseTable {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:/Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/FuelData.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String fuelCard = "CREATE TABLE IF NOT EXISTS FuelCard (" +
                    "id INTEGER PRIMARY KEY," +
                    "cardNumber TEXT" +
                    ");";

            stmt.execute(fuelCard);

            System.out.println("Table 'FuelCard' created successfully.");

            String fuelData = "CREATE TABLE IF NOT EXISTS FuelData (" +
                    "id INTEGER PRIMARY KEY," +
                    "trxDate TEXT," +
                    "city TEXT," +
                    "state TEXT," +
                    "invoiceAmount TEXT," +
                    "fuelCardId INTEGER," +
                    "FOREIGN KEY(fuelCardId) REFERENCES FuelCard(id)" +
                    ");";

            stmt.execute(fuelData);

            System.out.println("Table 'FuelData' created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


