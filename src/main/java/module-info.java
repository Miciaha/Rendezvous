module com.miciaha.rendezvous {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.miciaha.rendezvous to javafx.fxml;
    exports com.miciaha.rendezvous;
    exports com.miciaha.rendezvous.controllers;
    exports com.miciaha.rendezvous.entities;
    exports com.miciaha.rendezvous.entities.reports;
    exports com.miciaha.rendezvous.persistence;
    opens com.miciaha.rendezvous.persistence to javafx.fxml;
    opens com.miciaha.rendezvous.entities to javafx.fxml;
    opens com.miciaha.rendezvous.entities.reports to javafx.fxml;
    opens com.miciaha.rendezvous.controllers to javafx.fxml;
    exports com.miciaha.rendezvous.utilities;
    opens com.miciaha.rendezvous.utilities to javafx.fxml;
    exports com.miciaha.rendezvous.interfaces;
    opens com.miciaha.rendezvous.interfaces to javafx.fxml;
    exports com.miciaha.rendezvous.utilities.fields;
    opens com.miciaha.rendezvous.utilities.fields to javafx.fxml;
}