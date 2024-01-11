package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarMapTest {
    private CarMap map;

    @Before
    public void setUp() throws Exception {
        map = new CarHashMap();
    }

    @Test
    public void whenPut100ElementsThenSize100() {
        for (int i = 0; i < 100; i++) {
            CarOwner user = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            map.put(user, car);
        }
        assertEquals(100, map.size());
    }

    @Test
    public void whenPut100ElementsWith10DifferentKeysThenSize10() {
        for (int i = 0; i < 100; i++) {
            int index = i % 10;
            CarOwner user = new CarOwner(index % 10, "Name" + index % 10, "LastName" + index % 10);
            Car car = new Car("Brand" + index, index);
            map.put(user, car);
        }
        assertEquals(10, map.size());
    }

    @Test
    public void removeReturnTrueOnlyOnce() {
        for (int i = 0; i < 10; i++) {
            CarOwner user = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            map.put(user, car);
        }
        assertEquals(10, map.size());
        CarOwner elementDelete = new CarOwner(7, "Name7", "LastName7");
        assertTrue(map.remove(elementDelete));
        assertEquals(9, map.size());
        assertFalse(map.remove(elementDelete));
    }

    @Test
    public void countOfKeysMustBeEqualsToCountOfValues() {
        for (int i = 0; i < 100; i++) {
            CarOwner user = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            map.put(user, car);
        }
        assertEquals(100, map.size());
        assertEquals(100, map.keySet().size());
        assertEquals(100, map.values().size());
    }

    @Test
    public void methodGetMustReturnRightValue() {
        for (int i = 0; i < 100; i++) {
            CarOwner user = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            map.put(user, car);
        }
        CarOwner key = new CarOwner(50, "Name50", "LastName50");
        Car value = map.get(key);
        String expectedCarBrand = "Brand50";
        assertEquals(expectedCarBrand, value.getBrand() );
    }
}