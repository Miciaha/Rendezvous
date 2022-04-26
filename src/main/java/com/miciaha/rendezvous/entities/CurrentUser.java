package com.miciaha.rendezvous.entities;

public class CurrentUser {
    private static User currentUser = null;

    public static String getName(){
        return currentUser.getUsername();
    }

    public static int getID() {return currentUser.getID();}

    public static void setCurrentUser(User user){
        currentUser = user;
    }
}
