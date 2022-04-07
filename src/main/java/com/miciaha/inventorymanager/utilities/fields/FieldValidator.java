package com.miciaha.inventorymanager.utilities.fields;

import com.miciaha.inventorymanager.interfaces.FormFieldValidator;
import com.miciaha.inventorymanager.utilities.Alerts;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FieldValidator {

    public static class TextFieldValidator implements FormFieldValidator {
        protected TextField field;
        protected Label errorLabel;
        protected String text;
        protected String errorText;
        protected boolean isValid;

        public TextFieldValidator(TextField field, Label errorLabel) {
            this.field = field;
            this.errorLabel = errorLabel;
            this.errorText = "Please enter a value.";
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            this.text = t1;
            checkField();
        }

        public boolean checkField() {
            if (text.trim().isEmpty()) {
                setErrorValues();
                return false;
            } else {
                setValidValues();
                return true;
            }
        }

        protected void updateValid() {
            if (!isValid) {
                isValid = true;
                FieldTracker.Fields.addValidFields();
            }
        }

        protected void updateInvalid() {
            if (isValid) {
                isValid = false;
                FieldTracker.Fields.decreaseValidFields();
            }
        }

        protected void setErrorValues() {
            if(!field.getStyleClass().contains("invalid-field")) {
                field.getStyleClass().add("invalid-field");
            }
            field.getStyleClass().remove("valid-field");
            errorLabel.textProperty().setValue(errorText);
            errorLabel.visibleProperty().setValue(true);
            updateInvalid();
        }

        protected void setValidValues() {
            if(!field.getStyleClass().contains("valid-field")){
                field.getStyleClass().add("valid-field");
            }
            field.getStyleClass().remove("invalid-field");
            errorLabel.visibleProperty().setValue(false);
            updateValid();
        }
    }

    public static class IntegerFieldValidator extends TextFieldValidator implements FormFieldValidator {
        public IntegerFieldValidator(TextField field, Label errorLabel) {
            super(field, errorLabel);
            this.errorText = "Please enter a valid integer";
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            this.text = t1;
            if (checkField()) {
                if (checkIntField()) {
                    setValidValues();
                } else {
                    setErrorValues();
                }
            }
        }

        @Override
        public boolean checkField() {
            if (text.trim().isEmpty()) {
                setErrorValues();
                return false;
            } else {
                return true;
            }
        }

        private boolean checkIntField() {
            try {
                Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage());
                return false;
            }
            return true;
        }
    }

    public static class DoubleFieldValidator extends TextFieldValidator implements FormFieldValidator {
        public DoubleFieldValidator(TextField field, Label errorLabel) {
            super(field, errorLabel);
            this.errorText = "Please enter a valid number";
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            this.text = t1;
            if (checkField()) {
                if (checkDoubleField()) {
                    setValidValues();
                } else {
                    setErrorValues();
                }
            }
        }

        @Override
        public boolean checkField() {
            if (text.trim().isEmpty()) {
                setErrorValues();
                return false;
            } else {
                return true;
            }
        }

        private boolean checkDoubleField() {
            try {
                Double.parseDouble(text);
            } catch (Exception e) {
                System.out.print(e.getMessage());
                return false;
            }
            return true;
        }
    }

    public static class checkFieldLogic {
        public static boolean checkStock(int min, int max, int inventory) {
            if (inventory > max || min > inventory) {
                new Alerts.CustomAlert.WarningAlert(
                        "Fix inventory values",
                        "Please ensure inventory is between the minimum and maximum values.");
                return false;
            }
            return true;
        }
    }
}