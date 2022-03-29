package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.InventoryApplication;
import com.miciaha.inventorymanager.inventory.Inventory;
import com.miciaha.inventorymanager.inventory.Product;
import com.miciaha.inventorymanager.inventory.parts.InHouse;
import com.miciaha.inventorymanager.inventory.parts.Part;
import javafx.application.Platform;
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

        Part part1 = new InHouse(1, "Test1", 2.22, 3, 5, 2, 3);
        Product product1 = new Product(1, "Test2", 43.23, 10, 0, 10);
        Inventory.addPart(part1);
        Inventory.addProduct(product1);

        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        btnDeletePart.setOnAction(new DeletePartButtonHandler());
        btnAddPart.setOnAction(new AddPartButtonHandler());
        btnModifyPart.setOnAction(new ModifyPartButtonHandler());
        btnExit.setOnAction(new ExitButtonHandler());
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

    private class ModifyPartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
// TODO: Pass table focus to PartViewController
        }
    }

    private class DeletePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Inventory.deletePart(partsTable.getFocusModel().getFocusedItem());
        }
    }

    private class ExitButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Platform.exit();
            System.exit(0);
        }
    }
}