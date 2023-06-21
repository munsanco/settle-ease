package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableDeletion {
    public static void deleteTables(String dbFilePath) {
    	// Precondition: dbFilePath is a valid file path for the SQLite database
    	// Table names to delete
        String[] tableNames = {"FuelData", "FuelCard"};

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
            // Postcondition: A connection to the database is successfully established
            // and stored in the 'connection' variable
            // Create a statement
            Statement statement = connection.createStatement();
            // Postcondition: A statement object is successfully created and stored in the 'statement' variable

            // Delete the tables
            for (String tableName : tableNames) {
                String deleteTableQuery = "DROP TABLE IF EXISTS " + tableName;
                statement.execute(deleteTableQuery);
                System.out.println("Table " + tableName + " deleted successfully.");
            // Postcondition: The specified tables are deleted from the database

            }

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Exception handling for SQLException that may occur during the database operations
            // Prints the stack trace of the exception
        }
    }
}
