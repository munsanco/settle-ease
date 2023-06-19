package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseTable {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:/Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/FuelData.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS FuelData (" +
                    "id INTEGER PRIMARY KEY," +
                    "card TEXT," +
                    "trxDate TEXT," +
                    "city TEXT," +
                    "state TEXT," +
                    "invoiceAmount TEXT" +
                    ")";

            stmt.execute(sql);

            System.out.println("Table 'FuelData' created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}



