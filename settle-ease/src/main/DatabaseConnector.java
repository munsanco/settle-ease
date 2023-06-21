package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    
    public void connect(String filePath) {
    	// Precondition: filePath is a valid file path for the SQLite database
    	try {
            // Load the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            
            // Postcondition: A connection to the database is successfully established
            // Print a success message if the connection is successful
            System.out.println("You are now connected to the database!");

            // Close the connection
            connection.close();
            // Postcondition: The connection to the database is closed
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
            // Exception handling for ClassNotFoundException that may occur when loading the JDBC driver
            // Prints an error message and the stack trace of the exception
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
            // Exception handling for SQLException that may occur when establishing the database connection
            // Prints an error message and the stack trace of the exception
        }
    }
}


