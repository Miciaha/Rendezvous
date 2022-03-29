package com.miciaha.inventorymanager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PartViewController {
    
    @FXML
    public RadioButton inhouseRadio;
    
    @FXML
    public RadioButton outsourceRadio;
    
    @FXML
    public TextField partIdText;

    @FXML
    public TextField partNameText;

    @FXML
    public TextField partInvText;

    @FXML
    public TextField partPriceText;

    @FXML
    public TextField partMaxText;

    @FXML
    public TextField partMachineCompany;

    @FXML
    public TextField partMinText;

    @FXML
    public Label machineIdLabel;

    @FXML
    public Label companyNameLabel;

    @FXML
    public Button savePartBtn;

    @FXML
    public Button cancelPartBtn;
}
