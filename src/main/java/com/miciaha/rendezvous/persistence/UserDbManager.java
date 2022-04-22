package com.miciaha.rendezvous.persistence;


import com.miciaha.rendezvous.interfaces.Query;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDbManager {

    public static class Validator implements Query {
        // In the future, we would want to make password storage and retrieval more secure than this...
        // I would also want to decouple these calls from the SQL implementation; this would make it easier to switch dbContexts as needed
        public static boolean userPwMatch(String user, String pw) throws SQLException {
            String statement =  "SELECT * FROM \"User\" WHERE User_Name like '" + user + "'";
            ResultSet rs =  SQLDBConnection.runQuery(statement);

            if(rs == null){
                return false;
            }
            String password = rs.getString("Password");

            return pw.equals(password);
        }
    }
}
