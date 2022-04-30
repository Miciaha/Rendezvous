package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;

/**
 * The type User.
 */
public class User implements DBEntity {
    private final int id;
    private final String username;

    /**
     * Instantiates a new User.
     *
     * @param id   the id
     * @param name the name
     */
    public User(int id, String name) {
        this.id = id;
        username = name;
    }

    public int getId() {
        return id;
    }

    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername() {
        return username;
    }

}