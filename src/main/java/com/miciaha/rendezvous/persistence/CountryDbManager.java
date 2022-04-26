package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Country;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDbManager {

    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String statement =  "SELECT * FROM COUNTRIES";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null)){
                do {
                    String name = rs.getString("Country");
                    int id = rs.getInt("Country_ID");

                    Country country = new Country(id, name);
                    countries.add(country);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
