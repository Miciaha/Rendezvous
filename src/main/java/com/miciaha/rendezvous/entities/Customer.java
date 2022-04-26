package com.miciaha.rendezvous.entities;

public class Customer {
    private int ID;
    private String Name;
    private String Address;
    private String PostCode;
    private String Phone;
    private Division Division;

    public Customer(){

    }

    public Customer(String name, String address, String postCode, String phone, String divisionName){
        Name = name;
        Address = address;
        PostCode = postCode;
        Phone = phone;
        Division = CountryDivision.getDivision(divisionName);
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

    public void setName(String name){
    }

    public void setAddress(String address){
    }

    public void setPostCode(String code){
    }

    public void setPhone(String phone){
    }

    public void setDivision(String division){
    }

}
