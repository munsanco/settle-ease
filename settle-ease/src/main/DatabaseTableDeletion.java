package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableDeletion {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:sqlite:/Users/mundosanchez/GitHub/settle-ease/settle-ease/src/test/resources/FuelData.db";

        // Table name to delete
        String tableName = "FuelData";

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(url);

            // Create a statement
            Statement statement = connection.createStatement();

            // Delete the table
            String deleteTableQuery = "DROP TABLE IF EXISTS " + tableName;
            statement.execute(deleteTableQuery);

            // Close the resources
            statement.close();
            connection.close();

            System.out.println("Table " + tableName + " deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

