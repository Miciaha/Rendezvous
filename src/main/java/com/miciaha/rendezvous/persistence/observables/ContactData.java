package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactData {
    public static ObservableList<Contact> contacts = FXCollections.observableArrayList();
}
