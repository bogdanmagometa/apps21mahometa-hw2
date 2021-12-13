package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    ImmutableLinkedList linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new ImmutableLinkedList(new Object[] {1, 2, 3, 4, 5});
    }

    @Test
    public void get() {
        assertEquals(1, linkedList.get(0));
        assertEquals(2, linkedList.get(1));
        assertEquals(3, linkedList.get(2));
        assertEquals(4, linkedList.get(3));
    }

    @Test
    public void remove() {
        linkedList = (ImmutableLinkedList) linkedList.remove(0);
        assertEquals(2, linkedList.get(0));
        assertEquals(3, linkedList.get(1));
        assertEquals(4, linkedList.get(2));
        assertEquals(5, linkedList.get(3));
    }

    @Test
    public void set() {
        linkedList = (ImmutableLinkedList) linkedList.set(linkedList.size()-1, 10);
        assertEquals(10, linkedList.get(linkedList.size()-1));
    }

    @Test
    public void indexOf() {
        assertEquals(0, linkedList.indexOf(1));
        assertEquals(1, linkedList.indexOf(2));
        assertEquals(2, linkedList.indexOf(3));
        assertEquals(3, linkedList.indexOf(4));
        assertEquals(4, linkedList.indexOf(5));
    }

    @Test
    public void size() {
        assertEquals(5, linkedList.size());
    }

    @Test
    public void clear() {
        linkedList = (ImmutableLinkedList) linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    public void isEmpty() {
        assertFalse(linkedList.isEmpty());
        linkedList = (ImmutableLinkedList) linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void toArray() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], linkedList.get(i));
        }
    }

    @Test
    public void addFirst() {
        linkedList = linkedList.addFirst(-123);
        assertEquals(-123, linkedList.getFirst());
    }

    @Test
    public void addLast() {
        linkedList.addLast(123);
        assertNotEquals(123, linkedList.getLast());
        linkedList = linkedList.addLast(123);
        assertEquals(123, linkedList.getLast());
    }

    @Test
    public void getHead() {
        assertTrue(linkedList.getHead() instanceof Node);
    }

    @Test
    public void getTail() {
        assertTrue(linkedList.getTail() instanceof Node);
    }

    @Test
    public void getFirst() {
        assertEquals(1, linkedList.getFirst());
    }

    @Test
    public void getLast() {
        assertEquals(5, linkedList.getLast());
    }

    @Test
    public void removeFirst() {
        linkedList.removeFirst();
        assertEquals(1, linkedList.getFirst());
        linkedList = linkedList.removeFirst();
        assertEquals(2, linkedList.getFirst());
    }

    @Test
    public void removeLast() {
        linkedList.removeLast();
        assertEquals(5, linkedList.getLast());
        linkedList = linkedList.removeLast();
        assertEquals(4, linkedList.getLast());
    }

    @Test
    public void testClone() {
        ImmutableLinkedList clone = new ImmutableLinkedList(linkedList);
        assertArrayEquals(linkedList.toArray(), clone.toArray());
    }
}