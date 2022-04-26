package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

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
    public TableColumn<Appointment, String> appStartCol;

    @FXML
    public TableColumn<Appointment, String> appEndCol;

    @FXML
    public TableColumn<Appointment, Integer> appUserIdCol;

    @FXML
    public TableColumn<Appointment, Integer> appCostIdCol;

    @FXML
    public TableView<Customer> customerTable;

    @FXML
    public TableColumn<Customer, String> custNameCol;

    @FXML
    public TableColumn<Customer, Integer> custIdCol;

    @FXML
    public TableColumn<Customer, String> custAddressCol;

    @FXML
    public TableColumn<Customer, String> custPostCol;

    @FXML
    public TableColumn<Customer, String> custPhoneCol;

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

    }
}
