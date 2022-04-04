package com.miciaha.inventorymanager.utilities.fields;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class FieldTracker {
    public static class Fields {
        public static ArrayList<FormField> trackedFormFields = new ArrayList<>();
        public static Button linkedButton = new Button();
        public static int validFields = 0;
        public static int fieldCount = 0;

        public static void add(FormField field) {
            trackedFormFields.add(field);

            if (field.textField.visibleProperty().getValue()) {
                fieldCount += 1;
            }

            switch (field.fieldType) {
                case TEXT:
                    field.textField.textProperty().addListener(
                            new FieldValidator.TextFieldValidator(field.textField, field.textFieldErrorLabel)
                    );
                    break;
                case INTEGER:
                    field.textField.textProperty().addListener(
                            new FieldValidator.IntegerFieldValidator(field.textField, field.textFieldErrorLabel)
                    );
                    break;
                case DOUBLE:
                    field.textField.textProperty().addListener(
                            new FieldValidator.DoubleFieldValidator(field.textField, field.textFieldErrorLabel)
                    );
                    break;
            }
        }

        public static void linkToButton(Button button) {
            linkedButton = button;
            button.disableProperty().setValue(true);
        }

        public static void clear() {
            if (!trackedFormFields.isEmpty()) {
                trackedFormFields.clear();
                linkedButton = new Button();
                fieldCount = 0;
                validFields = 0;
            }
        }

        public static void addValidFields() {
            validFields = validFields + 1;
            checkFields();
        }

        public static void decreaseValidFields() {
            validFields = validFields - 1;
            checkFields();
        }

        private static void checkFields() {
            linkedButton.disableProperty().setValue(fieldCount != validFields);
        }

        public static void editFields() {
            validFields = fieldCount;
        }
    }
}
