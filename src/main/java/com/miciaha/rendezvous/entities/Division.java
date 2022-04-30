package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;
import com.miciaha.rendezvous.persistence.CountryDbManager;

/**
 * The type Division.
 */
public class Division implements DBEntity {
    private int id;
    private String name;
    private Country country;

    /**
     * Instantiates a new Division.
     *
     * @param id         the id
     * @param name       the name
     * @param country_ID the country id
     */
    public Division(int id, String name, int country_ID) {
        this.id = id;
        this.name = name;
        country = CountryDbManager.getCountry(country_ID);

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
     * Get country.
     *
     * @return the country
     */
    public Country getCountry() {
        return country;
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

    /**
     * Set country.
     *
     * @param country the country
     */
    public void setCountry(Country country) {
        this.country = country;
    }
}

