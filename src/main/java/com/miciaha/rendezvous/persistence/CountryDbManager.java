package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Country;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDbManager {

    public static ObservableList<String> getCountryList() {
        ObservableList<String> countries = FXCollections.observableArrayList();
        String statement =  "SELECT * FROM COUNTRIES";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null)){
                do {
                    String name = rs.getString("Country");

                    countries.add(name);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public static Country getCountry(String country){
        String statement = "SELECT * FROM COUNTRIES WHERE Country like '" + country + "'";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null)){
                String name = rs.getString("Country");
                int id = rs.getInt("Country_ID");

                return (new Country(id, name));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Country getCountry(int countryID){
        String statement = "SELECT * FROM COUNTRIES WHERE Country_ID = " + countryID;

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null)){
                String name = rs.getString("Country");
                int id = rs.getInt("Country_ID");

               return (new Country(id, name));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
