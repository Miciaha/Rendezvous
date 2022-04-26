package com.miciaha.rendezvous.interfaces;

public interface DbManager<T> {
    boolean Create(T type);
    boolean Update(T type);
    boolean Delete(T type);
}
