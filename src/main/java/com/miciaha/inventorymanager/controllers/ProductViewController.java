package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.interfaces.FormEditor;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.commands.CommandType;
import com.miciaha.inventorymanager.inventoryitems.commands.ModifyProductPart;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import com.miciaha.inventorymanager.utilities.FormManager;
import com.miciaha.inventorymanager.utilities.TableManager;
import com.miciaha.inventorymanager.utilities.fields.FieldTracker;
import com.miciaha.inventorymanager.utilities.fields.FieldType;
import com.miciaha.inventorymanager.utilities.fields.FormField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.miciaha.inventorymanager.utilities.fields.FieldValidator.checkFieldLogic.checkStock;

public class ProductViewController implements Initializable, FormEditor<Product> {

    private ObservableList<Part> prodParts = FXCollections.observableArrayList();

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
    public TextField searchAllParts;
    @FXML
    public TextField searchProdParts;
    @FXML
    public Label partProdTableLabel;
    @FXML
    public Label partTableLabel;
    @FXML
    public Label nameErrorLabel;
    @FXML
    public Label invErrorLabel;
    @FXML
    public Label priceErrorLabel;
    @FXML
    public Label maxErrorLabel;
    @FXML
    public Label minErrorLabel;
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

        new TableManager.PartTableLink(partIdCol, partNameCol, partStockCol, partPriceCol);
        new TableManager.ProductTableLink(prodPartIdCol, prodPartNameCol, prodPartStockCol, prodPartPriceCol);

        searchAllParts.textProperty().addListener(new TableManager.SearchPartTableField(partTableLabel, partsTable));
        searchProdParts.textProperty().addListener(new TableManager.SearchProductPartTableField(partProdTableLabel, productPartTable, prodParts));

        FieldTracker.Fields.clear();
        FieldTracker.Fields.add(new FormField(prodNameText, nameErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(prodPriceText, priceErrorLabel, FieldType.DOUBLE));
        FieldTracker.Fields.add(new FormField(prodMaxText, maxErrorLabel, FieldType.INTEGER));
        FieldTracker.Fields.add(new FormField(prodMinText, minErrorLabel, FieldType.INTEGER));
        FieldTracker.Fields.add(new FormField(prodInvText, invErrorLabel, FieldType.INTEGER));
        FieldTracker.Fields.linkToButton(saveProductBtn);

        addPartBtn.setOnAction(new AddPartButtonHandler());
        removePartBtn.setOnAction(new RemovePartButtonHandler());
        saveProductBtn.setOnAction(new SaveProductButtonHandler());
        cancelProductBtn.setOnAction(new CancelProductButtonHandler());
    }

    @Override
    public void initData(Product prod) {
        productPartTable.setItems(prod.getAllAssociatedParts());
        prodParts = prod.getAllAssociatedParts();

        prodIdText.textProperty().setValue(String.valueOf(prod.getId()));
        prodNameText.textProperty().setValue(prod.getName());
        prodInvText.textProperty().setValue(String.valueOf(prod.getStock()));
        prodPriceText.textProperty().setValue(String.valueOf(prod.getPrice()));
        prodMaxText.textProperty().setValue(String.valueOf(prod.getMax()));
        prodMinText.textProperty().setValue(String.valueOf(prod.getMin()));
    }

    private class AddPartButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Part selectedPart = partsTable.getFocusModel().getFocusedItem();
            prodParts.add(selectedPart);
        }
    }

    private class RemovePartButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Part selectedPart = productPartTable.getFocusModel().getFocusedItem();
            prodParts.remove(selectedPart);
        }
    }

    private class SaveProductButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String name = prodNameText.getText();
            double price = Double.parseDouble(prodPriceText.getText());
            int inventory = Integer.parseInt(prodInvText.getText());
            int max = Integer.parseInt(prodMaxText.getText());
            int min = Integer.parseInt(prodMinText.getText());

            boolean productSaved;

            if (checkStock(min, max, inventory)) {
                if (prodIdText.getText().trim().isEmpty()) {
                    productSaved = createProduct(name, price, inventory, min, max);
                } else {
                    productSaved = updateProduct(name, price, inventory, min, max);
                }

                if (productSaved) {
                    FormManager.closeForm(saveProductBtn);
                }
            }
        }
    }

    private boolean createProduct(String name, double price, int inv, int min, int max) {
        int id = Inventory.getAllProducts().size() + 1;

        Product newProduct = new Product(id, name, price, inv, min, max);

        for (Part part : prodParts) {
            newProduct.addAssociatedPart(part);
        }
        Inventory.addProduct(newProduct);
        return true;
    }

    private boolean updateProduct(String name, double price, int inv, int min, int max) {
        int productId = Integer.parseInt(prodIdText.getText());

        Product product = Inventory.lookupProduct(productId);
        if (product != null) {
            int index = Inventory.getAllProducts().indexOf(product);
            product.setName(name);
            product.setPrice(price);
            product.setStock(inv);
            product.setMin(min);
            product.setMax(max);

            Inventory.updateProduct(index, product);
            return true;
        } else {
            return false;
        }
    }

    private class CancelProductButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FormManager.closeForm(cancelProductBtn);
        }
    }
}
