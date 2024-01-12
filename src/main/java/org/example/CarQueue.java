package org.example;

import java.util.Collection;

public interface CarQueue extends CarCollection {
    boolean add(Car car);
    Car peek();
    Car poll();
}
