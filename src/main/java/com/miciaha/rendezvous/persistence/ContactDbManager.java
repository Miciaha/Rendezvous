package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Contact;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Contact db manager.
 */
public class ContactDbManager implements DbManager<Contact> {
    @Override
    public boolean Create(Contact type) {
        return false;
    }

    /**
     * Get contact.
     *
     * @param contactID the contact id
     * @return the contact
     */
    public static Contact getContact(int contactID) {
        String statement = "SELECT * FROM CONTACTS WHERE Contact_ID = " + contactID;

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                return (new Contact(contactID, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all contacts observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String statement = "SELECT * FROM CONTACTS";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                do {
                    int id = rs.getInt("Contact_ID");
                    String name = rs.getString("Contact_Name");
                    String email = rs.getString("Email");

                    contacts.add(new Contact(id, name, email));
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public boolean Update(Contact type) {
        return false;
    }

    @Override
    public boolean Delete(Contact type) {
        return false;
    }
}
