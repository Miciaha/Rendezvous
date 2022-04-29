package com.miciaha.rendezvous.persistence;


import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.entities.User;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User db manager.
 */
public class UserDbManager {

    /**
     * The type Validator.
     */
    public static class Validator {
        /**
         * Login boolean.
         *
         * @param user the user
         * @param pw   the pw
         * @return the boolean
         * @throws SQLException the sql exception
         */
// In the future, we would want to make password storage and retrieval more secure than this...
        // I would also want to decouple these calls from the SQL implementation; this would make it easier to switch dbContexts as needed
        public static boolean login(String user, String pw) throws SQLException {
            String statement = "SELECT * FROM USERS WHERE User_Name like '" + user + "'";
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (rs == null) {
                return false;
            }
            String password = rs.getString("Password");

            if (pw.equals(password)) {
                int id = rs.getInt("User_ID");
                User currentUser = new User(id, user);
                CurrentUser.setCurrentUser(currentUser);
            }

            return pw.equals(password);
        }
    }

    /**
     * Get user user.
     *
     * @param userID the user id
     * @return the user
     */
    public static User getUser(int userID) {
        String statement = "SELECT * FROM USERS WHERE User_ID = " + userID;

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                int id = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                return (new User(id, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
