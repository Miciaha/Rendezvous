package com.miciaha.inventorymanager.inventory.parts;

public class InHouse extends Part implements PartType {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public void setMachineId(int id){
        machineId = id;
    }

    public int getMachineId(){
        return machineId;
    }

    public String getTypeDetail(){
        return String.valueOf(machineId);
    }
}
