package com.miciaha.rendezvous.entities;

import com.miciaha.rendezvous.interfaces.DBEntity;
import com.miciaha.rendezvous.persistence.ContactDbManager;
import com.miciaha.rendezvous.persistence.CustomerDbManager;
import com.miciaha.rendezvous.persistence.UserDbManager;

import java.time.LocalDateTime;

/**
 * The type Appointment.
 */
public class Appointment implements DBEntity {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private final User user;
    private Customer customer;
    private Contact contact;

    /**
     * Instantiates a new Appointment.
     *
     * @param id          the id
     * @param title       the title
     * @param description the description
     * @param location    the location
     * @param type        the type
     * @param start       the start
     * @param end         the end
     * @param userID      the user id
     * @param customerID  the customer id
     * @param contactID   the contact id
     */
    public Appointment(int id, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, int userID, int customerID, int contactID) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        user = UserDbManager.getUser(userID);
        customer = CustomerDbManager.getCustomer(customerID);
        contact = ContactDbManager.getContact(contactID);
    }

    /**
     * Instantiates a new Appointment.
     *
     * @param title       the title
     * @param description the description
     * @param location    the location
     * @param type        the type
     * @param start       the start
     * @param end         the end
     * @param userID      the user id
     * @param customerID  the customer id
     * @param contactID   the contact id
     */
    public Appointment(String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, int userID, int customerID, int contactID) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        user = UserDbManager.getUser(userID);
        customer = CustomerDbManager.getCustomer(customerID);
        contact = ContactDbManager.getContact(contactID);
    }

    public int getId() {
        return id;
    }

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description string.
     *
     * @return the string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get location string.
     *
     * @return the string
     */
    public String getLocation() {
        return location;
    }

    /**
     * Get type string.
     *
     * @return the string
     */
    public String getType() {
        return type;
    }

    /**
     * Get start local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Get end local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Get user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Get customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Get contact.
     *
     * @return the contact
     */
    public Contact getContact() {
        return contact;
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
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Set type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set start.
     *
     * @param startDate the start date
     */
    public void setStart(LocalDateTime startDate) {
        start = startDate;
    }

    /**
     * Set end.
     *
     * @param endDate the end date
     */
    public void setEnd(LocalDateTime endDate) {
        end = endDate;
    }

    /**
     * Set customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Set contact.
     *
     * @param contact the contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }


}
