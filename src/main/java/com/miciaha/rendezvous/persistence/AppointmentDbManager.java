package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

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

    public ObservableList<Appointment> getAllAppointments(){
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String statement =  "SELECT * FROM APPOINTMENTS";

        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if(!(rs==null)){
                do {
                    int id = rs.getInt("Appointment_ID");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String location = rs.getString("Location");
                    String type = rs.getString("Type");
                    LocalDateTime startDateTime = LocaleHandler.gmtToLocal(rs.getDate("Start"));
                    LocalDateTime endDateTime = LocaleHandler.gmtToLocal(rs.getDate("End"));
                    int customerID = rs.getInt("Customer_ID");
                    int userID = rs.getInt("User_ID");
                    int contactID = rs.getInt("Contact_ID");


                    Appointment appointment = new Appointment(id, title, description, location, type,
                                                    startDateTime, endDateTime, customerID, userID, contactID);
                    appointments.add(appointment);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
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
