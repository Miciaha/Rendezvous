package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;
import com.miciaha.rendezvous.persistence.DivisionDbManager;

/**
 * The type Customer.
 */
public class Customer implements DBEntity {
    private int ID;
    private String Name;
    private String Address;
    private String PostCode;
    private String Phone;
    private Division Division;

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
        ID = id;
        Name = name;
        Address = address;
        PostCode = postCode;
        Phone = phone;
        Division = DivisionDbManager.getDivision(divisionID);
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
        Name = name;
        Address = address;
        PostCode = postCode;
        Phone = phone;
        Division = division;
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
     * Get address string.
     *
     * @return the string
     */
    public String getAddress() {
        return Address;
    }

    /**
     * Get post code string.
     *
     * @return the string
     */
    public String getPostCode() {
        return PostCode;
    }

    /**
     * Get phone string.
     *
     * @return the string
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * Get division division.
     *
     * @return the division
     */
    public Division getDivision() {
        return Division;
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
     * Set address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     * Set post code.
     *
     * @param code the code
     */
    public void setPostCode(String code) {
        PostCode = code;
    }

    /**
     * Set phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     * Set division.
     *
     * @param division the division
     */
    public void setDivision(Division division) {
        Division = division;
    }

}
