package main;
import java.sql.*;


public class InsertPayrollTable {
    public static void insertDataIntoFuelCardTable(String dbFilePath) {
        try (Connection conn = getConnection(dbFilePath);
             Statement stmt = conn.createStatement()) {

            // Insert data into FuelCard table
            insertData(stmt, "086", "Employee A");
            insertData(stmt, "097", "Employee B");
            insertData(stmt, "140", "Employee C");
            insertData(stmt, "089", "Employee D");
            insertData(stmt, "104", "Employee E");
            insertData(stmt, "157", "Employee F");
            insertData(stmt, "017", "Employee G");
            insertData(stmt, "131", "Employee H");
            insertData(stmt, "012", "Employee I");
            insertData(stmt, "020", "Employee J");
            insertData(stmt, "128", "Employee K");
            insertData(stmt, "081", "Employee L");
            insertData(stmt, "132", "Employee M");
            insertData(stmt, "152", "Employee N");
            insertData(stmt, "031", "Employee O");
            insertData(stmt, "114", "Employee P");
            insertData(stmt, "048", "Employee Q");
            insertData(stmt, "127", "Employee R");
            insertData(stmt, "138", "Employee S");
            insertData(stmt, "154", "Employee T");
            insertData(stmt, "014", "Employee U");
            insertData(stmt, "109", "Employee V");
            insertData(stmt, "102", "Employee W");
            insertData(stmt, "105", "Employee X");
            insertData(stmt, "155", "Employee Y");
            insertData(stmt, "116", "Employee Z");
            insertData(stmt, "013", "Employee AA");
            insertData(stmt, "130", "Employee AB");
            insertData(stmt, "133", "Employee AC");
            insertData(stmt, "103", "Employee AD");
            insertData(stmt, "087", "Employee AE");

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Connection getConnection(String dbFilePath) throws SQLException {
        String url = "jdbc:sqlite:" + dbFilePath;
        return DriverManager.getConnection(url);
    }

    private static void insertData(Statement stmt, String cardNumber, String employeeName) throws SQLException {
        String insertQuery = "INSERT INTO FuelCard (cardNumber, employeeName) " +
                "VALUES ('" + cardNumber + "', '" + employeeName + "')";
        stmt.executeUpdate(insertQuery);
    }
}
