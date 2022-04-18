package com.miciaha.rendezvous.utilities.fields;

import com.miciaha.rendezvous.interfaces.FormFieldValidator;
import com.miciaha.rendezvous.utilities.Alerts;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * The type Field validator validates field according to their field type.
 */
public class FieldValidator {

    /**
     * The type Text field validator.
     */
    public static class TextFieldValidator implements FormFieldValidator {
        /**
         * The Field.
         */
        protected TextField field;
        /**
         * The Error label.
         */
        protected Label errorLabel;
        /**
         * The Text.
         */
        protected String text;
        /**
         * The Error text.
         */
        protected String errorText;
        /**
         * The Is valid.
         */
        protected boolean isValid;

        /**
         * Instantiates a new Text field validator.
         *
         * @param field      the field
         * @param errorLabel the error label
         */
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

        /**
         * Update valid.
         */
        protected void updateValid() {
            if (!isValid) {
                isValid = true;
                FieldTracker.Fields.addValidFields();
            }
        }

        /**
         * Update invalid.
         */
        protected void updateInvalid() {
            if (isValid) {
                isValid = false;
                FieldTracker.Fields.decreaseValidFields();
            }
        }

        /**
         * Sets error values.
         */
        protected void setErrorValues() {
            if(!field.getStyleClass().contains("invalid-field")) {
                field.getStyleClass().add("invalid-field");
            }
            field.getStyleClass().remove("valid-field");
            errorLabel.textProperty().setValue(errorText);
            errorLabel.visibleProperty().setValue(true);
            updateInvalid();
        }

        /**
         * Sets valid values.
         */
        protected void setValidValues() {
            if(!field.getStyleClass().contains("valid-field")){
                field.getStyleClass().add("valid-field");
            }
            field.getStyleClass().remove("invalid-field");
            errorLabel.visibleProperty().setValue(false);
            updateValid();
        }
    }

    /**
     * The type Integer field validator.
     */
    public static class IntegerFieldValidator extends TextFieldValidator implements FormFieldValidator {
        /**
         * Instantiates a new Integer field validator.
         *
         * @param field      the field
         * @param errorLabel the error label
         */
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

    /**
     * The type Double field validator.
     */
    public static class DoubleFieldValidator extends TextFieldValidator implements FormFieldValidator {
        /**
         * Instantiates a new Double field validator.
         *
         * @param field      the field
         * @param errorLabel the error label
         */
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

    /**
     * The type Check field logic.
     */
    public static class checkFieldLogic {
        /**
         * Check stock boolean.
         *
         * @param min       the min
         * @param max       the max
         * @param inventory the inventory
         * @return the boolean
         */
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