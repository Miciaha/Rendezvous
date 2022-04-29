package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Customer db manager.
 */
public class CustomerDbManager implements DbManager<Customer> {
    @Override
    public boolean Create(Customer customer) {

        int divisionID = customer.getDivision().getID();

        String query = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone," +
                "Create_Date,Created_By,Last_Update,Last_Updated_By, Division_ID)" +
                "VALUES (" + customer.getID() + ", '" + customer.getName() + "', '" + customer.getAddress() + "', '" + customer.getPostCode() +
                "', '" + customer.getPhone() + "', SYSUTCDATETIME(), '" + CurrentUser.getName() + "', SYSUTCDATETIME(), '" +
                CurrentUser.getName() + "', " + divisionID + ")";

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get customer customer.
     *
     * @param customerID the customer id
     * @return the customer
     */
    public static Customer getCustomer(int customerID) {
        String statement = "SELECT * FROM CUSTOMERS WHERE Customer_ID = " + customerID;

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                return (new Customer(id, name, address, postCode, phone, divisionID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all customers observable list.
     *
     * @return the observable list
     */
    public ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String statement = "SELECT * FROM CUSTOMERS";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                do {
                    int id = rs.getInt("Customer_ID");
                    String name = rs.getString("Customer_Name");
                    String address = rs.getString("Address");
                    String postCode = rs.getString("Postal_Code");
                    String phone = rs.getString("Phone");
                    int divisionID = rs.getInt("Division_ID");

                    Customer customer = new Customer(id, name, address, postCode, phone, divisionID);
                    customers.add(customer);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean Update(Customer customer) {

        int divisionID = customer.getDivision().getID();

        String query = "UPDATE CUSTOMERS " +
                "SET Customer_Name = '" + customer.getName() + "', Address = '" + customer.getAddress() + "'," +
                " Postal_Code = '" + customer.getPostCode() + "', Phone = '" + customer.getPhone() + "'," +
                " Last_Update = SYSUTCDATETIME(), Last_Updated_By = '" + CurrentUser.getName() + "', Division_ID = " + divisionID +
                " WHERE Customer_ID = " + customer.getID();

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Delete(Customer customer) {

        String query = "DELETE FROM CUSTOMERS Where Customer_ID = " + customer.getID();

        try {
            SQLDBConnection.runQuery(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
