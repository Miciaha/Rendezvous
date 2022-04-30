package com.miciaha.rendezvous.utilities.fields;

import com.miciaha.rendezvous.interfaces.FormFieldValidator;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;


/**
 * The type Field validator validates field according to their field type.
 */
public class FieldValidator {


    /**
     * The type Combo field validator.
     */
    public static class ComboFieldValidator implements EventHandler<ActionEvent> {
        private final ComboBox<String> comboField;
        private final Label errorText;
        private boolean isValid = false;

        /**
         * Instantiates a new Combo field validator.
         *
         * @param comboBoxField the combo box field
         */
        public ComboFieldValidator(FormField<ComboBox<String>> comboBoxField) {
            comboField = comboBoxField.field;
            errorText = comboBoxField.textFieldErrorLabel;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            if (comboField.getValue().isEmpty()) {
                errorText.textProperty().setValue("Please enter a value.");

                if (isValid) {
                    isValid = false;
                    FieldTracker.decreaseValidFields();
                }
            } else {
                if (!isValid) {
                    errorText.textProperty().setValue("");
                    isValid = true;
                    FieldTracker.increaseValidFields();
                }
            }
        }
    }

    /**
     * The type Date field validator.
     */
    public static class DateFieldValidator implements EventHandler<ActionEvent> {
        private final DatePicker dateField;
        private final Label errorText;
        private boolean isValid = false;

        /**
         * Instantiates a new Date field validator.
         *
         * @param datePickerField the date picker field
         */
        public DateFieldValidator(FormField<DatePicker> datePickerField) {
            dateField = datePickerField.field;
            errorText = datePickerField.textFieldErrorLabel;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            if (Objects.isNull(dateField.getValue())) {
                errorText.textProperty().setValue("Please enter a date.");

                if (isValid) {
                    isValid = false;
                    FieldTracker.decreaseValidFields();
                }
            } else {
                if (!isValid) {
                    errorText.textProperty().setValue("");
                    isValid = true;
                    FieldTracker.increaseValidFields();
                }
            }
        }
    }

    /**
     * The type Text field validator.
     */
    public static class TextFieldValidator implements FormFieldValidator {
        /**
         * The Field.
         */
        protected final TextField field;
        /**
         * The Error label.
         */
        protected final Label errorLabel;
        /**
         * The Text.
         */
        protected String text;
        /**
         * The Error text.
         */
        protected final String errorText;
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

        public void checkField() {
            if (text.trim().isEmpty()) {
                setErrorValues();
            } else {
                setValidValues();
            }
        }

        /**
         * Update valid.
         */
        protected void updateValid() {
            if (!isValid) {
                isValid = true;
                FieldTracker.increaseValidFields();
            }
        }

        /**
         * Update invalid.
         */
        protected void updateInvalid() {
            if (isValid) {
                isValid = false;
                FieldTracker.decreaseValidFields();
            }
        }

        /**
         * Sets error values.
         */
        protected void setErrorValues() {
            if (!field.getStyleClass().contains("invalid-field")) {
                field.getStyleClass().add("invalid-field");
            }
            field.getStyleClass().remove("valid-field");
            LocaleHandler.setText(errorLabel.textProperty(), "error_text");
            errorLabel.visibleProperty().setValue(true);
            updateInvalid();
        }

        /**
         * Sets valid values.
         */
        protected void setValidValues() {
            if (!field.getStyleClass().contains("valid-field")) {
                field.getStyleClass().add("valid-field");
            }
            field.getStyleClass().remove("invalid-field");
            errorLabel.visibleProperty().setValue(false);
            updateValid();
        }
    }
}