package com.miciaha.rendezvous.utilities;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.TableSearcher;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;

/**
 * The type Table manager handles linking tables to static and dynamic data.
 * The Table manager also handles the table search functionality.
 *
 */
public class TableManager {

    /**
     * The type Base table link.
     *
     * @param <T> the type parameter
     */
    public static class BaseTableLink<T> {
        /**
         * Instantiates a new Base table link.
         *
         * @param idCol    the id col
         * @param nameCol  the name col
         * @param stockCol the stock col
         * @param priceCol the price col
         */
        BaseTableLink(TableColumn<T, Integer> idCol, TableColumn<T, String> nameCol,
                      TableColumn<T, Integer> stockCol, TableColumn<T, Double> priceCol) {

            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
    }

    public static void LinkAppointmentColumns(TableColumn<Appointment,Integer> id, TableColumn<Appointment,Integer> userID, TableColumn<Appointment,Integer> custID,
                                              TableColumn<Appointment,String> title, TableColumn<Appointment,String> description, TableColumn<Appointment,String> location,
                                              TableColumn<Appointment,String> type, TableColumn<Appointment,String> contact, TableColumn<Appointment, LocalDateTime> start,
                                              TableColumn<Appointment, LocalDateTime> end){
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        userID.setCellValueFactory(new PropertyValueFactory<>("User"));
        custID.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        contact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));

    }

    public static void LinkCustomerColumns(TableColumn<Customer, Integer> id, TableColumn<Customer, String> name, TableColumn<Customer, String> address,
                                           TableColumn<Customer, String> postCode, TableColumn<Customer, String> phone){
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postCode.setCellValueFactory(new PropertyValueFactory<>("PostCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    /**
     * The type Search table field.
     *
     * @param <T> the type parameter
     */
    public static class SearchTableField<T> implements TableSearcher<T> {
        /**
         * The Error label.
         */
        protected Label errorLabel;
        /**
         * The Search text.
         */
        protected String searchText;
        /**
         * The Error text item.
         */
        protected String errorTextItem;
        /**
         * The Table.
         */
        protected TableView<T> table;

        /**
         * Instantiates a new Search table field.
         *
         * @param errorLabel the error label
         * @param table      the table
         */
        public SearchTableField(Label errorLabel, TableView<T> table) {
            this.table = table;
            this.errorLabel = errorLabel;
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            this.searchText = t1;
            if (t1.trim().isEmpty()) {
                setEmptyView();
                setDefaultView();
            } else {
                if (!setTableItems()) {
                    setErrorLabel();
                }
            }
        }

        public boolean setTableItems() {
            return false;
        }

        public void setDefaultView() {
        }

        /**
         * Set empty view.
         */
        public void setEmptyView() {
            errorLabel.getStyleClass().remove("search-error");
            errorLabel.textProperty().setValue("No " + errorTextItem + " found in the database");
        }

        /**
         * Set error label.
         */
        public void setErrorLabel() {
            if (!errorLabel.getStyleClass().contains("search-error")) {
                errorLabel.getStyleClass().add("search-error");
            }
            errorLabel.textProperty().setValue("No " + errorTextItem + " matching " + searchText + " were found in the database.");
        }
    }
}