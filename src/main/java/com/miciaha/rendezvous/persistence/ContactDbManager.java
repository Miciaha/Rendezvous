package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Contact;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDbManager implements DbManager<Contact> {
    @Override
    public boolean Create(Contact type) {
        return false;
    }

    public static Contact getContact(int contactID){
        String statement = "SELECT * FROM CONTACTS WHERE Contact_ID = " + contactID;

        try{
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs == null)){
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                return (new Contact(contactID, name, email));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
