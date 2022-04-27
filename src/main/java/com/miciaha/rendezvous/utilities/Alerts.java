package com.miciaha.rendezvous.utilities;

import com.miciaha.rendezvous.interfaces.Command;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * The Alerts class makes it easier to create custom JavaFX alerts without the need to rewrite code.
 *
 */
public class Alerts {
    /**
     * The type Custom alert.
     */
    public static class CustomAlert{

        /**
         * The type Base alert.
         */
        public static class BaseAlert{
            /**
             * The Alert type.
             */
            protected Alert.AlertType alertType = Alert.AlertType.ERROR;
            /**
             * The Custom alert.
             */
            protected Alert customAlert = new Alert(alertType);
            /**
             * The Title.
             */
            protected String title = "";
            /**
             * The Header text.
             */
            protected String headerText = "";
            /**
             * The Content text.
             */
            protected String contentText = "";


            /**
             * Create alert.
             *
             * @param alertType the alert type
             */
            protected void createAlert(Alert.AlertType alertType){
                customAlert.setAlertType(alertType);
                customAlert.setTitle(title);
                customAlert.setHeaderText(headerText);
                customAlert.setContentText(contentText);
            }

        }

        /**
         * The type Success alert.
         */
        public static class SuccessAlert extends BaseAlert{
            /**
             * Instantiates a new Success alert.
             *
             * @param commandName the command name
             */
            public SuccessAlert(String commandName){
                title = "Success!";
                headerText = "Operation complete!";
                contentText = commandName;

                createAlert(Alert.AlertType.INFORMATION);
                customAlert.showAndWait();
            }
        }

        /**
         * The type Warning alert.
         */
        public static class WarningAlert extends BaseAlert{
            /**
             * Instantiates a new Warning alert.
             * @RUNTIME_ERROR NULLPOINTEREXCEPTION - This error was encountered a few times throughout the project.
             * In this instance, the Alert was not created before the extended classes tried to use the BaseAlert.
             * To fix the issue, I instantiated the Alert in BaseAlert upon declaration.
             *
             * @param customHeader  the custom header
             * @param customContent the custom content
             */
            public WarningAlert(String customHeader, String customContent){
                title = "Warning";
                headerText = customHeader;
                contentText = customContent;

                createAlert(Alert.AlertType.WARNING);
                customAlert.showAndWait();
            }
        }

        /**
         * The type Confirmation alert.
         *
         * @param <T> the type parameter
         */
        public static class ConfirmationAlert<T> extends BaseAlert{
            /**
             * The Command.
             */
            protected Command command;

            /**
             * Instantiates a new Confirmation alert.
             *
             * @param command     the command
             * @param commandName the command name
             */
            public ConfirmationAlert(Command command, String commandName){
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

        /**
         * The type Error alert.
         */
        public static class ErrorAlert extends BaseAlert{
            /**
             * Instantiates a new Error alert.
             *
             * @param action the action
             * @param object the object
             * @param reason the reason
             */
            public ErrorAlert(String action, String object, String reason){
                title = "Error!";
                headerText = "Unable to " + action + " " + object;
                contentText = reason;

                createAlert(Alert.AlertType.ERROR);
                customAlert.showAndWait();
            }
        }

        /**
         * The type Command error alert.
         */
        public static class CommandErrorAlert extends BaseAlert{
            /**
             * Instantiates a new Command error alert.
             *
             * @param command the command
             * @param reason  the reason
             */
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
