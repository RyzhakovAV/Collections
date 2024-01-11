package org.example;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CarTreeSet {
    public static void main(String[] args) {
        Set<Car> cars = new TreeSet<>(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });

        for (int i = 0; i < 100; i++) {
            cars.add(new Car("Brand" + i, i));
        }

        for (Car car : cars) {
            System.out.println(car);
        }
    }
}
