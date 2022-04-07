package com.miciaha.inventorymanager.utilities;

import com.miciaha.inventorymanager.interfaces.TableSearcher;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.entities.Product;
import com.miciaha.inventorymanager.inventoryitems.entities.parts.Part;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableManager {

    public static class BaseTableLink<T> {
        BaseTableLink(TableColumn<T, Integer> idCol, TableColumn<T, String> nameCol,
                      TableColumn<T, Integer> stockCol, TableColumn<T, Double> priceCol){

            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
    }

    public static class PartTableLink extends BaseTableLink<Part> {
        public PartTableLink(TableColumn<Part, Integer> idCol, TableColumn<Part, String> nameCol, TableColumn<Part, Integer> stockCol, TableColumn<Part, Double> priceCol) {
            super(idCol, nameCol, stockCol, priceCol);
        }
    }

    public static class ProductTableLink extends BaseTableLink<Product> {
        public ProductTableLink(TableColumn<Product, Integer> idCol, TableColumn<Product, String> nameCol, TableColumn<Product, Integer> stockCol, TableColumn<Product, Double> priceCol) {
            super(idCol, nameCol, stockCol, priceCol);
        }
    }

    public static class SearchTableField<T> implements TableSearcher<T> {
        protected Label errorLabel;
        protected String searchText;
        protected String errorTextItem;
        protected TableView<T> table;

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
                 if(!setTableItems()){
                     setErrorLabel();
                 }
            }
        }

        public boolean setTableItems(){return false;}

        public void setDefaultView(){}

        public void setEmptyView(){
            errorLabel.getStyleClass().remove("search-error");
            errorLabel.textProperty().setValue("No " + errorTextItem + " found in the database");
        }

        public void setErrorLabel(){
            if(!errorLabel.getStyleClass().contains("search-error")){
                errorLabel.getStyleClass().add("search-error");
            }
            errorLabel.textProperty().setValue("No " + errorTextItem + " matching " + searchText + " were found in the database.");
        }
    }

    public static class SearchPartTableField extends SearchTableField<Part> implements TableSearcher<Part>{
        protected ObservableList<Part> foundList = FXCollections.observableArrayList();

        public SearchPartTableField(Label errorLabel, TableView<Part> table) {
            super(errorLabel, table);
            this.errorTextItem = "parts";
            this.table = table;
        }

        @Override
        public boolean setTableItems(){
            ObservableList<Part> rawFoundList = FXCollections.observableArrayList();

            int searchId;
            foundList.clear();
            rawFoundList.addAll(Inventory.lookupPart(searchText));

            try{
                searchId = Integer.parseInt(searchText);
                Part part = Inventory.lookupPart(searchId);
                if(part!=null){
                    rawFoundList.add(part);
                }
            } catch (Exception e){
                System.out.print(e.getMessage());
            }

            rawFoundList.stream().distinct().forEach(part -> foundList.add(part));

            table.setItems(foundList);
            return !foundList.isEmpty();
        }

        @Override
        public void setDefaultView(){
            table.setItems(Inventory.getAllParts());
        }
    }

    public static class SearchProductTableField extends SearchTableField<Product> implements TableSearcher<Product>{
        protected ObservableList<Product> foundList = FXCollections.observableArrayList();

        public SearchProductTableField(Label errorLabel, TableView<Product> table) {
            super(errorLabel, table);
            this.errorTextItem = "products";
            this.table = table;
        }

        @Override
        public boolean setTableItems(){
            ObservableList<Product> rawFoundList = FXCollections.observableArrayList();

            int searchId;
            foundList.clear();
            rawFoundList.addAll(Inventory.lookupProduct(searchText));

            try{
                searchId = Integer.parseInt(searchText);
                Product product = Inventory.lookupProduct(searchId);
                if(product!=null){
                    rawFoundList.add(product);
                }
            } catch (Exception e){
                System.out.print(e.getMessage());
            }

            rawFoundList.stream().distinct().forEach(product -> foundList.add(product));

            table.setItems(foundList);
            return !foundList.isEmpty();
        }

        @Override
        public void setDefaultView(){
            table.setItems(Inventory.getAllProducts());
        }
    }

    public static class SearchProductPartTableField extends SearchPartTableField {
        private final ObservableList<Part> partsList;
        public SearchProductPartTableField(Label errorLabel, TableView<Part> table, ObservableList<Part> watchList) {
            super(errorLabel, table);
            this.partsList = watchList;
        }

        @Override
        public boolean setTableItems(){
            ObservableList<Part> rawList = FXCollections.observableArrayList();

            int searchId;
            foundList.clear();

            for(Part part: partsList){
                if(part.getName().startsWith(searchText)){
                    rawList.add(part);
                }
            }

            try{
                searchId = Integer.parseInt(searchText);
                Part part = Inventory.lookupPart(searchId);
                if(part!=null){
                    for(Part x: partsList){
                        if(x.getId() == searchId){
                            rawList.add(x);
                        }
                    }
                }
            } catch (Exception e){
                System.out.print(e.getMessage());
            }

            rawList.stream().distinct().forEach(part -> foundList.add(part));

            table.setItems(foundList);
            return !foundList.isEmpty();
        }

        @Override
        public void setDefaultView(){
            table.setItems(partsList);
        }
    }
}
