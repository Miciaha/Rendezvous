package com.miciaha.rendezvous.interfaces;

import javafx.beans.value.ChangeListener;

/**
 * The interface Table searcher identifies fields that are used to search through tables.
 *
 * @param <T> the type parameter
 */
public interface TableSearcher<T> extends ChangeListener<String> {
    /**
     * Sets table items.
     *
     * @return the table items
     */
    boolean setTableItems();

    /**
     * Sets default view.
     */
    void setDefaultView();
}
