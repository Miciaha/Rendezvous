package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.persistence.DivisionDbManager;

public class Customer {
    private int ID;
    private String Name;
    private String Address;
    private String PostCode;
    private String Phone;
    private Division Division;

    public Customer(){

    }

    public Customer(int id, String name, String address, String postCode, String phone, int divisionID){
        ID = id;
        Name = name;
        Address = address;
        PostCode = postCode;
        Phone = phone;
        Division = DivisionDbManager.getDivision(divisionID);
    }

    public Customer(String name, String address, String postCode, String phone, Division division){
        Name = name;
        Address = address;
        PostCode = postCode;
        Phone = phone;
        Division = division;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return Name;
    }

    public String getAddress(){
            return Address;
    }

    public String getPostCode(){
            return PostCode;
    }

    public String getPhone(){
            return Phone;
    }

    public Division getDivision(){
            return Division;
    }

    public void setID(int id){
        ID = id;
    }

    public void setName(String name){
        Name = name;
    }

    public void setAddress(String address){
        Address = address;
    }

    public void setPostCode(String code){
        PostCode = code;
    }

    public void setPhone(String phone){
        Phone = phone;
    }

    public void setDivision(Division division){
        Division = division;
    }

}
