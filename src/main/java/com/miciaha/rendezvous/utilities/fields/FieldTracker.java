package com.miciaha.rendezvous.utilities.fields;

import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * The type Field tracker takes an array of FormFields to track.
 * FormFields are linked to a button in the form and validated based on the FieldType value.
 * The linked button is disabled until all the linked fields are valid.
 *
 * FUTURE_ENHANCEMENT create a more robust solution identify that the form is to be edited.
 *
 */
public class FieldTracker {
    /**
     * The type Fields.
     */
    public static class Fields {
        /**
         * The constant trackedFormFields.
         */
        public static ArrayList<FormField> trackedFormFields = new ArrayList<>();
        /**
         * The constant linkedButton.
         */
        public static Button linkedButton = new Button();
        /**
         * The constant validFields.
         */
        public static int validFields = 0;
        /**
         * The constant fieldCount.
         */
        public static int fieldCount = 0;

        /**
         * Add FormField to the list of tracked fields.
         *
         * @param field the FormField
         */
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

        /**
         * Link to tracked fields to a button. The button will be disabled until all tracked fields are valid.
         *
         * @param button the button
         */
        public static void linkToButton(Button button) {
            linkedButton = button;
            button.disableProperty().setValue(true);
        }

        /**
         * Clear and reset field tracker.
         */
        public static void clear() {
            if (!trackedFormFields.isEmpty()) {
                trackedFormFields.clear();
                linkedButton = new Button();
                fieldCount = 0;
                validFields = 0;
            }
        }

        /**
         * Add valid fields found in form.
         */
        public static void addValidFields() {
            validFields = validFields + 1;
            checkFields();
        }

        /**
         * Decrease valid fields found in form.
         */
        public static void decreaseValidFields() {
            validFields = validFields - 1;
            checkFields();
        }

        private static void checkFields() {
            linkedButton.disableProperty().setValue(fieldCount != validFields);
        }

        /**
         * Edit fields method informs FieldTracker that the form has already been validated and is set for editing.
         */
        public static void editFields() {
            validFields = fieldCount;
        }
    }
}
