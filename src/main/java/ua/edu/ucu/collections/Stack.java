package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList data = new ImmutableLinkedList();

    public void push(Object e) {
        data = data.addFirst(e);
    }

    public Object pop() {
        Object first = peek();
        data = data.removeFirst();
        return first;
    }

    public Object peek() {
        Object first = data.getFirst();
        return first;
    }
}
