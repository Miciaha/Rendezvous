package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Country;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.entities.Division;
import com.miciaha.rendezvous.interfaces.FormEditor;
import com.miciaha.rendezvous.persistence.CountryDbManager;
import com.miciaha.rendezvous.persistence.CustomerDbManager;
import com.miciaha.rendezvous.persistence.DivisionDbManager;
import com.miciaha.rendezvous.persistence.observables.AppointmentData;
import com.miciaha.rendezvous.persistence.observables.CustomerData;
import com.miciaha.rendezvous.utilities.FormManager;
import com.miciaha.rendezvous.utilities.fields.FieldTracker;
import com.miciaha.rendezvous.utilities.fields.FieldType;
import com.miciaha.rendezvous.utilities.fields.FormField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable, FormEditor<Customer> {
    protected boolean updatingCustomer = false;
    protected Customer formCustomer;

    @FXML
    public TextField idTF;

    @FXML
    public ComboBox<String> CountryCmb;

    @FXML
    public ComboBox<String> DivisionCmb;

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
    public Button saveButton;

    @FXML
    public Button cancelButton;

    @FXML
    public Label addressErrorLabel;

    @FXML
    public Label postErrorLabel;

    @FXML
    public Label phoneErrorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryCmb.setItems(CountryDbManager.getCountryList());
        CountryCmb.setOnAction(new SelectCountryEventHandler());

        cancelButton.setOnAction(new CancelCustomerButtonHandler());
        setFields();

    }

    @Override
    public void initData(Customer customer) {

        String country = customer.getDivision().getCountry().getName();
        String division = customer.getDivision().getName();

        idTF.textProperty().setValue(String.valueOf(customer.getID()));
        nameTF.textProperty().setValue(customer.getName());
        addressTF.textProperty().setValue(customer.getAddress());
        postTF.textProperty().setValue(customer.getPostCode());
        phoneTF.textProperty().setValue(customer.getPhone());
        CountryCmb.setValue(country);
        DivisionCmb.setValue(division);
        FieldTracker.Fields.editFields();

        updatingCustomer = true;
        formCustomer = customer;
    }

    private void setFields(){
        FieldTracker.Fields.clear();
        FieldTracker.Fields.add(new FormField(nameTF, nameErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(addressTF, addressErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(postTF, postErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(phoneTF, phoneErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.linkToButton(saveButton);
        saveButton.setOnAction(new SaveCustomerButtonHandler());
    }

    private boolean updateCustomer(String name, String address, String post, String phone, Division division){

        formCustomer.setName(name);
        formCustomer.setAddress(address);
        formCustomer.setPostCode(post);
        formCustomer.setPhone(phone);
        formCustomer.setDivision(division);

        return CustomerData.updateCustomer(formCustomer);
    }

    private boolean createCustomer(String name, String address, String post, String phone, Division division){

        Customer newCustomer = new Customer(name, address, post, phone, division);
        return CustomerData.addCustomer(newCustomer);
    }

    private class SaveCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            String name = nameTF.textProperty().getValue();
            String address = addressTF.textProperty().getValue();
            String post = postTF.textProperty().getValue();
            String phone = phoneTF.textProperty().getValue();
            Division division = DivisionDbManager.getDivision(DivisionCmb.getValue());
            boolean taskSuccess;

            if(updatingCustomer){
                taskSuccess = updateCustomer(name, address, post, phone, division);
            } else {
                taskSuccess = createCustomer(name, address, post, phone, division);
            }

            if(taskSuccess){
                FormManager.closeForm(saveButton);
            }
        }
    }

    private class SelectCountryEventHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            String selectedCountry = CountryCmb.getValue();

            Country country = CountryDbManager.getCountry(selectedCountry);

            if(country != null){
                DivisionCmb.setItems(DivisionDbManager.getCountryDivisionsList(country.getID()));
            }
        }
    }

    private class CancelCustomerButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            FormManager.closeForm(cancelButton);
        }
    }

}
