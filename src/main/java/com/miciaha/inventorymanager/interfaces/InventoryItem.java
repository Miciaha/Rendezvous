package com.miciaha.inventorymanager.interfaces;

public interface InventoryItem {
    int getId();
    String getName();
    int getStock();
    double getPrice();
    int getMax();
    int getMin();
}
