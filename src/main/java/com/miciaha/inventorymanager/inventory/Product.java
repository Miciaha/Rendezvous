package com.miciaha.inventorymanager.inventory;

import com.miciaha.inventorymanager.inventory.parts.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts =  FXCollections.observableArrayList();;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int Id, String Name, double Price, int Stock, int Min, int Max){
        this.id = Id;
        this.name = Name;
        this.price = Price;
        this.stock = Stock;
        this.min = Min;
        this.max = Max;
    }

    public void setId(int ID){
        id = ID;
    }

    public void setName(String Name){
        name = Name;
    }

    public void setPrice(double Price){
        price = Price;
    }

    public void setStock(int Stock){
        stock = Stock;
    }

    public void setMin(int Min){
        min = Min;
    }

    public void setMax(int Max){
        max = Max;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getStock(){
        return stock;
    }

    public int getMin(){
        return min;
    }

    public int getMax(){
        return max;
    }

    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part part){
        associatedParts.remove(part);
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}
