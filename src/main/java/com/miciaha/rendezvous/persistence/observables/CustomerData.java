package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.persistence.CustomerDbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.ObservableList;

public class CustomerData {
    private static final CustomerDbManager dbManager = new CustomerDbManager();
    public static ObservableList<Customer> customerList = dbManager.getAllCustomers();

    public static boolean addCustomer(Customer customer){
        int id = SQLDBConnection.getMaxId("Customer");

        customer.setID(id);
        if(dbManager.Create(customer)){
            return customerList.add(customer);
        }
        return false;
    }

    public static boolean removeCustomer(Customer customer){
        if(dbManager.Delete(customer)){
            return customerList.remove(customer);
        }
        return false;
    }

    public static boolean updateCustomer(Customer customer){
        if(dbManager.Update(customer)){
            for(Customer x: customerList){
                if (x.getID() == customer.getID()){
                    customerList.remove(x);
                    return customerList.add(customer);
                }
            }
        }
        return false;
    }
}
