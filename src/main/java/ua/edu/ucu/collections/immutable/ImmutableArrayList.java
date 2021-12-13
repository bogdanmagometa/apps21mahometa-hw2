package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] array;

    public ImmutableArrayList(Object[] elements) {
        array = Arrays.copyOf(elements, elements.length);
    }

    public ImmutableArrayList() {
        array = new Object[0];
    }

    @Override
    public ImmutableList add(Object e) {
        Object[] newArray = new Object[this.size() + 1];
        System.arraycopy(this.array, 0, newArray, 0, this.size());
        newArray[this.size()] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] newArray = new Object[this.size() + 1];
        System.arraycopy(this.array, 0, newArray, 0, index);
        newArray[index] = e;
        System.arraycopy(this.array, index, newArray, index+1, this.size() - index);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Object[] newArray = new Object[this.size() + c.length];
        System.arraycopy(this.array, 0, newArray, 0, this.size());
        System.arraycopy(c, 0, newArray, this.size(), c.length);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        Object[] newArray = new Object[this.size() + c.length];
        System.arraycopy(this.array, 0, newArray, 0, index);
        System.arraycopy(c, 0, newArray, index, c.length);
        System.arraycopy(this.array, index, newArray, index + c.length, this.size() - index);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public ImmutableList remove(int index) {
        Object[] newArray = new Object[this.size() - 1];
        System.arraycopy(this.array, 0, newArray, 0, index);
        System.arraycopy(this.array, index+1, newArray, index, this.size()-index-1);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        Object[] newArray = toArray();
        newArray[index] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < this.size(); i++) {
            if (e.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.array, this.size());
    }
}
