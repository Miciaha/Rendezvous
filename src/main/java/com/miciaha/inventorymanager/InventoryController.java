package com.miciaha.inventorymanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryController {
    public TableView partsTable;
    public TableView productsTable;
    public Button btnAddPart;
    public Button btnModifyPart;
    public Button btnDeletePart;
    public Button btnAddProduct;
    public Button btnModifyProduct;
    public Button btnDeleteProduct;
    public TextField tfSearchParts;
    public TextField tfSearchProducts;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}