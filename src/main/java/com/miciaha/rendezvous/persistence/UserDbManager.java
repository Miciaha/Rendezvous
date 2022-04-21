package com.miciaha.rendezvous.persistence;

import java.sql.*;

public class UserDbManager {

    static Connection conn = null;

    public UserDbManager(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public static boolean checkUserPassword(String name, String password) throws SQLException{
        PreparedStatement stmt =  conn.prepareStatement("SELECT Password FROM \"User\" WHERE User_Name like %?%",
                                    Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1,name);

        ResultSet rs = stmt.executeQuery();

        if(rs.next()){
            return rs.getString("Password").equals(password);
        }
        return false;
    }
}
