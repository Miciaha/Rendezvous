package com.miciaha.inventorymanager.interfaces;

/**
 * The interface Command identifies classes that execute simple commands.
 *
 * @param <T> the type parameter
 */
public interface Command<T> {
    /**
     * Execute boolean.
     *
     * @return the boolean
     */
    boolean execute();
}
