package org.example;

public interface InterfaceCarSet extends CarCollection {
    boolean add(Car car);

    boolean remove(Car car);

    int size();

    void clear();
}
