package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;

import java.sql.ResultSet;

public class CustomerDbManager implements DbManager<Customer> {
    @Override
    public boolean Create(Customer customer) {

        int newID = getMaxId() + 1;
        int divisionID = customer.getDivision().getID();

        String query = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone," +
                                    "Create_Date,Created_By,Last_Update,Last_Updated_By, Division_ID)" +
                        "VALUES ("+ newID + ", '" + customer.getName() + "', '" + customer.getAddress() + "', '" + customer.getPostCode() +
                            "', '" + customer.getPhone() + "', SYSUTCDATETIME(), '" +  CurrentUser.getName() + "', SYSUTCDATETIME(), '" +
                            CurrentUser.getName() + "', " +  divisionID + ")";

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // Cascade delete is set within the database to remove appointments related to the customer
    @Override
    public boolean Delete(Customer customer) {

        String query = "DELETE FROM APPOINTMENTS Where Customer_ID = " + customer.getID() + ";" +
                "DELETE FROM CUSTOMERS Where Customer_ID = " + customer.getID();

        try {
            SQLDBConnection.runQuery(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean Delete(int customerId){
        String query = "DELETE FROM APPOINTMENTS Where Customer_ID = " + customerId + ";" +
                "DELETE FROM CUSTOMERS Where Customer_ID = " + customerId + ";";

        try {
            SQLDBConnection.runQuery(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private int getMaxId(){
        String query = "SELECT MAX(Customer_ID) AS num FROM CUSTOMERS";
        int maxID = 0;
        try {
            ResultSet rs = SQLDBConnection.runQuery(query);
            maxID = rs.getInt("num");
        }catch (Exception e){
            e.printStackTrace();
        }

        return maxID;
    }
}
