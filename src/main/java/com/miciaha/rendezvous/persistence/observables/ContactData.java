package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.Contact;
import com.miciaha.rendezvous.persistence.ContactDbManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * The type Contact data.
 */
public class ContactData {
    /**
     * The Contacts list.
     */
    public static final ObservableList<Contact> contactsList = ContactDbManager.getAllContacts();

    /**
     * Get contact names list observable list.
     *
     * @return the observable list
     */
    public static ObservableList<String> getContactNamesList() {
        ObservableList<String> contacts = FXCollections.observableArrayList();
        for (Contact contact : contactsList) {
            contacts.add(contact.getName());
        }
        return contacts;
    }

    /**
     * Get contact id int.
     *
     * @param name the name
     * @return the int
     */
    public static int getContactID(String name) {
        for (Contact contact : contactsList) {
            if (Objects.equals(contact.getName(), name)) {
                return contact.getId();
            }
        }
        return 0;
    }

    /**
     * Get contact.
     *
     * @param name the name
     * @return the contact
     */
    public static Contact getContact(String name) {
        for (Contact contact : contactsList) {
            if (Objects.equals(contact.getName(), name)) {
                return contact;
            }
        }
        return null;
    }
}
