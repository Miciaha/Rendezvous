package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.persistence.CustomerDbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * The type Customer data.
 */
public class CustomerData {
    private static final CustomerDbManager dbManager = new CustomerDbManager();
    /**
     * The Customer list.
     */
    public static ObservableList<Customer> customerList = dbManager.getAllCustomers();

    /**
     * Add customer boolean.
     *
     * @param customer the customer
     * @return the boolean
     */
    public static boolean addCustomer(Customer customer) {
        int id = SQLDBConnection.getMaxId("Customer");

        customer.setID(id);
        if (dbManager.Create(customer)) {
            return customerList.add(customer);
        }
        return false;
    }

    /**
     * Remove customer boolean.
     *
     * @param customer the customer
     * @return the boolean
     */
    public static boolean removeCustomer(Customer customer) {
        // remove appointments first!
        AppointmentData.removeCustomerAppointment(customer);
        if (dbManager.Delete(customer)) {
            return customerList.remove(customer);
        }
        return false;
    }

    /**
     * Update customer boolean.
     *
     * @param customer the customer
     * @return the boolean
     */
    public static boolean updateCustomer(Customer customer) {
        if (dbManager.Update(customer)) {
            for (Customer x : customerList) {
                if (x.getID() == customer.getID()) {
                    int index = customerList.indexOf(x);
                    customerList.set(index, customer);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get customer id int.
     *
     * @param name the name
     * @return the int
     */
    public static int getCustomerID(String name) {
        for (Customer customer : customerList) {
            if (Objects.equals(customer.getName(), name)) {
                return customer.getID();
            }
        }
        return 0;
    }

    /**
     * Get customer customer.
     *
     * @param name the name
     * @return the customer
     */
    public static Customer getCustomer(String name) {
        for (Customer customer : customerList) {
            if (Objects.equals(customer.getName(), name)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Get all customer names observable list.
     *
     * @return the observable list
     */
    public static ObservableList<String> getAllCustomerNames() {
        ObservableList<String> customers = FXCollections.observableArrayList();

        for (Customer customer : customerList) {
            customers.add(customer.getName());
        }
        return customers;
    }
}
