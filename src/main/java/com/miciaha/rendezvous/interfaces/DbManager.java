package com.miciaha.rendezvous.interfaces;

/**
 * The interface Db manager.
 *
 * @param <T> the type parameter
 */
public interface DbManager<T> {
    /**
     * Create boolean.
     *
     * @param type the type
     * @return the boolean
     */
    boolean Create(T type);

    /**
     * Update boolean.
     *
     * @param type the type
     * @return the boolean
     */
    boolean Update(T type);

    /**
     * Delete boolean.
     *
     * @param type the type
     * @return the boolean
     */
    boolean Delete(T type);
}
