package com.miciaha.rendezvous.persistence;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.DbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Appointment db manager.
 */
public class AppointmentDbManager implements DbManager<Appointment> {
    @Override
    public boolean Create(Appointment app) {

        int newID = getMaxId() + 1;
        int customerID = app.getCustomer().getID();
        int contactID = app.getContact().getID();
        Timestamp utcStart = LocaleHandler.DateTimeHelper.localDateToTimestampUTC(app.getStart());
        Timestamp utcEnd = LocaleHandler.DateTimeHelper.localDateToTimestampUTC(app.getEnd());

        String query = "INSERT INTO APPOINTMENTS ([Appointment_ID] ,[Title] ,[Description], [Location] , [Type], [Start]," +
                " [End],[Create_Date], [Created_By] , [Last_Update], [Last_Updated_By], [Customer_ID], [User_ID],[Contact_ID])" +
                "VALUES (" + newID + ", '" + app.getTitle() + "', '" + app.getDescription() + "', '" + app.getType() + "', '" + app.getLocation() +
                "', '" + utcStart + "', '" + utcEnd + "', SYSUTCDATETIME(), '" + CurrentUser.getName() + "', SYSUTCDATETIME(), '" +
                CurrentUser.getName() + "', " + customerID + ", " + CurrentUser.getID() + ", " + contactID + " )";

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Update(Appointment app) {

        int customerID = app.getCustomer().getID();
        int contactID = app.getContact().getID();
        Timestamp utcStart = LocaleHandler.DateTimeHelper.localDateToTimestampUTC(app.getStart());
        Timestamp utcEnd = LocaleHandler.DateTimeHelper.localDateToTimestampUTC(app.getEnd());

        String query = "UPDATE APPOINTMENTS " +
                "SET [Title] = '" + app.getTitle() + "', [Description] = '" + app.getDescription() + "', [Type] = '" + app.getType() +
                "', [Start] = '" + utcStart + "', [End] = '" + utcEnd + "', [Last_Update] = SYSUTCDATETIME(), " +
                " [Last_Updated_By] = '" + CurrentUser.getName() + "', [Customer_ID] = " + customerID + ", [User_ID] = " + CurrentUser.getID() +
                ", [Contact_ID] = " + contactID +
                "WHERE [Appointment_ID] = " + app.getID();

        try {
            return SQLDBConnection.updateDB(query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get customer appointments array list.
     *
     * @param customer the customer
     * @return the array list
     */
    public ArrayList<Appointment> getCustomerAppointments(Customer customer) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String statement = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = " + customer.getID();

        if (getAppointmentQuery(statement, appointments)) {
            return appointments;
        }
        return null;
    }

    /**
     * Get all appointments observable list.
     *
     * @return the observable list
     */
    public ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String statement = "SELECT * FROM APPOINTMENTS";
        if (getAppointmentQuery(statement, appointments)) {
            return appointments;
        }
        return null;
    }

    /**
     * Get user appointments array list.
     *
     * @return the array list
     */
    public ArrayList<Appointment> getUserAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String statement = "SELECT * FROM APPOINTMENTS WHERE User_ID = " + CurrentUser.getID();
        if (getAppointmentQuery(statement, appointments)) {
            return appointments;
        }
        return null;
    }

    private static boolean getAppointmentQuery(String statement, List<Appointment> appointments) {
        try {
            ResultSet rs = SQLDBConnection.runQuery(statement);

            if (!(rs == null)) {
                do {
                    int id = rs.getInt("Appointment_ID");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String location = rs.getString("Location");
                    String type = rs.getString("Type");
                    Timestamp startDateTime = rs.getTimestamp("Start");
                    Timestamp endDateTime = rs.getTimestamp("End");
                    int customerID = rs.getInt("Customer_ID");
                    int userID = rs.getInt("User_ID");
                    int contactID = rs.getInt("Contact_ID");


                    Appointment appointment = new Appointment(id, title, description, location, type,
                            LocaleHandler.DateTimeHelper.utcToLocalDateTime(startDateTime),
                            LocaleHandler.DateTimeHelper.utcToLocalDateTime(endDateTime),
                            userID, customerID, contactID);

                    appointments.add(appointment);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean deleteAppointmentStatement(String statement) {
        try {
            SQLDBConnection.runQuery(statement);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean Delete(Appointment app) {
        String statement = "DELETE FROM APPOINTMENTS Where Appointment_ID = " + app.getID();
        return deleteAppointmentStatement(statement);
    }

    /**
     * Delete boolean.
     *
     * @param customer the customer
     * @return the boolean
     */
    public boolean Delete(Customer customer) {
        String statement = "DELETE FROM APPOINTMENTS Where Customer_ID = " + customer.getID();
        return deleteAppointmentStatement(statement);
    }

    private int getMaxId() {
        String query = "SELECT MAX(Appointment_ID) AS num FROM APPOINTMENTS";
        int maxID = 0;
        try {
            ResultSet rs = SQLDBConnection.runQuery(query);
            maxID = rs.getInt("num");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return maxID;
    }
}
