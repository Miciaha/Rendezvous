package com.miciaha.rendezvous.utilities.database;

import java.sql.*;

public class SQLDBConnection{
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=rendezvous;user=sa;password=Pa$$w0rd;Encrypt=False";

    public static ResultSet runQuery(String query) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement stmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return rs;
        }
        else {
            return null;
        }
    }

    public static boolean updateDB(String query) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement stmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        return stmt.executeUpdate() > 0;
    }
}
