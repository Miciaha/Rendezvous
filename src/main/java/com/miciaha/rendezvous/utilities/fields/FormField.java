package com.miciaha.rendezvous.utilities.fields;

import javafx.scene.control.Label;

/**
 * The type Form field. This class type was created to better manage fields within the form.
 * A field has a few objects that need to be tracked to ensure that they can be validated properly.
 *
 * @param <T> the type parameter
 */
public class FormField<T> {

    /**
     * The Field.
     */
    public T field;

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
     * @param fieldObject the field object
     * @param label       the label
     * @param type        the type
     */
    public FormField(T fieldObject, Label label, FieldType type) {
        field = fieldObject;
        textFieldErrorLabel = label;
        fieldType = type;
    }

}

