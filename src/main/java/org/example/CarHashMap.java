package org.example;

import java.util.*;

public class CarHashMap implements CarMap {
    private static final int INITIAL_CAPACITY = 16;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        boolean put = put(key, value, array);
        if (put) {
            size++;
        }
    }

    private boolean put(CarOwner key, Car value, Entry[] dst) {
        int position = getElementPosition(key, dst.length);
        Entry existedElement = dst[position];
        if (existedElement == null) {
            Entry entry = new Entry(key, value, null);
            dst[position] = entry;
            return true;
        } else {
            while(true) {
                if (existedElement.key.equals(key)) {
                    existedElement.value = value;
                    return false;
                }
                if (existedElement.next == null) {
                    existedElement.next = new Entry(key, value, null);
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existElement = array[position];
        while (existElement != null) {
            if (existElement.key.equals(key)) {
                return existElement.value;
            }
            existElement = existElement.next;
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> result = new HashSet<>();
        for (Entry element : array) {
            Entry existElement = element;
            while (existElement != null) {
                result.add(existElement.key);
                existElement = existElement.next;
            }
        }
        return result;
    }

    @Override
    public List<Car> values() {
        List<Car> result = new ArrayList<>();
        for (Entry element : array) {
            Entry existElement = element;
            while (existElement != null) {
                result.add(existElement.value);
                existElement = existElement.next;
            }
        }
        return result;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existElement = array[position];
        if (existElement != null && existElement.key.equals(key)) {
            array[position] = existElement.next;
            size--;
            return true;
        } else {
            while (existElement != null) {
                Entry nextElement = existElement.next;
                if (nextElement == null) {
                    return false;
                }
                if (nextElement.key.equals(key)) {
                    existElement.next = nextElement.next;
                    size--;
                    return true;
                }
                existElement = existElement.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry element : array) {
            Entry existElement = element;
            while (existElement != null) {
                put(existElement.key, existElement.value, newArray);
                existElement = existElement.next;
            }
        }
    }

    private int getElementPosition(CarOwner carOwner, int arrayLength) {
        return Math.abs(carOwner.hashCode() % arrayLength);
    }

    private static class Entry {

        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
