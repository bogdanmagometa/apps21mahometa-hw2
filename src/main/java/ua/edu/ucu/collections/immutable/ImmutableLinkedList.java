package ua.edu.ucu.collections.immutable;

import java.util.NoSuchElementException;

public final class ImmutableLinkedList implements ImmutableList {
    private Node first;
    private Node last;
    private int len;

    public ImmutableLinkedList(Object[] elements) {
        int size = 0;
        if (elements.length == 0) {
            first = null;
            last = null;
        } else {
            Node probe = new Node(elements[0], null, null);
            size++;
            first = probe;
            for (int i = 1; i < elements.length; i++) {
                Object element = elements[i];
                probe = new Node(element, probe, null);
                probe.getPrevious().setNext(probe);
                size++;
            }
            last = probe;

        }
        this.len = size;
    }

    public ImmutableLinkedList() {
        first = null;
        last = null;
        len = 0;
    }

    @Override
    public ImmutableList add(Object e) {
        return this.add(this.size(), e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        ImmutableLinkedList newLL = new ImmutableLinkedList(this);
        newLL.mutAdd(index, e);
        return newLL;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(this.size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableLinkedList newLL = new ImmutableLinkedList(this);
        newLL.mutAddAll(index, c);
        return newLL;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds. Size is "
                    + this.size());
        }
        return getNode(index).getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        ImmutableLinkedList newll = new ImmutableLinkedList(this);
        newll.mutRemove(index);
        return newll;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        ImmutableLinkedList newll = new ImmutableLinkedList(this);
        Node node = newll.getNode(index);
        node.setValue(e);
        return newll;
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        Node probe = first;
        while (probe != null && !probe.getValue().equals(e)) {
            i++;
            probe = probe.getNext();
        }
        if (probe == null) {
            throw new NoSuchElementException("No " + e
                    + " in the linked list.");
        }
        return i;
    }

    @Override
    public int size() {
        return this.len;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[len];
        Node probe = first;
        int i = 0;
        while (probe != null) {
            arr[i] = probe.getValue();
            i++;
            probe = probe.getNext();
        }
        return arr;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) this.add(e);
    }

    public Node getHead() {
        return this.first;
    }

    public Node getTail() {
        return this.last;
    }

    public Object getFirst() {
        return this.get(0);
    }

    public Object getLast() {
        return this.get(this.size() - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) this.remove(this.size() - 1);
    }

    private void mutAddFirst(Object e) {
        Node newFirst = new Node(e, null, first);
        if (first != null) {
            first.setPrevious(newFirst);
        } else {
            last = newFirst;
        }
        first = newFirst;
        len++;
    }

    private void mutAddLast(Object e) {
        Node newLast = new Node(e, last, null);
        if (last != null) {
            last.setNext(newLast);
        } else {
            first = newLast;
        }
        last = newLast;
        len++;
    }

    private void mutAdd(int index, Object e) {
        if (index == 0) {
            mutAddFirst(e);
        } else if (index == this.size()) {
            mutAddLast(e);
        } else if (index > 0 && index < this.size()) {
            Node probe = getNode(index);
            Node newNode = new Node(e, probe.getPrevious(), probe);
            probe.getPrevious().setNext(newNode);
            probe.setPrevious(newNode);
            this.len++;
        } else {
            throw new IndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds");
        }
    }

    private void mutRemove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds. Size is "
                    + this.size());
        }
        Node probe = getNode(index);
        Node prev = probe.getPrevious();
        Node next = probe.getNext();
        if (index == 0) {
            first = first.getNext();
        }
        if (index == this.len - 1) {
            last = last.getPrevious();
        }
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrevious(prev);
        }
        this.len--;
    }

    private void mutAddAll(int index, Object[] c) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds. Size is "
                    + this.size());
        }
        int i = index;
        for (Object obj: c) {
            mutAdd(i, obj);
            i++;
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds. Size is "
                    + this.size());
        }
        int i = 0;
        Node probe = first;
        while (i != index) {
            probe = probe.getNext();
            i++;
        }
        return probe;
    }

    /**
     * Copy constructor
     * */
    public ImmutableLinkedList(ImmutableLinkedList linkedList) {
        this();
        Node probe = linkedList.first;
        while (probe != null) {
            this.mutAddLast(probe.getValue());
            probe = probe.getNext();
        }
    }

}
