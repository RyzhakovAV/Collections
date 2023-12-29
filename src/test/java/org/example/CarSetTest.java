package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarSetTest {
    private InterfaceCarSet carSet;

    @Before
    public void setUp() throws Exception {
        //init
        for (int i = 0; i < 100; i++) {
            carSet.add(new Car("Brand" + i, i));
        }
    }

    @Test
    public void whenAdded100AndNew3SimilarThenSizeBy101() {
        assertEquals(100, carSet.size());
        assertTrue(carSet.add(new Car("BMW", 50)));
        assertFalse(carSet.add(new Car("BMW", 50)));
        assertFalse(carSet.add(new Car("BMW", 50)));
        assertEquals(101, carSet.size());
    }

    public void whenDeletedThenSize99() {
        assertTrue(carSet.remove(new Car("Brand50", 50)));
        assertEquals(99, carSet.size());
        assertFalse(carSet.remove(new Car("Brand50", 50)));
        assertEquals(99, carSet.size());
    }

    public void whenClearThenSize0() {
        carSet.clear();
        assertEquals(0, carSet.size());
    }
}