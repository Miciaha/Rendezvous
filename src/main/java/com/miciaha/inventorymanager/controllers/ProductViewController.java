package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.inventory.Inventory;
import com.miciaha.inventorymanager.inventory.Product;
import com.miciaha.inventorymanager.inventory.parts.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductViewController implements Initializable {
    private ObservableList<Part> prodParts = FXCollections.observableArrayList();
    private Product product;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public TableView<Part> partsTable;

    @FXML
    public TableView<Part> productPartTable;

    @FXML
    public TableColumn<Product, Integer> prodPartIdCol;

    @FXML
    public TableColumn<Product, String> prodPartNameCol;

    @FXML
    public TableColumn<Product, Integer> prodPartStockCol;

    @FXML
    public TableColumn<Product, Double> prodPartPriceCol;

    @FXML
    public TableColumn<Part, Integer> partIdCol;

    @FXML
    public TableColumn<Part, String> partNameCol;

    @FXML
    public TableColumn<Part, Integer> partStockCol;

    @FXML
    public TableColumn<Part, Double> partPriceCol;

    @FXML
    public TextField prodIdText;

    @FXML
    public TextField prodNameText;

    @FXML
    public TextField prodInvText;

    @FXML
    public TextField prodPriceText;

    @FXML
    public TextField prodMaxText;

    @FXML
    public TextField prodMinText;

    @FXML
    public Button addPartBtn;

    @FXML
    public Button removePartBtn;

    @FXML
    public Button saveProductBtn;

    @FXML
    public Button cancelProductBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        productPartTable.setItems(prodParts);

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        addPartBtn.setOnAction(new AddPartButtonHandler());
        removePartBtn.setOnAction(new RemovePartButtonHandler());
        saveProductBtn.setOnAction(new SaveProductButtonHandler());
        cancelProductBtn.setOnAction(new CancelProductButtonHandler());
    }

    public void initData(Product prod){
        this.product = prod;

        productPartTable.setItems(product.getAllAssociatedParts());
        prodParts = prod.getAllAssociatedParts();

        prodIdText.textProperty().setValue(String.valueOf(product.getId()));
        prodNameText.textProperty().setValue(product.getName());
        prodInvText.textProperty().setValue(String.valueOf(product.getStock()));
        prodPriceText.textProperty().setValue(String.valueOf(product.getPrice()));
        prodMaxText.textProperty().setValue(String.valueOf(product.getMax()));
        prodMinText.textProperty().setValue(String.valueOf(product.getMin()));
    }

    private Product createUpdateProduct(int id){
        String name = prodNameText.getText();


        // TODO: Create a catch for parsed values
        double price = ControllerUtility.parseDoubleField(prodPriceText);
        int inventory = ControllerUtility.parseIntField(prodInvText);
        int max = ControllerUtility.parseIntField(prodMaxText);
        int min = ControllerUtility.parseIntField(prodMinText);

        Product newProduct = new Product(id,name,price,inventory,max,min);

        for(Part part: prodParts){
            newProduct.addAssociatedPart(part);
        }

        return newProduct;
    }


    private class AddPartButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Part selectedPart = partsTable.getFocusModel().getFocusedItem();

            prodParts.add(selectedPart);
        }
    }

    private class RemovePartButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Part selectedPart = productPartTable.getFocusModel().getFocusedItem();

            prodParts.remove(selectedPart);
        }
    }

    private class SaveProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            boolean fieldsCorrect = ControllerUtility.validateFields(prodNameText,
                                    prodInvText,prodPriceText,prodMaxText,prodMinText);

            if(fieldsCorrect){
                if(prodIdText.getText().trim().isEmpty()){
                    int id = Inventory.getAllProducts().size() + 1;
                    Product newProduct = createUpdateProduct(id);
                    Inventory.addProduct(newProduct);
                } else{
                    int productId = Integer.parseInt(prodIdText.getText());
                    Product updatedProduct = createUpdateProduct(productId);

                    Product product = Inventory.lookupProduct(productId);
                    int index = Inventory.getAllProducts().indexOf(product);

                    Inventory.updateProduct(index, updatedProduct);
                }
                ControllerUtility.returnToMain(anchorPane);
            }
        }
    }

    private class CancelProductButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            ControllerUtility.returnToMain(anchorPane);
        }
    }
}
