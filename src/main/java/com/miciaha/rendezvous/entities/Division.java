package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;
import com.miciaha.rendezvous.persistence.CountryDbManager;

/**
 * The type Division.
 */
public class Division implements DBEntity {
    private int ID;
    private String Name;
    private Country Country;

    /**
     * Instantiates a new Division.
     *
     * @param id         the id
     * @param name       the name
     * @param country_ID the country id
     */
    public Division(int id, String name, int country_ID) {
        ID = id;
        Name = name;
        Country = CountryDbManager.getCountry(country_ID);

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
     * Get country country.
     *
     * @return the country
     */
    public Country getCountry() {
        return Country;
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
     * Set country.
     *
     * @param country the country
     */
    public void setCountry(Country country) {
        Country = country;
    }
}

