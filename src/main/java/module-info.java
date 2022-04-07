module com.miciaha.inventorymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.miciaha.inventorymanager to javafx.fxml;
    exports com.miciaha.inventorymanager;
    exports com.miciaha.inventorymanager.inventoryitems;
    opens com.miciaha.inventorymanager.inventoryitems to javafx.fxml;
    exports com.miciaha.inventorymanager.inventoryitems.entities.parts;
    opens com.miciaha.inventorymanager.inventoryitems.entities.parts to javafx.fxml;
    exports com.miciaha.inventorymanager.controllers;
    opens com.miciaha.inventorymanager.controllers to javafx.fxml;
    exports com.miciaha.inventorymanager.utilities;
    opens com.miciaha.inventorymanager.utilities to javafx.fxml;
    exports com.miciaha.inventorymanager.interfaces;
    opens com.miciaha.inventorymanager.interfaces to javafx.fxml;
    exports com.miciaha.inventorymanager.utilities.fields;
    opens com.miciaha.inventorymanager.utilities.fields to javafx.fxml;
    exports com.miciaha.inventorymanager.inventoryitems.entities;
    opens com.miciaha.inventorymanager.inventoryitems.entities to javafx.fxml;
}