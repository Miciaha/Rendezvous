package com.miciaha.rendezvous.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {

    @FXML
    public TextField titleTF;

    @FXML
    public Label titleErrorLabel;

    @FXML
    public TextField descriptionTF;

    @FXML
    public Label descriptionErrorLabel;

    @FXML
    public TextField locationTF;

    @FXML
    public Label locationErrorLabel;

    @FXML
    public TextField typeTF;

    @FXML
    public Label typeErrorLabel;

    @FXML
    public DatePicker startDate;

    @FXML
    public TextField startHour;

    @FXML
    public RadioButton startAmRadio;

    @FXML
    public RadioButton startPmRadio;

    @FXML
    public TextField startMin;

    @FXML
    public Label startErrorLabel;

    @FXML
    public DatePicker endDate;

    @FXML
    public TextField endHour;

    @FXML
    public RadioButton endAmRadio;

    @FXML
    public RadioButton endPmRadio;

    @FXML
    public TextField endMinute;

    @FXML
    public Label endErrorLabel;

    @FXML
    public Button saveBtn;

    @FXML
    public Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
