module com.miciaha.inventorymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.miciaha.inventorymanager to javafx.fxml;
    exports com.miciaha.inventorymanager;
}