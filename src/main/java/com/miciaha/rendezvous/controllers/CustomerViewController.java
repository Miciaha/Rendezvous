package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Country;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.entities.Division;
import com.miciaha.rendezvous.interfaces.FormEditor;
import com.miciaha.rendezvous.persistence.CountryDbManager;
import com.miciaha.rendezvous.persistence.DivisionDbManager;
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

/**
 * The type Customer view controller.
 */
public class CustomerViewController implements Initializable, FormEditor<Customer> {
    /**
     * The Updating customer.
     */
    protected boolean updatingCustomer = false;
    /**
     * The Form customer.
     */
    protected Customer formCustomer;

    /**
     * The Id TextField.
     */
    @FXML
    public TextField idTF;

    /**
     * The Country cmb.
     */
    @FXML
    public ComboBox<String> CountryCmb;

    /**
     * The Division cmb.
     */
    @FXML
    public ComboBox<String> DivisionCmb;

    /**
     * The Country error label.
     */
    @FXML
    public Label countryErrorLabel;

    /**
     * The Name error label.
     */
    @FXML
    public Label nameErrorLabel;

    /**
     * The Division error label.
     */
    @FXML
    public Label divisionErrorLabel;

    /**
     * The Name tf.
     */
    @FXML
    public TextField nameTF;

    /**
     * The Address tf.
     */
    @FXML
    public TextField addressTF;

    /**
     * The City tf.
     */
    @FXML
    public TextField cityTF;

    /**
     * The City error label.
     */
    @FXML
    public Label cityErrorLabel;

    /**
     * The Post tf.
     */
    @FXML
    public TextField postTF;

    /**
     * The Phone tf.
     */
    @FXML
    public TextField phoneTF;

    /**
     * The Save button.
     */
    @FXML
    public Button saveButton;

    /**
     * The Cancel button.
     */
    @FXML
    public Button cancelButton;

    /**
     * The Address error label.
     */
    @FXML
    public Label addressErrorLabel;

    /**
     * The Post error label.
     */
    @FXML
    public Label postErrorLabel;

    /**
     * The Phone error label.
     */
    @FXML
    public Label phoneErrorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryCmb.setItems(CountryDbManager.getCountryList());
        CountryCmb.setOnAction(new SelectCountryEventHandler());

        saveButton.setOnAction(new SaveCustomerButtonHandler());
        cancelButton.setOnAction(new CancelCustomerButtonHandler());
        setFields();

    }

    @Override
    public void initData(Customer customer) {

        String country = customer.getDivision().getCountry().getName();
        String division = customer.getDivision().getName();
        String[] splitAddress = customer.getAddress().split(",");

        idTF.textProperty().setValue(String.valueOf(customer.getId()));
        nameTF.textProperty().setValue(customer.getName());
        addressTF.textProperty().setValue(splitAddress[0]);
        cityTF.textProperty().setValue(splitAddress[1]);
        postTF.textProperty().setValue(customer.getPostCode());
        phoneTF.textProperty().setValue(customer.getPhone());
        CountryCmb.setValue(country);
        DivisionCmb.setValue(division);
        FieldTracker.editFields();

        updatingCustomer = true;
        formCustomer = customer;
    }

    private void setFields() {
        FieldTracker.clear();
        FieldTracker.TextFields.add(new FormField<>(nameTF, nameErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(addressTF, addressErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(cityTF, cityErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(postTF, postErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(phoneTF, phoneErrorLabel, FieldType.TEXT));
        FieldTracker.linkToButton(saveButton);
    }

    private boolean updateCustomer(String name, String address, String post, String phone, Division division) {

        formCustomer.setName(name);
        formCustomer.setAddress(address);
        formCustomer.setPostCode(post);
        formCustomer.setPhone(phone);
        formCustomer.setDivision(division);

        return CustomerData.updateCustomer(formCustomer);
    }

    private boolean createCustomer(String name, String address, String post, String phone, Division division) {

        Customer newCustomer = new Customer(name, address, post, phone, division);
        return CustomerData.addCustomer(newCustomer);
    }

    private class SaveCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            String name = nameTF.textProperty().getValue();
            String address = addressTF.textProperty().getValue() + ", " + cityTF.textProperty().getValue();
            String post = postTF.textProperty().getValue();
            String phone = phoneTF.textProperty().getValue();
            Division division = DivisionDbManager.getDivision(DivisionCmb.getValue());
            boolean taskSuccess;

            if (updatingCustomer) {
                taskSuccess = updateCustomer(name, address, post, phone, division);
            } else {
                taskSuccess = createCustomer(name, address, post, phone, division);
            }

            if (taskSuccess) {
                FormManager.closeForm(saveButton);
            }
        }
    }

    private class SelectCountryEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            String selectedCountry = CountryCmb.getValue();

            Country country = CountryDbManager.getCountry(selectedCountry);

            if (country != null) {
                DivisionCmb.setItems(DivisionDbManager.getCountryDivisionsList(country.getId()));
            }
        }
    }

    private class CancelCustomerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            FormManager.closeForm(cancelButton);
        }
    }

}
