package com.miciaha.inventorymanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InventoryController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}