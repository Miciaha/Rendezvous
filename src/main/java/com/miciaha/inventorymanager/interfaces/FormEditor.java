package com.miciaha.inventorymanager.interfaces;

/**
 * The interface Form editor identifies classes that need to populate fields with a specific type of data.
 *
 * @param <T> the type parameter
 */
public interface FormEditor<T>{
    /**
     * Init data.
     *
     * @param type the type
     */
    void initData(T type);
}
