package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;

/**
 * The type User.
 */
public class User implements DBEntity {
    private final int ID;
    private final String Username;

    /**
     * Instantiates a new User.
     *
     * @param id   the id
     * @param name the name
     */
    public User(int id, String name) {
        ID = id;
        Username = name;
    }

    public int getID() {
        return ID;
    }

    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername() {
        return Username;
    }

}