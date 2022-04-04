package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.interfaces.FormEditor;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.parts.InHouse;
import com.miciaha.inventorymanager.inventoryitems.parts.Outsourced;
import com.miciaha.inventorymanager.inventoryitems.parts.Part;
import com.miciaha.inventorymanager.inventoryitems.parts.PartType;
import com.miciaha.inventorymanager.utilities.fields.FieldTracker;
import com.miciaha.inventorymanager.utilities.fields.FieldType;
import com.miciaha.inventorymanager.utilities.fields.FormField;
import com.miciaha.inventorymanager.utilities.FormManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.miciaha.inventorymanager.utilities.fields.FieldValidator.LogicChecker.checkStock;

public class PartViewController implements Initializable, FormEditor<Part> {
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public ToggleGroup partType;
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
    public TextField partMachineText;
    @FXML
    public TextField partCompanyText;
    @FXML
    public TextField partMinText;
    @FXML
    public Label machineIdLabel;
    @FXML
    public Label companyNameLabel;
    @FXML
    public Label companyErrorLabel;
    @FXML
    public Label machineErrorLabel;
    @FXML
    public Label nameErrorLabel;
    @FXML
    public Label minErrorLabel;
    @FXML
    public Label priceErrorLabel;
    @FXML
    public Label maxErrorLabel;
    @FXML
    public Label invErrorLabel;
    @FXML
    public Label partViewTitle;
    @FXML
    public Button savePartBtn;
    @FXML
    public Button cancelPartBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savePartBtn.setOnAction(new SavePartButtonHandler());
        cancelPartBtn.setOnAction(new CancelPartButtonHandler());

        partType.selectedToggleProperty().addListener(new PartTypeRadioHandler());

        FieldTracker.Fields.clear();
        FieldTracker.Fields.add(new FormField(partNameText,nameErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(partMachineText,machineErrorLabel,FieldType.INTEGER));
        FieldTracker.Fields.add(new FormField(partCompanyText,companyErrorLabel,FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(partPriceText,priceErrorLabel,FieldType.DOUBLE));
        FieldTracker.Fields.add(new FormField(partMinText,minErrorLabel,FieldType.INTEGER));
        FieldTracker.Fields.add(new FormField(partMaxText,maxErrorLabel,FieldType.INTEGER));
        FieldTracker.Fields.add(new FormField(partInvText,invErrorLabel,FieldType.INTEGER));
        FieldTracker.Fields.linkToButton(savePartBtn);
    }

    @Override
    public void initData(Part part){

        String partDetail = ((PartType) part).getTypeDetail();

        partIdText.textProperty().setValue(String.valueOf(part.getId()));
        partNameText.textProperty().setValue(part.getName());
        partInvText.textProperty().setValue(String.valueOf(part.getStock()));
        partPriceText.textProperty().setValue(String.valueOf(part.getPrice()));
        partMaxText.textProperty().setValue(String.valueOf(part.getMax()));
        partMinText.textProperty().setValue(String.valueOf(part.getMin()));

        // Set label based on part type
        if (part instanceof Outsourced) {
            outsourceRadio.selectedProperty().setValue(true);
            inhouseRadio.selectedProperty().setValue(false);
            inhouseRadio.disableProperty().setValue(true);
            partCompanyText.textProperty().setValue(partDetail);
        } else{
            inhouseRadio.selectedProperty().setValue(true);
            outsourceRadio.disableProperty().setValue(true);
            outsourceRadio.selectedProperty().setValue(false);
            partMachineText.textProperty().setValue(partDetail);
        }

        setPartType();

        FieldTracker.Fields.editFields();
    }

    private class PartTypeRadioHandler implements ChangeListener<Toggle>{

        @Override
        public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
            setPartType();
        }
    }

    private void setPartType() {
        if (partType.getSelectedToggle() == inhouseRadio) {
            switchRadioFieldValues(partMachineText, machineIdLabel, machineErrorLabel, partCompanyText,companyNameLabel,companyErrorLabel);
        } else if (partType.getSelectedToggle() == outsourceRadio) {
            switchRadioFieldValues(partCompanyText,companyNameLabel,companyErrorLabel, partMachineText, machineIdLabel, machineErrorLabel);
        }
    }

    private void switchRadioFieldValues(TextField newField, Label newTextLabel, Label newErrorLabel, TextField oldField, Label oldTextLabel, Label oldErrorLabel){
        String currentText = oldField.textProperty().getValue();

        if (!currentText.trim().isEmpty()) {
            newField.textProperty().setValue(currentText);
            oldField.textProperty().setValue("");
        }
        newField.visibleProperty().setValue(true);
        newTextLabel.visibleProperty().setValue(true);
        newErrorLabel.visibleProperty().setValue(true);

        oldField.visibleProperty().setValue(false);
        oldTextLabel.visibleProperty().setValue(false);
        oldErrorLabel.visibleProperty().setValue(false);
        oldErrorLabel.textProperty().setValue("");

    }

    private boolean updatePart(int min, int max, int inventory, String name, Double price){
        int partId = Integer.parseInt(partIdText.getText());
        Part part = Inventory.lookupPart(partId);

        if(Objects.isNull(part)){
            return false;
        }

        part.setMin(min);
        part.setMax(max);
        part.setStock(inventory);
        part.setName(name);
        part.setPrice(price);

        if (outsourceRadio.selectedProperty().getValue()){
            String companyName = partCompanyText.getText();
            ((Outsourced)part).setCompanyName(companyName);

        }
        else if(inhouseRadio.selectedProperty().getValue()){
            int machineId = Integer.parseInt(partMachineText.getText());
            ((InHouse)part).setMachineId(machineId);
        }

        int index = Inventory.getAllParts().indexOf(part);
        Inventory.updatePart(index, part);
        return true;
    }

    private boolean createPart(int min, int max, int inventory, String name, Double price){
        int id = Inventory.getAllParts().size() + 1;

        if (outsourceRadio.selectedProperty().getValue()){
            String companyName = partCompanyText.getText();

            Outsourced newPart = new Outsourced(id, name, price, inventory, min, max,companyName);
            Inventory.addPart(newPart);
            return true;
        }
        else if(inhouseRadio.selectedProperty().getValue()){
            int machineId = Integer.parseInt(partMachineText.getText());

            InHouse newPart = new InHouse(id, name, price, inventory, min, max, machineId);
            Inventory.addPart(newPart);
            return true;
        }
        return false;
    }


    private class SavePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){

            boolean partSaved = false;

            String name = partNameText.getText();
            double price =  Double.parseDouble(partPriceText.getText());
            int inventory = Integer.parseInt(partInvText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int min = Integer.parseInt(partMinText.getText());

            if(checkStock(min,max,inventory)){
                if (partIdText.getText().trim().isEmpty()){
                    partSaved = createPart(min, max, inventory,name,price);
                } else{
                    partSaved = updatePart(min, max, inventory,name,price);
                }
            }
            if(partSaved){
                FormManager.closeForm(savePartBtn);
            }
        }
    }

    private class CancelPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            FormManager.closeForm(cancelPartBtn);
        }
    }
}
