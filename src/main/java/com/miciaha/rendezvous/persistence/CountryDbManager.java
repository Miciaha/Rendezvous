package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Country;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Country db manager.
 */
public class CountryDbManager {

    /**
     * Gets country list.
     *
     * @return the country list
     */
    public static ObservableList<String> getCountryList() {
        ObservableList<String> countries = FXCollections.observableArrayList();
        String statement = "SELECT * FROM COUNTRIES";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
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

    /**
     * Get country.
     *
     * @param country the country
     * @return the country
     */
    public static Country getCountry(String country) {
        String statement = "SELECT * FROM COUNTRIES WHERE Country like '" + country + "'";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                String name = rs.getString("Country");
                int id = rs.getInt("Country_ID");

                return (new Country(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get country.
     *
     * @param countryID the country id
     * @return the country
     */
    public static Country getCountry(int countryID) {
        String statement = "SELECT * FROM COUNTRIES WHERE Country_ID = " + countryID;

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                String name = rs.getString("Country");
                int id = rs.getInt("Country_ID");

                return (new Country(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
