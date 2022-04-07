package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.inventoryitems.commands.CommandType;
import com.miciaha.inventorymanager.inventoryitems.commands.ModifyPart;
import com.miciaha.inventorymanager.inventoryitems.commands.ModifyProduct;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.Alerts;
import com.miciaha.inventorymanager.utilities.FormManager;
import com.miciaha.inventorymanager.utilities.TableManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * The InventoryViewController manages the main application view.
 * By using the JavaFX Initializable interface, the controller is able to set
 * the necessary table links and button managers.
 *
 * @author Miciaha Ivey
 * FUTURE_ENHANCEMENT
 */
public class InventoryViewController implements Initializable {
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

        new TableManager.PartTableLink(partIdCol,partNameCol, partStockCol,partPriceCol);
        new TableManager.ProductTableLink(prodIdCol,prodNameCol,prodStockCol,prodPriceCol);

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

    /**
     * The AddPartButtonHandler opens the form to create a new part.
     * The button pressed is passed to reference the current stage.
     */
    private class AddPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            new FormManager.CreatePartForm(btnAddPart);
        }
    }

    /**
     * The AddProductButtonHandler opens the form to create a new product.
     * The button pressed is passed to reference the current stage.
     */
    private class AddProductButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            new FormManager.CreateProductForm(btnAddProduct);
        }
    }

    /**
     * The ModifyPartButtonHandler opens the form to modify the part selected.
     * If there is no part selected, an alert is thrown to the user.
     */
    private class ModifyPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Part selectedPart = partsTable.getFocusModel().getFocusedItem();

            if(selectedPart == null){
                new Alerts.CustomAlert.ErrorAlert("modify", "part", "No part selected.");
            }else{
                new FormManager.EditPartForm(btnModifyPart,selectedPart);
            }
        }
    }

    /**
     * The ModifyProductButtonHandler opens the form to modify the product selected.
     * If there is no product selected an error is thrown.
     */
    private class ModifyProductButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Product selectedProduct = productsTable.getFocusModel().getFocusedItem();

            if(selectedProduct == null){
                new Alerts.CustomAlert.ErrorAlert("modify", "product", "No product selected.");
            }else {
                new FormManager.EditProductForm(btnModifyProduct, selectedProduct);
            }
        }
    }

    /**
     * The DeletePartButtonHandler runs the delete command to remove a part from Inventory.
     * If the part is related to any product, an error is thrown to the user.
     */
    private class DeletePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Part selectedPart = partsTable.getFocusModel().getFocusedItem();

            if(!isProductPart(selectedPart)){
                new ModifyPart(selectedPart, CommandType.DELETE);
            } else{
                new Alerts.CustomAlert.ErrorAlert("delete", "part", "Part is related to a product.");
            }
        }

        private boolean isProductPart(Part selectedPart){
            ObservableList<Product> allProducts = Inventory.getAllProducts();

            for (Product product: allProducts){
                ObservableList<Part> productParts = product.getAllAssociatedParts();

                for(Part prodPart: productParts){
                    if(prodPart == selectedPart){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * The DeleteProductButtonHandler runs the delete product command to remove a product from Inventory.
     */
    private class DeleteProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Product selectedProd = productsTable.getFocusModel().getFocusedItem();
            new ModifyProduct(selectedProd, CommandType.DELETE);
        }
    }

    /**
     * The ExitButtonHandler closes the main form and stops the application from running.
     */
    private class ExitButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            FormManager.closeForm(btnExit);
            System.exit(0);
        }
    }
}