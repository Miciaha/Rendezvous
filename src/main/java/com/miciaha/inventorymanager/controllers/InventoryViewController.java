package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.InventoryApplication;
import com.miciaha.inventorymanager.inventory.Inventory;
import com.miciaha.inventorymanager.inventory.Product;
import com.miciaha.inventorymanager.inventory.parts.InHouse;
import com.miciaha.inventorymanager.inventory.parts.Part;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public TableColumn<Product, Integer> prodLevelCol;

    @FXML
    public TableColumn<Product, Double> prodPriceCol;

    @FXML
    public TableColumn<Part, Integer> partIdCol;

    @FXML
    public TableColumn<Part, String> partNameCol;

    @FXML
    public TableColumn<Part, Integer> partLevelCol;

    @FXML
    public TableColumn<Part, Double> partPriceCol;

    @FXML
    public Button btnExit;

    @FXML
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());

        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        btnAddPart.setOnAction(new AddPartButtonHandler());
        btnModifyPart.setOnAction(new ModifyPartButtonHandler());
        btnDeletePart.setOnAction(new DeletePartButtonHandler());

        btnAddProduct.setOnAction(new AddProductButtonHandler());
        btnModifyProduct.setOnAction(new ModifyProductButtonHandler());
        btnDeleteProduct.setOnAction(new DeleteProductButtonHandler());

        tfSearchParts.textProperty().addListener(new SearchPartsEventHandler());
        tfSearchProducts.textProperty().addListener(new SearchProductsEventHandler());

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
            anchorPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("part-view.fxml"));
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            anchorPane.getChildren().add(parent);
        }
    }

    private class AddProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            anchorPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("product-view.fxml"));
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            anchorPane.getChildren().add(parent);
        }
    }

    private class ModifyPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Part part = partsTable.getFocusModel().getFocusedItem();

            if (!(part == null)){
                anchorPane.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("part-view.fxml"));
                Parent parent = null;
                try {
                    parent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PartViewController controller = loader.getController();
                controller.initData(part);
                anchorPane.getChildren().add(parent);
            }
        }
    }

    private class ModifyProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Product product = productsTable.getFocusModel().getFocusedItem();

            if(!(product == null)){
                anchorPane.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("product-view.fxml"));
                Parent parent = null;
                try {
                    parent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ProductViewController controller = loader.getController();
                controller.initData(product);
                anchorPane.getChildren().add(parent);
            }
        }
    }

    private class DeletePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Inventory.deletePart(partsTable.getFocusModel().getFocusedItem());
        }
    }

    private class DeleteProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Inventory.deleteProduct(productsTable.getFocusModel().getFocusedItem());
        }
    }

    static private class ExitButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Platform.exit();
            System.exit(0);
        }
    }
}