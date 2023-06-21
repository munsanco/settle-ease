package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseTable {
    public static void createDatabaseTables(String dbFilePath) {
    	// Precondition: dbFilePath is a valid file path for the SQLite database
        String url = "jdbc:sqlite:" + dbFilePath;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String fuelCard = "CREATE TABLE IF NOT EXISTS FuelCard (" +
                    "id INTEGER PRIMARY KEY," +
                    "cardNumber TEXT," +
                    "employeeName TEXT" +
                    ");";

            stmt.execute(fuelCard);
            // Postcondition: The 'FuelCard' table is created successfully in the database

            System.out.println("Table 'FuelCard' created successfully.");

            String fuelData = "CREATE TABLE IF NOT EXISTS FuelData (" +
                    "id INTEGER PRIMARY KEY," +
                    "trxDate TEXT," +
                    "city TEXT," +
                    "state TEXT," +
                    "invoiceAmount TEXT," +
                    "cardNumber TEXT," +
                    "fuelCardId INTEGER," +
                    "FOREIGN KEY(fuelCardId) REFERENCES FuelCard(id)" +
                    ");";

            stmt.execute(fuelData);
            // Postcondition: The 'FuelData' table is created successfully in the database
            
            System.out.println("Table 'FuelData' created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
