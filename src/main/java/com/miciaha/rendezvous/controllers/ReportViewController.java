package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.reports.CustomerAppointment;
import com.miciaha.rendezvous.persistence.observables.AppointmentData;
import com.miciaha.rendezvous.persistence.observables.CustomerAppointmentData;
import com.miciaha.rendezvous.utilities.LoginEvent;
import com.miciaha.rendezvous.utilities.TableManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Report view controller.
 */
public class ReportViewController implements Initializable {
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
        customerAppointmentTable.setItems(CustomerAppointmentData.getCustomerAppointments());

        TableManager.LinkContactReportColumns(contactNameCol, contactAppIdCol, contactTitleCol, contactTypeCol,
                contactDescriptionCol, contactStartCol, contactEndCol, contactCustomerIDCol);

        TableManager.LinkLoginAttemptReportColumns(loginDateCol, loginTimeCol, loginUserCol, loginAttempt);

        TableManager.LinkCustomerAppointmentReportColumns(appointmentCountCol, appointmentTypeCol, appMonthCol);


    }
}
