package org.example;

public class CarLinkedList implements InterfaceCarList {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public boolean add(Car car) {
        if (size == 0) {
            first = new Node(null, car, null);
            last = first;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        if (0 > index || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(car);
        }
        Node nodeNext = getNode(index);
        Node nodePrevious = nodeNext.previous;
        Node nodeNew = new Node(nodePrevious, car, nodeNext);
        if (nodePrevious != null) {
            nodePrevious.next = nodeNew;
        } else {
            first = nodeNew;
        }
        nodeNext.previous = nodeNew;
        size++;
        return true;
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++){
            if(node.value.equals(car)) {
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node node = getNode(index);
        Node nodeNext = node.next;
        Node nodePrevious = node.previous;
        if (nodeNext != null) {
            nodeNext.previous = nodePrevious;
        } else {
            last = nodePrevious;
        }
        if (nodePrevious != null) {
            nodePrevious.next = nodeNext;
        } else {
            first = nodeNext;
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++){
            if(node.value.equals(car)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node {
        private Node previous;
        private Car value;
        private Node next;

        private Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
