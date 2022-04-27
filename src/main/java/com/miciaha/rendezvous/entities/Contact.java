package com.miciaha.rendezvous.entities;

public class Contact {
    private int ID;
    private String Name;
    private String Email;

    public Contact(int id, String name, String email){
        ID = id;
        Name = name;
        Email = email;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setID(int id){
        ID = id;
    }

    public void setName(String name){
        Name = name;
    }

    public void setEmail(String email){
        Email = email;
    }
}
