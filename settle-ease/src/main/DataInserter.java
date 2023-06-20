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
     * @param cardNumber    the fuel card number
     * @param trxDate       the transaction date
     * @param city          the city
     * @param state         the state
     * @param invoiceAmount the invoice amount
     * @throws SQLException if an error occurs during database operations
     */
    public void insertData(String cardNumber, String trxDate, String city, String state, String invoiceAmount) throws SQLException {
        // Precondition: cardNumber, trxDate, city, state, and invoiceAmount are not null
        String query = "INSERT INTO FuelData (CardNumber, TrxDate, City, State, InvoiceAmount) VALUES (?, ?, ?, ?, ?)";

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

    /**
     * Retrieves the fuel data from the FuelData table in descending order of invoice amount.
     *
     * @return a list of FuelRow objects representing the sorted fuel data
     * @throws SQLException if an error occurs during database operations
     */
    public List<FuelRow> getSortedFuelData() throws SQLException {
        // Postcondition: returns a list of FuelRow objects in descending order of invoice amount
        List<FuelRow> sortedRecords = new ArrayList<>();

        String query = "SELECT * FROM FuelData ORDER BY CAST(InvoiceAmount AS REAL) DESC";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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
     * Retrieves the employee name based on the fuel card number.
     *
     * @param cardNumber the fuel card number
     * @return the employee name
     * @throws SQLException if an error occurs during database operations
     */
    public String getEmployeeName(String cardNumber) throws SQLException {
        String employeeName = null;
        String query = "SELECT employeeName FROM FuelCard WHERE cardNumber = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);

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
     * Retrieves the top three employee names based on aggregated invoice amounts from FuelData.
     *
     * @return a list of top three employee names
     * @throws SQLException if an error occurs during database operations
     */
    public List<String> getTopThreeEmployeeNames() throws SQLException {
        // Precondition: The FuelData table exists in the database
        // Perform the aggregation to get the total invoice amount by employee name
        String query = "SELECT employeeName, SUM(CAST(InvoiceAmount AS REAL)) AS totalAmount " +
                "FROM FuelData JOIN FuelCard ON FuelData.CardNumber = FuelCard.cardNumber " +
                "GROUP BY employeeName " +
                "ORDER BY totalAmount DESC " +
                "LIMIT 3";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            List<String> topThreeEmployeeNames = new ArrayList<>();
            while (resultSet.next()) {
                String employeeName = resultSet.getString("employeeName");
                topThreeEmployeeNames.add(employeeName);
            }

            return topThreeEmployeeNames;
        } catch (SQLException e) {
            throw new SQLException("Error retrieving top three employee names: " + e.getMessage());
        }
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

