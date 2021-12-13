package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList data = new ImmutableLinkedList();

    public Object peek() {
        Object last = data.getLast();
        return last;
    }

    public Object dequeue() {
        Object last = peek();
        data = data.removeLast();
        return last;
    }

    public void enqueue(Object e) {
        data = data.addFirst(e);
    }
}
