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
    private int ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private User User;
    private Customer Customer;
    private Contact Contact;

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

        ID = id;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        User = UserDbManager.getUser(userID);
        Customer = CustomerDbManager.getCustomer(customerID);
        Contact = ContactDbManager.getContact(contactID);
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

        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        User = UserDbManager.getUser(userID);
        Customer = CustomerDbManager.getCustomer(customerID);
        Contact = ContactDbManager.getContact(contactID);
    }

    public int getID() {
        return ID;
    }

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Get description string.
     *
     * @return the string
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Get location string.
     *
     * @return the string
     */
    public String getLocation() {
        return Location;
    }

    /**
     * Get type string.
     *
     * @return the string
     */
    public String getType() {
        return Type;
    }

    /**
     * Get start local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * Get end local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * Get user user.
     *
     * @return the user
     */
    public User getUser() {
        return User;
    }

    /**
     * Get customer customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return Customer;
    }

    /**
     * Get contact contact.
     *
     * @return the contact
     */
    public Contact getContact() {
        return Contact;
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
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Set description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Set location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * Set type.
     *
     * @param type the type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * Set start.
     *
     * @param startDate the start date
     */
    public void setStart(LocalDateTime startDate) {
        Start = startDate;
    }

    /**
     * Set end.
     *
     * @param endDate the end date
     */
    public void setEnd(LocalDateTime endDate) {
        End = endDate;
    }

    /**
     * Set user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        User = user;
    }

    /**
     * Set customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    /**
     * Set contact.
     *
     * @param contact the contact
     */
    public void setContact(Contact contact) {
        Contact = contact;
    }


}
