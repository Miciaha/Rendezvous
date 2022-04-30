package com.miciaha.rendezvous.utilities.database;

import java.sql.*;
import java.util.Objects;

/**
 * The type SqlDatabase connection.
 */
public class SQLDBConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=rendezvous;user=sa;password=Pa$$w0rd;Encrypt=False";

    /**
     * Run query result set.
     *
     * @param query the query
     * @return the result set
     * @throws SQLException the sql exception
     */
    public static ResultSet runQuery(String query) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs;
        } else {
            return null;
        }
    }

    /**
     * Update db boolean.
     *
     * @param query the query
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public static boolean updateDB(String query) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        return stmt.executeUpdate() > 0;
    }

    /**
     * Get max id int.
     *
     * @param tableName the table name
     * @return the int
     */
    public static int getMaxId(String tableName) {

        String idCol = null, table = null;

        switch (tableName) {
            case "Appointment":
                idCol = "Appointment_ID";
                table = "APPOINTMENTS";
                break;
            case "Customer":
                idCol = "Customer_ID";
                table = "CUSTOMERS";
                break;
            case "Contact":
                idCol = "Contact_ID";
                table = "CONTACTS";
                break;
        }
        if (idCol != null) {
            String query = "SELECT MAX(" + idCol + ") AS num FROM " + table;
            int maxID = 0;
            try {
                ResultSet rs = SQLDBConnection.runQuery(query);
                maxID = Objects.requireNonNull(rs).getInt("num");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return maxID + 1;
        }
        return 0;
    }
}
