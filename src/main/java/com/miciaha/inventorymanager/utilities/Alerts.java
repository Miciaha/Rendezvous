package com.miciaha.inventorymanager.utilities;

import com.miciaha.inventorymanager.interfaces.Command;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Alerts {
    public static class CustomAlert{

        public static class BaseAlert{
            protected Alert.AlertType alertType = Alert.AlertType.ERROR;
            protected Alert customAlert = new Alert(alertType);
            protected String title = "";
            protected String headerText = "";
            protected String contentText = "";

            public BaseAlert(){}

            protected void createAlert(Alert.AlertType alertType){
                customAlert.setAlertType(alertType);
                customAlert.setTitle(title);
                customAlert.setHeaderText(headerText);
                customAlert.setContentText(contentText);
            }

        }

        public static class SuccessAlert extends BaseAlert{
            public SuccessAlert(String commandName){
                title = "Success!";
                headerText = "Operation complete!";
                contentText = "Command " + commandName + " completed successfully.";

                createAlert(Alert.AlertType.INFORMATION);
                customAlert.showAndWait();
            }
        }

        public static class WarningAlert extends BaseAlert{
            public WarningAlert(String customHeader, String customContent){
                title = "Warning";
                headerText = customHeader;
                contentText = customContent;

                createAlert(Alert.AlertType.WARNING);
                customAlert.showAndWait();
            }
        }

        public static class ConfirmationAlert<T> extends BaseAlert{
            protected Command<T> command;

            public ConfirmationAlert(Command<T> command, String commandName){
                this.command = command;
                title = "Confirmation";
                headerText = "Are you sure you would like to " + commandName;
                createAlert(Alert.AlertType.INFORMATION);

                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

                customAlert.getButtonTypes().clear();
                customAlert.getButtonTypes().add(okButton);
                customAlert.getButtonTypes().add(noButton);
                customAlert.showAndWait().ifPresent(response -> {
                    if (response == okButton) {
                        command.execute();
                    }
                });
            }
        }

        public static class ErrorAlert extends BaseAlert{
            public ErrorAlert(String action, String object, String reason){
                title = "Error!";
                headerText = "Unable to " + action + " " + object;
                contentText = reason;

                createAlert(Alert.AlertType.ERROR);
                customAlert.showAndWait();
            }
        }

        public static class CommandErrorAlert extends BaseAlert{
            public CommandErrorAlert(String command, String reason){
                title = "Error!";
                headerText = "Unable to " + command;
                contentText = reason;

                createAlert(Alert.AlertType.ERROR);
                customAlert.showAndWait();
            }
        }
    }
}
