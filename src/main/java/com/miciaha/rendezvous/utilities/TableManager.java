package com.miciaha.rendezvous.utilities;

import com.miciaha.rendezvous.controllers.ReportViewController;
import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Table manager handles linking tables to static and dynamic data.
 * The Table manager also handles the table search functionality.
 */
public class TableManager {

    /**
     * Link customer appointment report columns.
     *
     * @param appCount the app count
     * @param appType  the app type
     * @param appMonth the app month
     */
    public static void LinkCustomerAppointmentReportColumns(TableColumn<ReportViewController.CustomerAppointment, String> appCount, TableColumn<ReportViewController.CustomerAppointment, String> appType,
                                                            TableColumn<ReportViewController.CustomerAppointment, String> appMonth) {
        appCount.setCellValueFactory(x -> new SimpleStringProperty(String.valueOf(x.getValue().getCount())));
        appMonth.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getMonth()));
        appType.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getType()));
    }

    /**
     * Link login attempt report columns.
     *
     * @param loginDate    the login date
     * @param loginTime    the login time
     * @param username     the username
     * @param loginAttempt the login attempt
     */
    public static void LinkLoginAttemptReportColumns(TableColumn<LoginEvent, String> loginDate, TableColumn<LoginEvent, String> loginTime,
                                                     TableColumn<LoginEvent, String> username, TableColumn<LoginEvent, String> loginAttempt) {

        loginAttempt.setCellValueFactory(login -> new SimpleStringProperty(login.getValue().getAttemptString()));

        loginDate.setCellValueFactory(login -> {
            Timestamp dateTimestamp = login.getValue().getLoginDateTime();
            LocalDateTime localDateTime = LocaleHandler.DateTimeHelper.utcToLocalDateTime(dateTimestamp);

            String date = LocaleHandler.DateTimeHelper.formatDate(localDateTime);

            return new SimpleStringProperty(date);
        });

        loginTime.setCellValueFactory(login -> {
            Timestamp dateTimestamp = login.getValue().getLoginDateTime();
            LocalDateTime localDateTime = LocaleHandler.DateTimeHelper.utcToLocalDateTime(dateTimestamp);

            String date = LocaleHandler.DateTimeHelper.formatTime(localDateTime);
            return new SimpleStringProperty(date);
        });

        username.setCellValueFactory(login -> {
            String name = login.getValue().getUsername();
            return new SimpleStringProperty(name);
        });
    }

    /**
     * Link contact report columns.
     *
     * @param contactName the contact name
     * @param id          the id
     * @param title       the title
     * @param type        the type
     * @param description the description
     * @param startTime   the start time
     * @param endTime     the end time
     * @param customerID  the customer id
     */
    public static void LinkContactReportColumns(TableColumn<Appointment, String> contactName, TableColumn<Appointment, Integer> id, TableColumn<Appointment, String> title,
                                                TableColumn<Appointment, String> type, TableColumn<Appointment, String> description, TableColumn<Appointment, String> startTime,
                                                TableColumn<Appointment, String> endTime, TableColumn<Appointment, String> customerID) {
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));

        contactName.setCellValueFactory(appointment -> {
            String contact = appointment.getValue().getContact().getName();
            return new SimpleStringProperty(contact);
        });

        startTime.setCellValueFactory(appointment -> {
            LocalDateTime start = appointment.getValue().getStart();
            return new SimpleStringProperty(LocaleHandler.DateTimeHelper.formatDateTime(start));
        });

        endTime.setCellValueFactory(appointment -> {
            LocalDateTime end = appointment.getValue().getStart();
            return new SimpleStringProperty(LocaleHandler.DateTimeHelper.formatDateTime(end));
        });

        customerID.setCellValueFactory(appointment -> {
            int customer = appointment.getValue().getCustomer().getID();
            return new SimpleStringProperty(String.valueOf(customer));

        });
    }

    /**
     * Link appointment columns.
     *
     * @param id          the id
     * @param userID      the user id
     * @param custID      the cust id
     * @param title       the title
     * @param description the description
     * @param location    the location
     * @param type        the type
     * @param contact     the contact
     * @param start       the start
     * @param end         the end
     * @param date        the date
     * @param monthWeek   the month week
     */
    public static void LinkAppointmentColumns(TableColumn<Appointment, Integer> id, TableColumn<Appointment, String> userID, TableColumn<Appointment, String> custID,
                                              TableColumn<Appointment, String> title, TableColumn<Appointment, String> description, TableColumn<Appointment, String> location,
                                              TableColumn<Appointment, String> type, TableColumn<Appointment, String> contact, TableColumn<Appointment, String> start,
                                              TableColumn<Appointment, String> end, TableColumn<Appointment, String> date, TableColumn<Appointment, String> monthWeek) {
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));

        date.setCellValueFactory(appointment -> {
            String appointmentDate = LocaleHandler.DateTimeHelper.formatDate(appointment.getValue().getStart());
            return new SimpleStringProperty(appointmentDate);
        });

        monthWeek.setCellValueFactory(appointment -> {
            String month = appointment.getValue().getStart().getMonth().name();
            return new SimpleStringProperty(month);
        });

        start.setCellValueFactory(appointment -> {
            String startTime = LocaleHandler.DateTimeHelper.formatTime(appointment.getValue().getStart());
            return new SimpleStringProperty(startTime);
        });

        end.setCellValueFactory(appointment -> {
            String endTime = LocaleHandler.DateTimeHelper.formatTime(appointment.getValue().getEnd());
            return new SimpleStringProperty(endTime);
        });

        userID.setCellValueFactory(appointment -> {
            int appointmentUserId = appointment.getValue().getUser().getID();
            return new SimpleStringProperty(String.valueOf(appointmentUserId));
        });

        custID.setCellValueFactory(appointment -> {
            int customerID = appointment.getValue().getCustomer().getID();
            return new SimpleStringProperty(String.valueOf(customerID));
        });

        contact.setCellValueFactory(appointment -> {
            String contactName = appointment.getValue().getContact().getName();
            return new SimpleStringProperty(contactName);
        });
    }

    /**
     * Link customer columns.
     *
     * @param id       the id
     * @param name     the name
     * @param address  the address
     * @param postCode the post code
     * @param phone    the phone
     */
    public static void LinkCustomerColumns(TableColumn<Customer, Integer> id, TableColumn<Customer, String> name, TableColumn<Customer, String> address,
                                           TableColumn<Customer, String> postCode, TableColumn<Customer, String> phone) {
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        address.setCellValueFactory(customer -> {

            String countryName = customer.getValue().getDivision().getCountry().getName();
            String cAddress = customer.getValue().getAddress();
            if (countryName.contains("Kingdom") || countryName.contains("UK")) {
                String cDivision = customer.getValue().getDivision().getName();

                return new SimpleStringProperty(cAddress + ", " + cDivision);
            } else {
                return new SimpleStringProperty(cAddress);
            }
        });
        postCode.setCellValueFactory(new PropertyValueFactory<>("PostCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }
}