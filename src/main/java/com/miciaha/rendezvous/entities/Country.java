package com.miciaha.rendezvous.entities;

public class Country {
    private int ID;
    private String Name;

    public Country(int id, String name){
        ID = id;
        Name = name;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return Name;
    }

    public void setID(int id){
        ID = id;
    }

    public void setName(String name){
        Name = name;
    }
}
