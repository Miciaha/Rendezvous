package com.miciaha.inventorymanager.interfaces;

import javafx.beans.value.ChangeListener;

public interface FormFieldValidator extends ChangeListener<String> {
    boolean checkField();
}
