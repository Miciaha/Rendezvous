package com.miciaha.inventorymanager.controllers;

import com.miciaha.inventorymanager.interfaces.FormEditor;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
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

/**
 * The ProductViewController manages the product application view.
 * By using the JavaFX Initializable interface, the controller is able to set
 * the necessary table links and button managers.
 * <p>
 * Using the FormEditor interface, the controller is recognized for having an initData method
 * which loads the selected data into a form for users.
 * <p>
 * Form field types are created then validated through the FieldTracker class.
 *
 * @see com.miciaha.inventorymanager.utilities.fields.FormField
 * @see com.miciaha.inventorymanager.utilities.fields.FieldTracker
 * Tables are populated using the TableManager class.
 * @see com.miciaha.inventorymanager.utilities.TableManager
 */
public class ProductViewController implements Initializable, FormEditor<Product> {

    private ObservableList<Part> prodParts = FXCollections.observableArrayList();

    /**
     * The Anchor pane.
     */
    @FXML
    public AnchorPane anchorPane;
    /**
     * The Parts table.
     */
    @FXML
    public TableView<Part> partsTable;
    /**
     * The Product part table.
     */
    @FXML
    public TableView<Part> productPartTable;
    /**
     * The Prod part id col.
     */
    @FXML
    public TableColumn<Product, Integer> prodPartIdCol;
    /**
     * The Prod part name col.
     */
    @FXML
    public TableColumn<Product, String> prodPartNameCol;
    /**
     * The Prod part stock col.
     */
    @FXML
    public TableColumn<Product, Integer> prodPartStockCol;
    /**
     * The Prod part price col.
     */
    @FXML
    public TableColumn<Product, Double> prodPartPriceCol;
    /**
     * The Part id col.
     */
    @FXML
    public TableColumn<Part, Integer> partIdCol;
    /**
     * The Part name col.
     */
    @FXML
    public TableColumn<Part, String> partNameCol;
    /**
     * The Part stock col.
     */
    @FXML
    public TableColumn<Part, Integer> partStockCol;
    /**
     * The Part price col.
     */
    @FXML
    public TableColumn<Part, Double> partPriceCol;
    /**
     * The Prod id text.
     */
    @FXML
    public TextField prodIdText;
    /**
     * The Prod name text.
     */
    @FXML
    public TextField prodNameText;
    /**
     * The Prod inv text.
     */
    @FXML
    public TextField prodInvText;
    /**
     * The Prod price text.
     */
    @FXML
    public TextField prodPriceText;
    /**
     * The Prod max text.
     */
    @FXML
    public TextField prodMaxText;
    /**
     * The Prod min text.
     */
    @FXML
    public TextField prodMinText;
    /**
     * The Search all parts.
     */
    @FXML
    public TextField searchAllParts;
    /**
     * The Search prod parts.
     */
    @FXML
    public TextField searchProdParts;
    /**
     * The Part prod table label.
     */
    @FXML
    public Label partProdTableLabel;
    /**
     * The Part table label.
     */
    @FXML
    public Label partTableLabel;
    /**
     * The Name error label.
     */
    @FXML
    public Label nameErrorLabel;
    /**
     * The Inv error label.
     */
    @FXML
    public Label invErrorLabel;
    /**
     * The Price error label.
     */
    @FXML
    public Label priceErrorLabel;
    /**
     * The Max error label.
     */
    @FXML
    public Label maxErrorLabel;
    /**
     * The Min error label.
     */
    @FXML
    public Label minErrorLabel;
    /**
     * The Add part btn.
     */
    @FXML
    public Button addPartBtn;
    /**
     * The Remove part btn.
     */
    @FXML
    public Button removePartBtn;
    /**
     * The Save product btn.
     */
    @FXML
    public Button saveProductBtn;
    /**
     * The Cancel product btn.
     */
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
