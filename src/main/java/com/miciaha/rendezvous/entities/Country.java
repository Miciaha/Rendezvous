package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;

/**
 * The type Country.
 */
public class Country implements DBEntity {
    private int id;
    private String name;

    /**
     * Instantiates a new Country.
     *
     * @param id   the id
     * @param name the name
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName() {
        return name;
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
