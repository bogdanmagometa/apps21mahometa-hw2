package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

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

    @Test
    public void testAddAll() {
        linkedList = (ImmutableLinkedList) linkedList.addAll(new Object[] {1, 2, 3, 4});
        assertArrayEquals(new Object[] {1, 2, 3, 4, 5, 1, 2, 3, 4}, linkedList.toArray());
        linkedList = (ImmutableLinkedList) linkedList.addAll(0, new Object[] {-1, -12});
        assertArrayEquals(new Object[] {-1, -12, 1, 2, 3, 4, 5, 1, 2, 3, 4},
                linkedList.toArray());
        linkedList = (ImmutableLinkedList) linkedList.addAll(2, new Object[] {-1, -12});
        assertArrayEquals(new Object[] {-1, -12, -1, -12, 1, 2, 3, 4, 5, 1, 2, 3, 4},
                linkedList.toArray());

    }

    @Test
    public void testAdd() {
        linkedList = (ImmutableLinkedList) linkedList.add(0, -123);
        assertEquals(-123, linkedList.get(0));
        linkedList = (ImmutableLinkedList) linkedList.add(1, -123);
        assertEquals(-123, linkedList.get(1));
        linkedList = (ImmutableLinkedList) linkedList.addLast(-123);
        assertEquals(-123, linkedList.get(linkedList.size()-1));
        linkedList = (ImmutableLinkedList) linkedList.addFirst(-123);
        assertEquals(-123, linkedList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionGet0() {
        linkedList = new ImmutableLinkedList();
        linkedList.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void testExceptionIndexOf() {
        linkedList = new ImmutableLinkedList();
        linkedList.indexOf(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionAdd() {
        linkedList = new ImmutableLinkedList();
        linkedList.add(1, 12);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionAddAll() {
        linkedList = new ImmutableLinkedList();
        linkedList.addAll(1, new Object[] {1, 2, 3});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionGet() {
        linkedList = new ImmutableLinkedList();
        linkedList.get(0);
    }

}