package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInserter {
    private String url;
    private int successCount;

    public DataInserter(String dbFilePath) {
        url = "jdbc:sqlite:" + dbFilePath;
        successCount = 0;
    }

    public void insertData(String cardNumber, String trxDate, String city, String state, String invoiceAmount) throws SQLException {
        String query = "INSERT INTO FuelData (cardNumber, trxDate, city, state, invoiceAmount) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            statement.setString(2, trxDate);
            statement.setString(3, city);
            statement.setString(4, state);
            statement.setString(5, invoiceAmount);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                successCount += rowsInserted;
            }
        } catch (SQLException e) {
            throw new SQLException("Error inserting data into FuelData table: " + e.getMessage());
        }
    }

    public int getSuccessCount() {
        return successCount;
    }
}
