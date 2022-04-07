package com.miciaha.inventorymanager.utilities.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The type Form field. This class type was created to better manage fields within the form.
 * A field has a few objects that need to be tracked to ensure proper validation.
 */
public class FormField {
    /**
     * The Text field.
     */
    public TextField textField;
    /**
     * The Text field error label.
     */
    public Label textFieldErrorLabel;
    /**
     * The Field type.
     */
    public FieldType fieldType;

    /**
     * Instantiates a new Form field.
     *
     * @param field the TextField
     * @param label the label
     * @param type  the FieldType
     */
    public FormField(TextField field, Label label, FieldType type) {
        textField = field;
        textFieldErrorLabel = label;
        fieldType = type;
    }

}

