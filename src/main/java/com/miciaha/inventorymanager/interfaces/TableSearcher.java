package com.miciaha.inventorymanager.interfaces;

import javafx.beans.value.ChangeListener;

public interface TableSearcher<T> extends ChangeListener<String> {
    boolean setTableItems();
    void setDefaultView();
}
