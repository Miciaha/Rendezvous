package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Division;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDbManager {

    public static ObservableList<String> getCountryDivisionsList(int countryID){
        ObservableList<String> divisions = FXCollections.observableArrayList();
        String statement = "SELECT * FROM [FIRST-LEVEL DIVISIONS] WHERE Country_ID = " + countryID;

        try{
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null))
            do {
                String divisionName = rs.getString("Division");
                divisions.add(divisionName);
            } while (rs.next());
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return divisions;

    }

    // TODO: Combine these queries together -- Are exactly the same aside from query statement

    public static Division getDivision(String division){
        String statement = "SELECT * FROM [FIRST-LEVEL DIVISIONS] WHERE Division like '" + division + "'";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs == null)){
                int id = rs.getInt("Division_ID");
                int countryId = rs.getInt("Country_ID");
                String divisionName = rs.getString("Division");

                return (new Division(id, divisionName, countryId));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public static Division getDivision(int divisionID){
        String statement = "SELECT * FROM [FIRST-LEVEL DIVISIONS] WHERE Division_ID = " + divisionID;

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs == null)){
                int id = rs.getInt("Division_ID");
                int countryId = rs.getInt("Country_ID");
                String divisionName = rs.getString("Division");

                return (new Division(id, divisionName, countryId));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
