package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;
import com.miciaha.rendezvous.persistence.DivisionDbManager;

/**
 * The type Customer.
 */
public class Customer implements DBEntity {
    private int id;
    private String name;
    private String address;
    private String postCode;
    private String phone;
    private Division division;

    /**
     * Instantiates a new Customer.
     */
    public Customer() {

    }

    /**
     * Instantiates a new Customer.
     *
     * @param id         the id
     * @param name       the name
     * @param address    the address
     * @param postCode   the post code
     * @param phone      the phone
     * @param divisionID the division id
     */
    public Customer(int id, String name, String address, String postCode, String phone, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.phone = phone;
        division = DivisionDbManager.getDivision(divisionID);
    }

    /**
     * Instantiates a new Customer.
     *
     * @param name     the name
     * @param address  the address
     * @param postCode the post code
     * @param phone    the phone
     * @param division the division
     */
    public Customer(String name, String address, String postCode, String phone, Division division) {
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.phone = phone;
        this.division = division;
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
     * Get address string.
     *
     * @return the string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get post code string.
     *
     * @return the string
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Get phone string.
     *
     * @return the string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get division division.
     *
     * @return the division
     */
    public Division getDivision() {
        return division;
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
     * Set address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set post code.
     *
     * @param code the code
     */
    public void setPostCode(String code) {
        postCode = code;
    }

    /**
     * Set phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Set division.
     *
     * @param division the division
     */
    public void setDivision(Division division) {
        this.division = division;
    }

}
