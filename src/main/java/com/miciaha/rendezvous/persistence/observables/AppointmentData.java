package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.persistence.AppointmentDbManager;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import javafx.collections.ObservableList;

public class AppointmentData {
    protected static final AppointmentDbManager dbManager = new AppointmentDbManager();
    public static ObservableList<Appointment> appointmentList = dbManager.getAllAppointments();

    public static boolean addAppointment(Appointment appointment){
        int id = SQLDBConnection.getMaxId("Appointment");

        appointment.setID(id);
        if(dbManager.Create(appointment)){
            return appointmentList.add(appointment);
        }
        return false;
    }

    public static boolean removeAppointment(Appointment appointment){
        if(dbManager.Delete(appointment)){
            return appointmentList.remove(appointment);
        }
        return false;
    }

    public static boolean updateAppointment(Appointment appointment){
        if(dbManager.Update(appointment)){
            for(Appointment x: appointmentList){
                if(x.getID() == appointment.getID()){
                    appointmentList.remove(x);
                    return appointmentList.add(appointment);
                }
            }
        }
        return false;
    }
}
