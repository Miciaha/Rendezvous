package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.InventoryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.OptionalDouble;

public final class ControllerUtility {

    static public void returnToMain(AnchorPane anchorPane){
        anchorPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("main-view.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().add(parent);
    }

    static public int parseIntField(TextField field){
        try {
            return Integer.parseInt(field.getText());
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
        return -1;
    }

    static public Double parseDoubleField(TextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return Double.NaN;
    }

    static public boolean validateFields(TextField nameText,TextField invText,TextField priceText,
                                         TextField maxText,TextField minText){
        StringBuilder errors = new StringBuilder();

        if(nameText.getText().trim().isEmpty()){
            errors.append("\n- Please add product name.");
        }

        if(invText.getText().trim().isEmpty()){
            errors.append("\n- Please add inventory count.");
        } else if(parseIntField(invText) == -1){
            errors.append("\n- Please enter a valid number for inventory.");
        }

        if(priceText.getText().trim().isEmpty()){
            errors.append("\n- Please add price/cost.");
        } else if(Double.isNaN(parseDoubleField(priceText))) {
            errors.append("\n- Please enter a valid price number.");
        }

        if(maxText.getText().trim().isEmpty()){
            errors.append("\n- Please add max.");
        } else if(parseIntField(maxText) == -1){
            errors.append("\n- Please enter a valid number for max field.");
        }

        if(minText.getText().trim().isEmpty()){
            errors.append("\n- Please add min.");
        } else if(parseIntField(minText) == -1){
            errors.append("\n- Please enter a valid number for min field.");
        }

        if(errors.length() > 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty or Incorrect");
            alert.setContentText(errors.toString());

            alert.showAndWait();
            return false;
        }

        return true;
    }
}
