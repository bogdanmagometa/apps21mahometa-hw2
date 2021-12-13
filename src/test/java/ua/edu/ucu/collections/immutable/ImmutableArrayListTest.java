package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    ImmutableArrayList arrayList;
    @Before
    public void setUp() throws Exception {
        Object[] arr = new Object[] {0, -1, -2 ,- 3};
        arrayList = new ImmutableArrayList(arr);
    }

    @Test
    public void add() {
        arrayList = (ImmutableArrayList) arrayList.add(1);
        arrayList = (ImmutableArrayList) arrayList.add(2);
        arrayList = (ImmutableArrayList) arrayList.add(3);
        arrayList = (ImmutableArrayList) arrayList.add(4);
        arrayList = (ImmutableArrayList) arrayList.add(5);
        assertEquals(5, arrayList.get(arrayList.size()-1));
        assertEquals(4, arrayList.get(arrayList.size()-2));
        assertEquals(3, arrayList.get(arrayList.size()-3));
        assertEquals(2, arrayList.get(arrayList.size()-4));
    }

    @Test
    public void testAdd() {
        int sizeBefore = arrayList.size();
        arrayList = (ImmutableArrayList) arrayList.add(123);
        arrayList = (ImmutableArrayList) arrayList.remove(arrayList.size() - 1);
        assertEquals(sizeBefore, arrayList.size());
    }

    @Test
    public void addAll() {
        arrayList = (ImmutableArrayList) arrayList.addAll(new Object[] {1, 1, 2, 3, 5, 8, 13});
        assertEquals(13, arrayList.get(arrayList.size() - 1));
        assertEquals(8, arrayList.get(arrayList.size() - 2));
        assertEquals(5, arrayList.get(arrayList.size() - 3));
        assertEquals(3, arrayList.get(arrayList.size() - 4));
        assertEquals(2, arrayList.get(arrayList.size() - 5));
        assertEquals(1, arrayList.get(arrayList.size() - 6));
        assertEquals(1, arrayList.get(arrayList.size() - 7));
    }

    @Test
    public void testAddAll() {
        int sizeBefore = arrayList.size();
        arrayList = (ImmutableArrayList) arrayList.addAll(new Object[] {});
        assertEquals(sizeBefore, arrayList.size());
    }

    @Test
    public void get() {
        assertEquals(0, arrayList.get(0));
        assertEquals(-1, arrayList.get(1));
        assertEquals(-2, arrayList.get(2));
        assertEquals(-3, arrayList.get(3));
    }

    @Test
    public void remove() {
        arrayList.remove(0);
        assertArrayEquals(new Object[] {0,-1,-2,-3}, arrayList.toArray());
        arrayList = (ImmutableArrayList) arrayList.remove(0);
        assertArrayEquals(new Object[] {-1,-2,-3}, arrayList.toArray());
    }

    @Test
    public void set() {
        arrayList.set(0, 123);
        assertArrayEquals(new Object[] {0,-1,-2,-3}, arrayList.toArray());
        arrayList = (ImmutableArrayList) arrayList.set(0, 123);
        assertArrayEquals(new Object[] {123,-1,-2,-3}, arrayList.toArray());
    }

    @Test
    public void indexOf() {
        assertEquals(0, arrayList.indexOf(0));
        assertEquals(1, arrayList.indexOf(-1));
        assertEquals(2, arrayList.indexOf(-2));
        assertEquals(3, arrayList.indexOf(-3));
    }

    @Test
    public void size() {
        ImmutableArrayList arrayList = new ImmutableArrayList();
        assertEquals(0, arrayList.size());
    }

    @Test
    public void clear() {
        assertFalse(arrayList.isEmpty());
        arrayList = (ImmutableArrayList) arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void isEmpty() {
        assertFalse(arrayList.isEmpty());
    }

    @Test
    public void toArray() {
        Object[] arr = arrayList.toArray();
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], arrayList.get(i));
        }
    }
}