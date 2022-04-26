package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Division;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDbManager {

    public static ObservableList<Division> getAllDivisions(){
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        String statement =  "SELECT Division_ID, Division, Country_ID FROM [FIRST-LEVEL DIVISIONS]";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null))
                do {
                    int id = rs.getInt("Division_ID");
                    int countryId = rs.getInt("Country_ID");
                    String divisionName = rs.getString("Division");

                    Division division = new Division(id,divisionName,countryId);
                    divisions.add(division);
                } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;
    }
}
