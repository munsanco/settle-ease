package main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataInserter {
    private String url;
    private int successCount;

    /**
     * Constructs a DataInserter object with the specified database file path.
     *
     * @param dbFilePath the path to the SQLite database file
     */
    public DataInserter(String dbFilePath) {
        url = "jdbc:sqlite:" + dbFilePath;
        successCount = 0;
    }

    /**
     * Inserts data into the FuelData table.
     *
     * @param cardNumber     the fuel card number
     * @param trxDate        the transaction date
     * @param city           the city
     * @param state          the state
     * @param invoiceAmount  the invoice amount
     * @throws SQLException if an error occurs during database operations
     */
    public void insertData(String cardNumber, String trxDate, String city, String state, String invoiceAmount) throws SQLException {
        // Precondition: cardNumber, trxDate, city, state, and invoiceAmount are not null
        String query = "INSERT INTO FuelData (CardNumber, TrxDate, City, State, InvoiceAmount) VALUES (?, ?, ?, ?, ?)";


        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
        	// Set the parameter values
            statement.setString(1, cardNumber);
            statement.setString(2, trxDate);
            statement.setString(3, city);
            statement.setString(4, state);
            statement.setString(5, invoiceAmount);

            // Execute the query and get the number of rows inserted
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                successCount += rowsInserted;
            }
        } catch (SQLException e) {
            throw new SQLException("Error inserting data into FuelData table: " + e.getMessage());
        }
    }

    /**
     * Retrieves the fuel data from the FuelData table in descending order of invoice amount.
     *
     * @return a list of FuelRow objects representing the sorted fuel data
     * @throws SQLException if an error occurs during database operations
     */
    public List<FuelRow> getSortedFuelData() throws SQLException {
        // Postcondition: returns a list of FuelRow objects in descending order of invoice amount
    	// Create an empty list to store the sorted fuel data
    	List<FuelRow> sortedRecords = new ArrayList<>();
    	 // Prepare the SQL query
        String query = "SELECT * FROM FuelData ORDER BY CAST(InvoiceAmount AS REAL) DESC";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        	// Iterate over the result set and create FuelRow objects
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("CardNumber");
                String trxDate = resultSet.getString("TrxDate");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                String invoiceAmount = resultSet.getString("InvoiceAmount");

                FuelRow fuelRow = new FuelRow(cardNumber, trxDate, city, state, invoiceAmount);
                sortedRecords.add(fuelRow);
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving sorted fuel data: " + e.getMessage());
        }

        return sortedRecords;
    }

    /**
     * Retrieves the employee name for the given fuel card number.
     *
     * @param fuelCardNumber the fuel card number
     * @return the employee name
     * @throws SQLException if an error occurs during database operations
     */
    public String getEmployeeName(String fuelCardNumber) throws SQLException {
    	// Precondition: fuelCardNumber is not null
    	String employeeName = null;
    	// Prepare the SQL query
        String query = "SELECT employeeName FROM FuelCard WHERE cardNumber = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
        	// Set the parameter value
        	statement.setString(1, fuelCardNumber);
        	// Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employeeName = resultSet.getString("employeeName");
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving employee name: " + e.getMessage());
        }

        return employeeName;
    }

    /**
     * Retrieves the top three employee names by aggregated invoice amount.
     *
     * @return a list of employee names
     * @throws SQLException if an error occurs during database operations
     */
    public List<String> getTopThreeEmployeeNames() throws SQLException {
    	// Postcondition: returns a list of employee names representing the top three by aggregated invoice amount
    	// Create an empty list to store the top three employee names
    	List<String> topThreeEmployees = new ArrayList<>();
    	// Prepare the SQL query
        String query = "SELECT FuelCard.employeeName, FuelCard.cardNumber, SUM(CAST(FuelData.InvoiceAmount AS REAL)) AS totalAmount " +
                "FROM FuelCard " +
                "JOIN FuelData ON FuelCard.cardNumber = FuelData.CardNumber " +
                "GROUP BY FuelCard.employeeName " +
                "ORDER BY totalAmount DESC " +
                "LIMIT 3";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
        	// Iterate over the result set and create employee name strings
            while (resultSet.next()) {
                String employeeName = resultSet.getString("employeeName");
                String cardNumber = resultSet.getString("cardNumber");
                double totalAmount = resultSet.getDouble("totalAmount");

                String employeeInfo = employeeName + " - card number " + cardNumber + " for a total of $" + String.format("%.2f", totalAmount);
                topThreeEmployees.add(employeeInfo);
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving top three employee names: " + e.getMessage());
        }

        return topThreeEmployees;
    }

    /**
     * Gets the count of successful data insertions.
     *
     * @return the count of successful data insertions
     */
    public int getSuccessCount() {
        return successCount;
    }
}
