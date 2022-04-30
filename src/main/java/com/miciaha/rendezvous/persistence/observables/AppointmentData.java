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
    public static final ObservableList<Appointment> appointmentList = dbManager.getAllAppointments();

    /**
     * Add appointment boolean.
     *
     * @param appointment the appointment
     * @return the boolean
     */
    public static boolean addAppointment(Appointment appointment) {

        if (!hasTimeConflict(appointment)) {
            int id = SQLDBConnection.getMaxId("Appointment");

            appointment.setId(id);
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
                // Naming four variables: a, b, c, and d.
                // a = existingStartTime; b = existingEndTime; c = newStartTime; d = newEndTime
                var a = x.getStart();
                var b = x.getEnd();
                var c = appointment.getStart();
                var d = appointment.getEnd();

                // There are three bad cases to consider:
                // 1. Both the new appointment start and end times take place during the scheduled appointment:
                //          This can be written as (a <= c and b >= d)
                // 2. The new appointment start time takes place during the scheduled appointment:
                //          This can be written as (a <= c and b > c)
                // 3. The new appointment end time takes place during the scheduled appointment:
                //          This can be written as (a < d and b >= d)
                int compareAtoC = a.compareTo(c);
                int compareAtoD = a.compareTo(d);
                int compareBtoC = b.compareTo(c);
                int compareBtoD = b.compareTo(d);

                if((compareAtoC <= 0 && compareBtoD >= 0) ||(compareAtoC <= 0 && compareBtoC > 0)||(compareAtoD < 0 && compareBtoD >= 0))
            {
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
     */
    public static void removeAppointment(Appointment appointment) {
        if (dbManager.Delete(appointment)) {
            appointmentList.remove(appointment);
        }
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
                if (appointment.getCustomer().getId() == customer.getId()) {
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
                if (x.getId() == appointment.getId()) {
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
