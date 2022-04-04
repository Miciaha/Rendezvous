package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.interfaces.InventoryItem;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.Product;
import com.miciaha.inventorymanager.inventoryitems.parts.Part;
import com.miciaha.inventorymanager.utilities.Alerts;
import com.miciaha.inventorymanager.utilities.FormManager;
import com.miciaha.inventorymanager.utilities.TableManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.miciaha.inventorymanager.utilities.FormManager.*;

public class InventoryViewController implements Initializable {
    private final int partViewSizeX = 300;
    private final int partViewSizeY = 550;
    private final int productViewSizeX = 820;
    private final int productViewSizeY = 650;

    @FXML
    public TableView<Part> partsTable;

    @FXML
    public TableView<Product> productsTable;

    @FXML
    public Button btnAddPart;

    @FXML
    public Button btnModifyPart;

    @FXML
    public Button btnDeletePart;

    @FXML
    public Button btnAddProduct;

    @FXML
    public Button btnModifyProduct;

    @FXML
    public Button btnDeleteProduct;

    @FXML
    public TextField tfSearchParts;

    @FXML
    public TextField tfSearchProducts;

    @FXML
    public TableColumn<Product, Integer> prodIdCol;

    @FXML
    public TableColumn<Product, String> prodNameCol;

    @FXML
    public TableColumn<Product, Integer> prodStockCol;

    @FXML
    public TableColumn<Product, Double> prodPriceCol;

    @FXML
    public TableColumn<Part, Integer> partIdCol;

    @FXML
    public TableColumn<Part, String> partNameCol;

    @FXML
    public TableColumn<Part, Integer> partStockCol;

    @FXML
    public TableColumn<Part, Double> partPriceCol;

    @FXML
    public Button btnExit;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Label partTableLabel;

    @FXML
    public Label productTableLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());

        TableManager.PartTable.linkFields(partIdCol,partNameCol, partStockCol,partPriceCol);
        TableManager.ProductTable.linkFields(prodIdCol,prodNameCol,prodStockCol,prodPriceCol);

        btnAddPart.setOnAction(new AddPartButtonHandler());
        btnModifyPart.setOnAction(new ModifyPartButtonHandler());
        btnDeletePart.setOnAction(new DeletePartButtonHandler());

        btnAddProduct.setOnAction(new AddProductButtonHandler());
        btnModifyProduct.setOnAction(new ModifyProductButtonHandler());
        btnDeleteProduct.setOnAction(new DeleteProductButtonHandler());

        tfSearchParts.textProperty().addListener(new TableManager.SearchPartTableField(partTableLabel, partsTable));
        tfSearchProducts.textProperty().addListener(new TableManager.SearchProductTableField(productTableLabel, productsTable));

        btnExit.setOnAction(new ExitButtonHandler());
    }

    private class SearchPartsEventHandler implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            if(t1.trim().isEmpty()){
                partsTable.setItems(Inventory.getAllParts());
            } else{
                ObservableList<Part> foundPartsList = Inventory.lookupPart(t1);
                partsTable.setItems(foundPartsList);
            }
        }
    }

    private class SearchProductsEventHandler implements ChangeListener<String>{

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            if(t1.trim().isEmpty()){
                productsTable.setItems(Inventory.getAllProducts());
            } else{
                ObservableList<Product> foundProductsList = Inventory.lookupProduct(t1);
                productsTable.setItems(foundProductsList);
            }
        }
    }

    private class AddPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            new FormManager.Form(btnAddPart, "part-view.fxml", "Add Part Form", "main.css",
                    partViewSizeX, partViewSizeY)
                                     .openForm();
        }
    }

    private class AddProductButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            new FormManager.Form(btnAddProduct, "product-view.fxml", "Add Product Form", "main.css",
                    productViewSizeX, productViewSizeY)
                                     .openForm();
        }
    }

    private class ModifyPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Part selectedPart = partsTable.getFocusModel().getFocusedItem();

            if(selectedPart == null){
                Alerts.CustomAlert.createErrorAlert("edit");
            }else{
                new FormManager.EditForm<>(btnModifyPart, "part-view.fxml","Modify Part Form", "main.css",
                        partViewSizeX, partViewSizeY, selectedPart)
                        .openEditForm();
            }
        }
    }

    private class ModifyProductButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Product selectedProduct = productsTable.getFocusModel().getFocusedItem();

            if (selectedProduct == null) {
                Alerts.CustomAlert.createErrorAlert("edit");
            } else {
                new FormManager.EditForm<>(btnModifyProduct, "product-view.fxml", "Modify Part Form", "main.css",
                        productViewSizeX, productViewSizeY, selectedProduct)
                        .openEditForm();
            }
        }
    }

    private class DeletePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Part selectedPart = partsTable.getFocusModel().getFocusedItem();
            deleteInventoryItem((InventoryItem) selectedPart);
        }
    }

    private class DeleteProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Product selectedProd = productsTable.getFocusModel().getFocusedItem();
            deleteInventoryItem(selectedProd);
        }
    }

    private class ExitButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            FormManager.closeForm(btnExit);
            System.exit(0);
        }
    }
}