package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.persistence.CountryDbManager;

public class Division {
    private int ID;
    private String Name;
    private Country Country;

    public Division(int id, String name, int country_ID){
        ID = id;
        Name = name;
        Country = CountryDbManager.getCountry(country_ID);

    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return Name;
    }

    public Country getCountry(){
        return  Country;
    }

    public void setID(int id){
        ID = id;
    }

    public void setName(String name){
        Name = name;
    }

    public void setCountry(Country country){
        Country = country;
    }
}

