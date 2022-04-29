package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;

/**
 * The type Country.
 */
public class Country implements DBEntity {
    private int ID;
    private String Name;

    /**
     * Instantiates a new Country.
     *
     * @param id   the id
     * @param name the name
     */
    public Country(int id, String name) {
        ID = id;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName() {
        return Name;
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
}
