package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;

/**
 * The type Contact.
 */
public class Contact implements DBEntity {
    private final int id;
    private final String name;
    private final String email;

    /**
     * Instantiates a new Contact.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     */
    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
