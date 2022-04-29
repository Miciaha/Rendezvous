package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.persistence.observables.AppointmentData;
import com.miciaha.rendezvous.utilities.LoginEvent;
import com.miciaha.rendezvous.utilities.TableManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The type Report view controller.
 */
public class ReportViewController implements Initializable {
    private static ObservableList<CustomerAppointment> customerAppointments = CustomerAppointment.setCustomerAppointments();

    /**
     * The Appointment count col.
     */
    @FXML
    public TableColumn<CustomerAppointment, String> appointmentCountCol;

    /**
     * The Appointment type col.
     */
    @FXML
    public TableColumn<CustomerAppointment, String> appointmentTypeCol;

    /**
     * The App month col.
     */
    @FXML
    public TableColumn<CustomerAppointment, String> appMonthCol;

    /**
     * The Contact name col.
     */
    @FXML
    public TableColumn<Appointment, String> contactNameCol;

    /**
     * The Contact app id col.
     */
    @FXML
    public TableColumn<Appointment, Integer> contactAppIdCol;

    /**
     * The Contact title col.
     */
    @FXML
    public TableColumn<Appointment, String> contactTitleCol;

    /**
     * The Contact type col.
     */
    @FXML
    public TableColumn<Appointment, String> contactTypeCol;

    /**
     * The Contact description col.
     */
    @FXML
    public TableColumn<Appointment, String> contactDescriptionCol;

    /**
     * The Contact start col.
     */
    @FXML
    public TableColumn<Appointment, String> contactStartCol;

    /**
     * The Contact end col.
     */
    @FXML
    public TableColumn<Appointment, String> contactEndCol;

    /**
     * The Contact customer id col.
     */
    @FXML
    public TableColumn<Appointment, String> contactCustomerIDCol;

    /**
     * The Login date col.
     */
    @FXML
    public TableColumn<LoginEvent, String> loginDateCol;

    /**
     * The Login time col.
     */
    @FXML
    public TableColumn<LoginEvent, String> loginTimeCol;

    /**
     * The Login user col.
     */
    @FXML
    public TableColumn<LoginEvent, String> loginUserCol;

    /**
     * The Login attempts table.
     */
    @FXML
    public TableView<LoginEvent> loginAttemptsTable;

    /**
     * The Contact schedule table.
     */
    @FXML
    public TableView<Appointment> contactScheduleTable;

    /**
     * The Customer appointment table.
     */
    @FXML
    public TableView<CustomerAppointment> customerAppointmentTable;

    /**
     * The Login attempt.
     */
    @FXML
    public TableColumn<LoginEvent, String> loginAttempt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginAttemptsTable.setItems(LoginEvent.getLoginEvents());
        contactScheduleTable.setItems(AppointmentData.appointmentList);
        customerAppointmentTable.setItems(customerAppointments);

        TableManager.LinkContactReportColumns(contactNameCol, contactAppIdCol, contactTitleCol, contactTypeCol,
                contactDescriptionCol, contactStartCol, contactEndCol, contactCustomerIDCol);

        TableManager.LinkLoginAttemptReportColumns(loginDateCol, loginTimeCol, loginUserCol, loginAttempt);

        TableManager.LinkCustomerAppointmentReportColumns(appointmentCountCol, appointmentTypeCol, appMonthCol);


    }

    /**
     * The type Customer appointment.
     */
    public static class CustomerAppointment {
        private int count;
        private String type;
        private String month;

        /**
         * Gets month.
         *
         * @return the month
         */
        public String getMonth() {
            return month;
        }

        /**
         * Gets type.
         *
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * Gets count.
         *
         * @return the count
         */
        public int getCount() {
            return count;
        }

        /**
         * Sets count.
         *
         * @param count the count
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * Sets month.
         *
         * @param month the month
         */
        public void setMonth(String month) {
            this.month = month;
        }

        /**
         * Sets type.
         *
         * @param type the type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Instantiates a new Customer appointment.
         *
         * @param type  the type
         * @param month the month
         */
        protected CustomerAppointment(String type, String month) {
            this.type = type;
            this.month = month;
        }

        /**
         * Instantiates a new Customer appointment.
         *
         * @param combinedValues the combined values
         * @param count          the count
         */
        protected CustomerAppointment(String combinedValues, int count) {
            String[] splitValues = combinedValues.split(";");
            this.type = splitValues[0];
            this.month = splitValues[1];
            this.count = count;

        }

        private static ObservableList<CustomerAppointment> setCustomerAppointments() {
            LinkedHashMap<String, Integer> repeatedTypes = new LinkedHashMap<>();

            List<String> newList = AppointmentData.appointmentList
                    .stream()
                    .map(x -> (x.getType() + ";" + x.getStart().getMonth().name()))
                    .collect(Collectors.toList());

            newList.forEach(x -> {
                if (repeatedTypes.containsKey(x)) {
                    repeatedTypes.put(x, repeatedTypes.get(x) + 1);
                } else {
                    repeatedTypes.put(x, 1);
                }
            });

            ArrayList<String> combinedTypeMonthValue = new ArrayList<>(repeatedTypes.keySet());
            ArrayList<Integer> counts = new ArrayList<>(repeatedTypes.values());

            ArrayList<CustomerAppointment> tempAppointment = new ArrayList<>();

            for (String combinedValues : combinedTypeMonthValue) {
                int count = counts.get(combinedTypeMonthValue.indexOf(combinedValues));
                CustomerAppointment temp = new CustomerAppointment(combinedValues, count);
                tempAppointment.add(temp);
            }

            customerAppointments = FXCollections.observableArrayList(tempAppointment);
            return customerAppointments;
        }
    }
}
