package com.miciaha.rendezvous.interfaces;

import javafx.beans.value.ChangeListener;

/**
 * The interface Form field validator identifies classes that validate JavaFX forms.
 */
public interface FormFieldValidator extends ChangeListener<String> {
    /**
     * Check field.
     */
    void checkField();
}
