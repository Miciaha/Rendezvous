module com.miciaha.inventorymanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.miciaha.rendezvous to javafx.fxml;
    exports com.miciaha.rendezvous;
    exports com.miciaha.rendezvous.controllers;
    opens com.miciaha.rendezvous.controllers to javafx.fxml;
    exports com.miciaha.rendezvous.utilities;
    opens com.miciaha.rendezvous.utilities to javafx.fxml;
    exports com.miciaha.rendezvous.interfaces;
    opens com.miciaha.rendezvous.interfaces to javafx.fxml;
    exports com.miciaha.rendezvous.utilities.fields;
    opens com.miciaha.rendezvous.utilities.fields to javafx.fxml;
}