package com.miciaha.rendezvous.utilities.fields;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * The type Field tracker takes an array of FormFields to track.
 * FormFields are linked to a button in the form and validated based on the FieldType value.
 * The linked button is disabled until all the linked fields are valid.
 */
public class FieldTracker {
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
     * The type Text fields.
     */
    public static class TextFields {
        /**
         * Add.
         *
         * @param formField the form field
         */
        public static void add(FormField<TextField> formField) {
            fieldCount += 1;
            formField.field.textProperty().addListener(
                    new FieldValidator.TextFieldValidator(formField.field, formField.textFieldErrorLabel)
            );
        }
    }

    /**
     * The type Combo boxes.
     */
    public static class ComboBoxes {
        /**
         * Add.
         *
         * @param comboField the combo field
         */
        public static void add(FormField<ComboBox<String>> comboField) {
            fieldCount += 1;
            comboField.field.setOnAction(
                    new FieldValidator.ComboFieldValidator(comboField)
            );
        }
    }

    /**
     * The type Date time fields.
     */
    public static class DateTimeFields {
        /**
         * Add.
         *
         * @param dateField the date field
         */
        public static void add(FormField<DatePicker> dateField) {
            fieldCount += 1;

            dateField.field.setOnAction(
                    new FieldValidator.DateFieldValidator(dateField)
            );
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
        linkedButton = new Button();
        fieldCount = 0;
        validFields = 0;
    }

    /**
     * Add valid fields found in form.
     */
    public static void increaseValidFields() {
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
