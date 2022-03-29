module com.miciaha.inventorymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.miciaha.inventorymanager to javafx.fxml;
    exports com.miciaha.inventorymanager;
    exports com.miciaha.inventorymanager.inventory;
    opens com.miciaha.inventorymanager.inventory to javafx.fxml;
    exports com.miciaha.inventorymanager.inventory.parts;
    opens com.miciaha.inventorymanager.inventory.parts to javafx.fxml;
    exports com.miciaha.inventorymanager.controllers;
    opens com.miciaha.inventorymanager.controllers to javafx.fxml;
}