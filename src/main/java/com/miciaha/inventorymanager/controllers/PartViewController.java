package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.inventory.Inventory;
import com.miciaha.inventorymanager.inventory.parts.InHouse;
import com.miciaha.inventorymanager.inventory.parts.Outsourced;
import com.miciaha.inventorymanager.inventory.parts.Part;
import com.miciaha.inventorymanager.inventory.parts.PartType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PartViewController implements Initializable {

    @FXML
    public AnchorPane anchorPane;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savePartBtn.setOnAction(new SavePartButtonHandler());
        cancelPartBtn.setOnAction(new CancelPartButtonHandler());
        inhouseRadio.setOnAction(new InhouseRadioHandler());
        outsourceRadio.setOnAction(new OutsourcedRadioHandler());
    }

    public void initData(Part part){

        // Check part object type; Change object specific form field name and set radio button
        if (part instanceof Outsourced) {
            machineIdLabel.visibleProperty().setValue(false);
            inhouseRadio.selectedProperty().setValue(false);
            companyNameLabel.visibleProperty().setValue(true);
            outsourceRadio.selectedProperty().setValue(true);
        }
        String partDetail = ((PartType) part).getTypeDetail();

        partIdText.textProperty().setValue(String.valueOf(part.getId()));
        partNameText.textProperty().setValue(part.getName());
        partInvText.textProperty().setValue(String.valueOf(part.getStock()));
        partPriceText.textProperty().setValue(String.valueOf(part.getPrice()));
        partMaxText.textProperty().setValue(String.valueOf(part.getMax()));
        partMinText.textProperty().setValue(String.valueOf(part.getMin()));
        partMachineCompany.textProperty().setValue(partDetail);
    }

    private class InhouseRadioHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            machineIdLabel.visibleProperty().setValue(true);
            companyNameLabel.visibleProperty().setValue(false);
        }
    }

    private class OutsourcedRadioHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            machineIdLabel.visibleProperty().setValue(false);
            companyNameLabel.visibleProperty().setValue(true);
        }
    }

    private Part createUpdatePart(int id){
        String name = partNameText.getText();

        // TODO: Create a catch for parsed values
        double price = ControllerUtility.parseDoubleField(partPriceText);
        int inventory = ControllerUtility.parseIntField(partInvText);
        int max = ControllerUtility.parseIntField(partMaxText);
        int min = ControllerUtility.parseIntField(partMinText);

        if (outsourceRadio.selectedProperty().getValue()){
            String companyName = partMachineCompany.getText();

            return new Outsourced(id, name, price, inventory, min, max, companyName);
        }
        else if(inhouseRadio.selectedProperty().getValue()){
            int machineId = ControllerUtility.parseIntField(partMachineCompany);

            if (machineId == -1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Incorrect type in machine ID field");
                alert.setContentText("- Please enter a number into the machine ID field.");

                alert.showAndWait();
                return null;
            }

            return new InHouse(id, name, price, inventory, min, max, machineId);
        }
        return null;
    }

    private class SavePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean fieldsCorrect = ControllerUtility.validateFields(partNameText,
                                    partInvText,partPriceText,partMaxText,partMinText);

            if(fieldsCorrect){

                if (partIdText.getText().trim().isEmpty()){
                    int id = Inventory.getAllParts().size() + 1;

                    Part newPart = createUpdatePart(id);
                    Inventory.addPart(newPart);
                } else{
                    int partId = Integer.parseInt(partIdText.getText());
                    Part newPart = createUpdatePart(partId);

                    Part part = Inventory.lookupPart(partId);
                    int index = Inventory.getAllParts().indexOf(part);

                    Inventory.updatePart(index, newPart);
                }
                ControllerUtility.returnToMain(anchorPane);
            }
        }
    }

    private class CancelPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            ControllerUtility.returnToMain(anchorPane);
        }
    }
}
