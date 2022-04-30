package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.Command;
import com.miciaha.rendezvous.persistence.observables.AppointmentData;
import com.miciaha.rendezvous.persistence.observables.CustomerData;
import com.miciaha.rendezvous.utilities.Alerts;
import com.miciaha.rendezvous.utilities.FormManager;
import com.miciaha.rendezvous.utilities.TableManager;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The type Main view controller.
 */
public class MainViewController implements Initializable {


    /**
     * The Customers.
     */
    public static final ObservableList<Customer> customers = CustomerData.customerList;
    /**
     * The Appointments.
     */
    public static final ObservableList<Appointment> appointments = AppointmentData.appointmentList;

    /**
     * The Appoint view.
     */
    public ToggleGroup appointView;

    /**
     * The Apt table.
     */
    @FXML
    public TableView<Appointment> aptTable;

    /**
     * The App month week col.
     */
    @FXML
    public TableColumn<Appointment, String> appMonthWeekCol;

    /**
     * The App date col.
     */
    @FXML
    public TableColumn<Appointment, String> appDateCol;

    /**
     * The App id col.
     */
    @FXML
    public TableColumn<Appointment, Integer> appIdCol;

    /**
     * The App title col.
     */
    @FXML
    public TableColumn<Appointment, String> appTitleCol;

    /**
     * The App description col.
     */
    @FXML
    public TableColumn<Appointment, String> appDescriptionCol;

    /**
     * The App loc col.
     */
    @FXML
    public TableColumn<Appointment, String> appLocCol;

    /**
     * The App con col.
     */
    @FXML
    public TableColumn<Appointment, String> appConCol;

    /**
     * The App type col.
     */
    @FXML
    public TableColumn<Appointment, String> appTypeCol;

    /**
     * The App start col.
     */
    @FXML
    public TableColumn<Appointment, String> appStartCol;

    /**
     * The App end col.
     */
    @FXML
    public TableColumn<Appointment, String> appEndCol;

    /**
     * The App user id col.
     */
    @FXML
    public TableColumn<Appointment, String> appUserIdCol;

    /**
     * The App customer id col.
     */
    @FXML
    public TableColumn<Appointment, String> appCustomerIdCol;

    /**
     * The Customer table.
     */
    @FXML
    public TableView<Customer> customerTable;

    /**
     * The Customer name col.
     */
    @FXML
    public TableColumn<Customer, String> customerNameCol;

    /**
     * The Customer id col.
     */
    @FXML
    public TableColumn<Customer, Integer> customerIdCol;

    /**
     * The Customer address col.
     */
    @FXML
    public TableColumn<Customer, String> customerAddressCol;

    /**
     * The Customer post col.
     */
    @FXML
    public TableColumn<Customer, String> customerPostCol;

    /**
     * The Customer phone col.
     */
    @FXML
    public TableColumn<Customer, String> customerPhoneCol;

    /**
     * The Add app btn.
     */
    @FXML
    public Button addAppBtn;

    /**
     * The Edit app btn.
     */
    @FXML
    public Button editAppBtn;

    /**
     * The Cancel apt btn.
     */
    @FXML
    public Button cancelAptBtn;

    /**
     * The Add customer btn.
     */
    @FXML
    public Button addCustomerBtn;

    /**
     * The Edit customer btn.
     */
    @FXML
    public Button editCustomerBtn;

    /**
     * The Delete customer btn.
     */
    @FXML
    public Button deleteCustomerBtn;

    /**
     * The Upcoming apt group.
     */
    @FXML
    public Group upcomingAptGroup;

    /**
     * The Upcoming apt label.
     */
    @FXML
    public Label upcomingAptLabel;

    /**
     * The No apt group.
     */
    @FXML
    public Group noAptGroup;

    /**
     * The Week radio.
     */
    @FXML
    public RadioButton weekRadio;

    /**
     * The Month radio.
     */
    @FXML
    public RadioButton monthRadio;

    /**
     * The Btn reports.
     */
    @FXML
    public Button btnReports;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTable.setItems(customers);
        aptTable.setItems(appointments);

        TableManager.LinkAppointmentColumns(appIdCol, appUserIdCol, appCustomerIdCol,
                appTitleCol, appDescriptionCol, appLocCol, appTypeCol,
                appConCol, appStartCol, appEndCol, appDateCol, appMonthWeekCol);

        TableManager.LinkCustomerColumns(customerIdCol, customerNameCol, customerAddressCol,
                customerPostCol, customerPhoneCol);

        btnReports.setOnAction(new ReportButtonHandler());

        monthRadio.setOnAction(new AppointmentViewToggleHandler());
        weekRadio.setOnAction(new AppointmentViewToggleHandler());


        addCustomerBtn.setOnAction(new AddCustomerButtonHandler());
        editCustomerBtn.setOnAction(new EditCustomerButtonHandler());
        deleteCustomerBtn.setOnAction(new DeleteCustomerButtonHandler());

        addAppBtn.setOnAction(new AddAppointmentButtonHandler());
        editAppBtn.setOnAction(new EditAppointmentButtonHandler());
        cancelAptBtn.setOnAction(new DeleteAppointmentButtonHandler());

        updateUpcomingAppointmentHeader();
    }

    private void updateUpcomingAppointmentHeader() {
        Appointment appointment = AppointmentData.upcomingUserAppointment();
        if (!Objects.isNull(appointment)) {
            noAptGroup.setVisible(false);
            upcomingAptGroup.setVisible(true);
            String date = LocaleHandler.DateTimeHelper.formatDate(appointment.getStart());
            String time = LocaleHandler.DateTimeHelper.formatTime(appointment.getStart());
            String notification = date + ": Appointment [" + appointment.getId() + "] starting at " + time;

            upcomingAptLabel.textProperty().setValue(notification);
        } else {
            noAptGroup.setVisible(true);
            upcomingAptGroup.setVisible(false);
        }
    }

    private class ReportButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            new FormManager.ReportForm(btnReports);
        }
    }

    private class AddCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            new FormManager.CreateCustomerForm(addCustomerBtn);
        }
    }

    private class AppointmentViewToggleHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (weekRadio.isSelected()) {
                appMonthWeekCol.textProperty().setValue("Week");
                appMonthWeekCol.cellValueFactoryProperty().setValue(appointment -> {
                    String week = LocaleHandler.DateTimeHelper.getWeekOfYear(appointment.getValue().getStart());
                    return new SimpleStringProperty(week);
                });
            } else {
                appMonthWeekCol.textProperty().setValue("Month");
                appMonthWeekCol.cellValueFactoryProperty().setValue(appointment -> {
                    String month = appointment.getValue().getStart().getMonth().name();
                    return new SimpleStringProperty(month);
                });
            }
            aptTable.refresh();
        }
    }

    private class EditCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Customer selectedCustomer = customerTable.getFocusModel().getFocusedItem();

            if (!(selectedCustomer == null)) {
                new FormManager.EditCustomerForm(editCustomerBtn, selectedCustomer);
            }

        }
    }

    private class DeleteCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Customer selectedCustomer = customerTable.getFocusModel().getFocusedItem();

            if (!(selectedCustomer == null)) {
                Command deleteCustomer = () -> CustomerData.removeCustomer(selectedCustomer);

                boolean customerRemoved = new Alerts.CustomAlert.ConfirmationAlert(deleteCustomer, "delete customer").run();
                if (customerRemoved) {
                    new Alerts.CustomAlert.SuccessAlert("Customer deleted");
                }
            }

        }
    }

    private class AddAppointmentButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            new FormManager.CreateAppointmentForm(addAppBtn);
        }
    }

    private class EditAppointmentButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Appointment selectedAppointment = aptTable.getFocusModel().getFocusedItem();

            if (!(selectedAppointment == null)) {
                new FormManager.EditAppointmentForm(editAppBtn, selectedAppointment);
            }
        }
    }

    private class DeleteAppointmentButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Appointment selectedAppointment = aptTable.getFocusModel().getFocusedItem();

            if (!(selectedAppointment == null)) {
                Command deleteAppointment = () -> AppointmentData.removeAppointment(selectedAppointment);

                boolean appointmentRemoved = new Alerts.CustomAlert.ConfirmationAlert(deleteAppointment, "remove appointment").run();
                if (appointmentRemoved) {
                    new Alerts.CustomAlert.SuccessAlert("Appointment removed");
                }
            }
        }
    }
}
