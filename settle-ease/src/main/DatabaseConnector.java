package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/FuelData.db");
            
            // Print a success message if the connection is successful
            System.out.println("Connected to the database!");

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }
}



