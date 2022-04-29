package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;

/**
 * The type Contact.
 */
public class Contact implements DBEntity {
    private int ID;
    private String Name;
    private String Email;

    /**
     * Instantiates a new Contact.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     */
    public Contact(int id, String name, String email) {
        ID = id;
        Name = name;
        Email = email;
    }

    public int getID() {
        return ID;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Set email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        Email = email;
    }
}
