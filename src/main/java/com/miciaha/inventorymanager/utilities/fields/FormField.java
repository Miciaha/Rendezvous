package com.miciaha.inventorymanager.utilities.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormField {
    public TextField textField;
    public Label textFieldErrorLabel;
    public FieldType fieldType;

    public FormField(TextField field, Label label, FieldType type) {
        textField = field;
        textFieldErrorLabel = label;
        fieldType = type;
    }

}

