package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableDeletion {
    public static void deleteTables(String dbFilePath) {
        // Table names to delete
        String[] tableNames = {"FuelData", "FuelCard"};

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);

            // Create a statement
            Statement statement = connection.createStatement();

            // Delete the tables
            for (String tableName : tableNames) {
                String deleteTableQuery = "DROP TABLE IF EXISTS " + tableName;
                statement.execute(deleteTableQuery);
                System.out.println("Table " + tableName + " deleted successfully.");
            }

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
