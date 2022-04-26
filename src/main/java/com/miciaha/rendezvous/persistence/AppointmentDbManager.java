package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;

import java.sql.ResultSet;

public class AppointmentDbManager implements DbManager<Appointment> {
    @Override
    public boolean Create(Appointment app) {

        int newID = getMaxId() + 1;
        int customerID = app.getCustomer().getID();
        int contactID = app.getContact().getID();

        String query = "INSERT INTO APPOINTMENTS (Appointment_ID ,Title ,Description, Location , Type, Start," +
                                                " End ,Create_Date,Created_By ,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)" +
                        "VALUES ("+ newID + ", '" + app.getTitle() + "', '" + app.getDescription() + "', '" + app.getType() + "', '" +  app.getLocation() +
                            "', '" + app.getStart() + "', '" + app.getEnd() + "', SYSUTCDATETIME(), '" +  CurrentUser.getName() + "', SYSUTCDATETIME(), '" +
                                    CurrentUser.getName() + "', " +  customerID + ", " +  CurrentUser.getID() + ", " + contactID + " )";

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Update(Appointment app) {

        int customerID = app.getCustomer().getID();
        int contactID = app.getContact().getID();

        String query = "UPDATE APPOINTMENTS" +
                        "SET Title = '" + app.getTitle() + "', Description = '" + app.getDescription() + "', Type = '" + app.getType() +
                            "', Start = '" + app.getStart() + "', End = '" + app.getEnd() + "', Last_Update = SYSUTCDATETIME(), " +
                            " Last_Updated_By = '" + CurrentUser.getName() + "', Customer_ID = " + customerID + ", User_ID = " +CurrentUser.getID() +
                            ", Contact_ID = " + contactID +
                        "WHERE Appointment_ID = " + app.getID();

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Delete(Appointment app) {
        String query = "DELETE FROM APPOINTMENTS Where Appointment_ID = " + app.getID();

        try {
            SQLDBConnection.runQuery(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private int getMaxId(){
        String query = "SELECT MAX(Appointment_ID) AS num FROM APPOINTMENTS";
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
