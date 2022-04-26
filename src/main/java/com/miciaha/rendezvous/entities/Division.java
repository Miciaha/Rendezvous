package com.miciaha.rendezvous.entities;

public class Division {
    public int ID;
    public String Name;
    public Country Country;

    public Division(int id, String name, int country_ID){
        ID = id;
        Name = name;
        Country = CountryDivision.getCountry(country_ID);

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
}
