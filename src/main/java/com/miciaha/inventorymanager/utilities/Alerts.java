package com.miciaha.inventorymanager.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerts {
    public static class CustomAlert{
        private static Alert customAlert;

        private static Alert createBaseAlert(){
            return new Alert(Alert.AlertType.NONE);
        }

        public static void createSuccessAlert() {
            customAlert = createBaseAlert();
            customAlert.setAlertType(Alert.AlertType.INFORMATION);
            customAlert.setTitle("Success!");
            customAlert.setHeaderText("Operation complete!");
            customAlert.setContentText("Item removed successfully");
            customAlert.showAndWait();
        }

        public static void createErrorAlert(String action) {
            customAlert = createBaseAlert();
            customAlert.getButtonTypes().add(ButtonType.OK);
            customAlert.setAlertType(Alert.AlertType.ERROR);
            customAlert.setTitle("Warning");
            customAlert.setHeaderText("ITEM NOT SELECTED");
            customAlert.setContentText("Please select an item to " + action);
            customAlert.showAndWait();
        }

        public static void createWarningAlert(String title, String headerText, String content) {
            customAlert = createBaseAlert();
            customAlert.setAlertType(Alert.AlertType.WARNING);
            customAlert.setTitle(title);
            customAlert.setHeaderText(headerText);
            customAlert.setContentText(content);
            customAlert.showAndWait();
        }
    }

}
