package com.miciaha.rendezvous.entities;

public class Appointment {
    private int ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private String Start;
    private String End;
    private User user;
    private Customer customer;
    private Contact contact;

    public Appointment(){

    }

    public int getID(){
        return ID;
    }

    public String getTitle(){
        return Title;
    }

    public String getDescription(){
        return Description;
    }

    public String getLocation(){
        return Location;
    }

    public String getType(){
        return Type;
    }

    public String getStart(){
        return Start;
    }

    public String getEnd(){
        return End;
    }

    public Customer getCustomer(){
        return customer;
    }

    public Contact getContact(){
        return contact;
    }



}
