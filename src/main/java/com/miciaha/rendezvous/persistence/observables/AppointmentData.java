package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.persistence.AppointmentDbManager;
import com.miciaha.rendezvous.utilities.Alerts;
import com.miciaha.rendezvous.utilities.database.SQLDBConnection;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * The type Appointment data.
 */
public class AppointmentData {
    /**
     * The constant dbManager.
     */
    protected static final AppointmentDbManager dbManager = new AppointmentDbManager();
    /**
     * The Appointment list.
     */
    public static ObservableList<Appointment> appointmentList = dbManager.getAllAppointments();

    /**
     * Add appointment boolean.
     *
     * @param appointment the appointment
     * @return the boolean
     */
    public static boolean addAppointment(Appointment appointment) {

        if (!hasTimeConflict(appointment)) {
            int id = SQLDBConnection.getMaxId("Appointment");

            appointment.setID(id);
            if (dbManager.Create(appointment)) {
                return appointmentList.add(appointment);
            }
        }
        return false;
    }

    private static boolean hasTimeConflict(Appointment appointment) {
        Customer customer = appointment.getCustomer();
        ArrayList<Appointment> appointments = dbManager.getCustomerAppointments(customer);

        if (!appointments.isEmpty()) {
            for (Appointment x : appointments) {
                int timeCompare = x.getEnd().compareTo(appointment.getStart());
                if (timeCompare >= 0) {
                    String startTime = LocaleHandler.DateTimeHelper.formatTime(x.getStart());
                    String endTime = LocaleHandler.DateTimeHelper.formatTime(x.getEnd());
                    new Alerts.CustomAlert.WarningAlert("Appointment Scheduling Conflict",
                            "Could not schedule appointment due to existing appointment from " +
                                    startTime + " - " + endTime);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove appointment boolean.
     *
     * @param appointment the appointment
     * @return the boolean
     */
    public static boolean removeAppointment(Appointment appointment) {
        if (dbManager.Delete(appointment)) {
            return appointmentList.remove(appointment);
        }
        return false;
    }

    /**
     * Remove customer appointment.
     *
     * @param customer the customer
     */
    public static void removeCustomerAppointment(Customer customer) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        if (dbManager.Delete(customer)) {
            for (Appointment appointment : appointmentList) {
                if (appointment.getCustomer().getID() == customer.getID()) {
                    appointments.add(appointment);
                }
            }
        }
        appointmentList.removeAll(appointments);
    }

    /**
     * Upcoming user appointment appointment.
     *
     * @return the appointment
     */
    public static Appointment upcomingUserAppointment() {
        ArrayList<Appointment> appointments = dbManager.getUserAppointments();
        if (!appointments.isEmpty()) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            for (Appointment appointment : appointments) {
                LocalDateTime appointmentDateTime = appointment.getStart();

                int compareDate = currentDateTime.toLocalDate().compareTo(appointmentDateTime.toLocalDate());
                int compareTime = currentDateTime.compareTo(appointmentDateTime);
                long timeRemaining = currentDateTime.until(appointmentDateTime, ChronoUnit.MINUTES);

                if (compareDate == 0 && compareTime < 0 && timeRemaining <= 15) {
                    return appointment;
                }
            }
        }
        return null;
    }

    /**
     * Update appointment boolean.
     *
     * @param appointment the appointment
     * @return the boolean
     */
    public static boolean updateAppointment(Appointment appointment) {
        if (dbManager.Update(appointment)) {
            for (Appointment x : appointmentList) {
                if (x.getID() == appointment.getID()) {
                    int index = appointmentList.indexOf(x);
                    appointmentList.set(index, appointment);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Generate appointment times observable list.
     *
     * @param startHour the start hour
     * @param endHour   the end hour
     * @param endTime   the end time
     * @return the observable list
     */
    public static ObservableList<String> generateAppointmentTimes(int startHour, int endHour, boolean endTime) {
        ObservableList<String> times = FXCollections.observableArrayList();
        int min = 0;
        if (endTime) {
            min = 1;

        }
        do {
            String time;
            String minutes = "";
            String timeOfDay;

            switch (min % 4) {
                case 0:
                    minutes = ":00";
                    break;
                case 1:
                    minutes = ":15";
                    break;
                case 2:
                    minutes = ":30";
                    break;
                case 3:
                    minutes = ":45";
                    break;
            }
            min++;

            if (startHour < 12) {
                timeOfDay = "AM";
            } else {
                timeOfDay = "PM";
            }

            if (startHour % 12 == 0) {
                time = 12 + minutes + timeOfDay;
            } else {
                time = (startHour % 12) + minutes + timeOfDay;
            }
            times.add(time);

            if (min % 4 == 0) {
                startHour++;
            }
        } while (startHour < endHour);

        return times;
    }
}
