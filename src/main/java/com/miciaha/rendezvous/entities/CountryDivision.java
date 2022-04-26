package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.persistence.CountryDbManager;
import com.miciaha.rendezvous.persistence.DivisionDbManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

// "Caching" locations makes sense since these values are never going to change.
public class CountryDivision {
    private static  ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static  ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    public static void initializeLocations(){
        allCountries = CountryDbManager.getAllCountries();
        allDivisions = DivisionDbManager.getAllDivisions();
    }

    public static Division getDivision(String divisionName){
        for (Division division: allDivisions){
            if(Objects.equals(division.getName(), divisionName)){
                return division;
            }
        }
        return null;
    }

    public static Country getCountry(int countryID){
        for (Country country: allCountries){
            if (country.getID() == countryID){
                return country;
            }
        }
        return null;
    }

    public static ObservableList<Country> getAllCountries(){
        return allCountries;
    }

    public static ObservableList<Division> getAllDivisions(){
        return allDivisions;
    }
}
