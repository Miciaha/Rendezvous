
package com.miciaha.rendezvous.entities;

public class User {
    private final int ID;
    private final String Username;

    public User(int id, String name){
        ID = id;
        Username = name;
    }

    public int getID() {
        return ID;
    }

    public String getUsername(){
        return Username;
    }

}