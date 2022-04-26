package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Country;
import com.miciaha.rendezvous.entities.CountryDivision;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.entities.Division;
import com.miciaha.rendezvous.interfaces.DbManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {
    @FXML
    public ComboBox<Country> CountryCmb;

    @FXML
    public ComboBox<Division> DivisionCmb;

    @FXML
    public Label countryErrorLabel;

    @FXML
    public Label nameErrorLabel;

    @FXML
    public Label divisionErrorLabel;

    @FXML
    public TextField nameTF;

    @FXML
    public TextField addressTF;

    @FXML
    public TextField postTF;

    @FXML
    public TextField phoneTF;

    @FXML
    public Button saveCustomerForm;

    @FXML
    public Button cancelForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDivision.initializeLocations();

        CountryCmb.setItems(CountryDivision.getAllCountries());
    }
}
