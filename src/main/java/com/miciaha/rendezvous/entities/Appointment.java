package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.persistence.ContactDbManager;
import com.miciaha.rendezvous.persistence.CustomerDbManager;
import com.miciaha.rendezvous.persistence.UserDbManager;

import java.time.LocalDateTime;

public class Appointment {
    private int ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private User User;
    private Customer Customer;
    private Contact Contact;

    public Appointment(int id, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, int userID, int customerID, int contactID){

        ID = id;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        User = UserDbManager.getUser(userID);
        Customer = CustomerDbManager.getCustomer(customerID);
        Contact = ContactDbManager.getContact(contactID);
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

    public LocalDateTime getStart(){
        return Start;
    }

    public LocalDateTime getEnd(){
        return End;
    }

    public User getUser(){return User;}

    public Customer getCustomer(){
        return Customer;
    }

    public Contact getContact(){
        return Contact;
    }

    public void setID(int id){
        ID = id;
    }

    public void setTitle(String title){
        Title = title;
    }

    public void setDescription(String description){
        Description = description;
    }

    public void setLocation(String location){
        Location = location;
    }

    public void setType(String type){
        Type = type;
    }

    public void setStart(LocalDateTime startDate){
        Start = startDate;
    }

    public void setEnd(LocalDateTime endDate){
        End = endDate;
    }

    public void setUser(User user){
        User = user;
    }

    public void setCustomer(Customer customer){
        Customer = customer;
    }

    public void setContact(Contact contact){
        Contact = contact;
    }



}
