package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.Command;
import com.miciaha.rendezvous.persistence.AppointmentDbManager;
import com.miciaha.rendezvous.persistence.CustomerDbManager;
import com.miciaha.rendezvous.persistence.observables.AppointmentData;
import com.miciaha.rendezvous.persistence.observables.CustomerData;
import com.miciaha.rendezvous.utilities.Alerts;
import com.miciaha.rendezvous.utilities.FormManager;
import com.miciaha.rendezvous.utilities.TableManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    protected CustomerDbManager customerDb = new CustomerDbManager();
    protected AppointmentDbManager appointmentDb = new AppointmentDbManager();

    public static ObservableList<Customer> customers = CustomerData.customerList;
    public static ObservableList<Appointment> appointments = AppointmentData.appointmentList;

    @FXML
    public TableView<Appointment> aptTable;

    @FXML
    public TableColumn<Appointment,Integer> appIdCol;

    @FXML
    public TableColumn<Appointment, String> appTitleCol;

    @FXML
    public TableColumn<Appointment, String> appDescriptionCol;

    @FXML
    public TableColumn<Appointment, String> appLocCol;

    @FXML
    public TableColumn<Appointment, String> appConCol;

    @FXML
    public TableColumn<Appointment, String> appTypeCol;

    @FXML
    public TableColumn<Appointment, LocalDateTime> appStartCol;

    @FXML
    public TableColumn<Appointment, LocalDateTime> appEndCol;

    @FXML
    public TableColumn<Appointment, Integer> appUserIdCol;

    @FXML
    public TableColumn<Appointment, Integer> appCustomerIdCol;

    @FXML
    public TableView<Customer> customerTable;

    @FXML
    public TableColumn<Customer, String> customerNameCol;

    @FXML
    public TableColumn<Customer, Integer> customerIdCol;

    @FXML
    public TableColumn<Customer, String> customerAddressCol;

    @FXML
    public TableColumn<Customer, String> customerPostCol;

    @FXML
    public TableColumn<Customer, String> customerPhoneCol;

    @FXML
    public Button addAppBtn;

    @FXML
    public Button editAppBtn;

    @FXML
    public Button cancelAptBtn;

    @FXML
    public Button addCustomerBtn;

    @FXML
    public Button editCustomerBtn;

    @FXML
    public Button deleteCustomerBtn;

    @FXML
    public MenuItem btnTotalReport;

    @FXML
    public MenuItem btnContactReport;

    @FXML
    public MenuItem btnLoginReport;

    @FXML
    public Group upcomingAptGroup;

    @FXML
    public Label upcomingAptLabel;

    @FXML
    public Group noAptGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTable.setItems(customers);
        aptTable.setItems(appointments);

        TableManager.LinkAppointmentColumns(appIdCol, appUserIdCol, appCustomerIdCol,
                                        appTitleCol, appDescriptionCol, appLocCol, appTypeCol, appConCol, appStartCol, appEndCol);

        TableManager.LinkCustomerColumns(customerIdCol, customerNameCol, customerAddressCol, customerPostCol, customerPhoneCol);


        addCustomerBtn.setOnAction(new AddCustomerButtonHandler());
        editCustomerBtn.setOnAction(new EditCustomerButtonHandler());
        deleteCustomerBtn.setOnAction(new DeleteCustomerButtonHandler());

        addAppBtn.setOnAction(new AddAppointmentButtonHandler());
        editAppBtn.setOnAction(new EditAppointmentButtonHandler());
        cancelAptBtn.setOnAction(new DeleteAppointmentButtonHandler());
    }

    private class AddCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            new FormManager.CreateCustomerForm(addCustomerBtn);

        }
    }

    private class EditCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Customer selectedCustomer = customerTable.getFocusModel().getFocusedItem();

            if(!(selectedCustomer == null)){
                new FormManager.EditCustomerForm(editCustomerBtn,selectedCustomer);
            }

        }
    }

    private class DeleteCustomerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            Customer selectedCustomer = customerTable.getFocusModel().getFocusedItem();

            if(!(selectedCustomer == null)){
                Command deleteCustomer = () -> CustomerData.removeCustomer(selectedCustomer);

                new Alerts.CustomAlert.ConfirmationAlert<Customer>(deleteCustomer, "delete customer");
                new Alerts.CustomAlert.SuccessAlert("Customer deleted");
            }

        }
    }

    private class AddAppointmentButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            new FormManager.CreateAppointmentForm(addAppBtn);
        }
    }

    private class EditAppointmentButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            Appointment selectedAppointment = aptTable.getFocusModel().getFocusedItem();

            if(!(selectedAppointment == null)){
                new FormManager.EditAppointmentForm(editAppBtn, selectedAppointment);
            }
        }
    }

    private class DeleteAppointmentButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Appointment selectedAppointment = aptTable.getFocusModel().getFocusedItem();

            if(!(selectedAppointment == null)){
                Command deleteAppointment = () -> AppointmentData.removeAppointment(selectedAppointment);

                new Alerts.CustomAlert.ConfirmationAlert<Appointment>(deleteAppointment, "remove appointment");
                new Alerts.CustomAlert.SuccessAlert("Appointment removed");
            }
        }
    }
}
