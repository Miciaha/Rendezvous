package com.miciaha.inventorymanager.inventory.parts;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String CompanyName) {
        super(id, name, price, stock, min, max);
        this.companyName = CompanyName;
    }

    public void setCompanyName(String name){
        companyName = name;
    }

    public String getCompanyName(){
        return companyName;
    }
}
