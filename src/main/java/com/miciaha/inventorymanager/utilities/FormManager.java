package com.miciaha.inventorymanager.utilities;

import com.miciaha.inventorymanager.InventoryApplication;
import com.miciaha.inventorymanager.interfaces.FormEditor;
import com.miciaha.inventorymanager.interfaces.InventoryItem;
import com.miciaha.inventorymanager.inventoryitems.Inventory;
import com.miciaha.inventorymanager.inventoryitems.Product;
import com.miciaha.inventorymanager.inventoryitems.parts.Part;
import com.miciaha.inventorymanager.utilities.fields.FieldTracker;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.miciaha.inventorymanager.utilities.Alerts.CustomAlert.createSuccessAlert;

public class FormManager {

    public static class Form {
        protected Button callBtn;
        protected String winTitle;
        protected String stylesheet;
        protected double winSizeX;
        protected double winSizeY;
        protected FXMLLoader loadedForm;

        public Form(Button callBtn, String viewFile, String winTitle, String stylesheet, double winSizeX, double winSizeY) {
            this.callBtn = callBtn;
            this.winTitle = winTitle;
            this.stylesheet = stylesheet;
            this.winSizeX = winSizeX;
            this.winSizeY = winSizeY;
            this.loadedForm = new FXMLLoader(InventoryApplication.class.getResource(viewFile));
        }

        public void openForm() {
            Scene primaryStage = callBtn.getScene();
            try {
                Scene scene = new Scene(loadedForm.load(), winSizeX, winSizeY);
                scene.getStylesheets().add(String.valueOf(InventoryApplication.class.getResource(stylesheet)));

                Stage form = new Stage();
                form.setTitle(winTitle);
                form.initModality(Modality.WINDOW_MODAL);
                form.initOwner(primaryStage.getWindow());
                form.setScene(scene);
                form.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class EditForm<T> extends Form {
        private final T formData;

        public EditForm(Button callBtn, String viewFile, String winTitle, String stylesheet, double winSizeX, double winSizeY, T formData) {
            super(callBtn, viewFile, winTitle, stylesheet, winSizeX, winSizeY);
            this.loadedForm = new FXMLLoader(InventoryApplication.class.getResource(viewFile));
            this.formData = formData;
        }

        public void openEditForm() {
            openForm();
            FormEditor<T> controller = loadedForm.getController();
            controller.initData(formData);
        }
    }

    public static void closeForm(Button button) {
        FieldTracker.Fields.clear();
        Stage btnStage = (Stage) button.getScene().getWindow();
        btnStage.close();
    }


    public static void deleteInventoryItem(InventoryItem item) {
        if (item == null) {
             Alerts.CustomAlert.createErrorAlert("delete");
        } else {
            String itemName = item.getClass().getSuperclass().getSimpleName();
            if(itemName.equals("Object")){
                itemName = item.getClass().getSimpleName();
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Deleted " + itemName + "s cannot be restored.");
            alert.setContentText("Are you sure you wish to delete this " + itemName + "?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    deleteItem(item);
                }
            });
        }
    }

    public static void removeProductPart(Part part, ObservableList<Part> partList) {
        if (part == null) {
            Alerts.CustomAlert.createErrorAlert("delete");
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Removal Confirmation");
            alert.setHeaderText("Remove part from product?");
            alert.setContentText("Are you sure you wish to remove this part from the product?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    partList.remove(part);
                }
            });
        }
    }

    private static void deleteItem(InventoryItem item) {
        if (item instanceof Product) {
            if (Inventory.deleteProduct((Product) item)) {
                createSuccessAlert();
            }
        } else {
            if (Inventory.deletePart((Part) item)) {
                createSuccessAlert();
            }
        }
    }
}
