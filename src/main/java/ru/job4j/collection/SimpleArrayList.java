package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    public void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container,
                    10);
        } else {
            container = Arrays.copyOf(container,
                    container.length * 2);
        }
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        modCount++;
        System.arraycopy(container, index + 1,
                container, index,
                container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int iter = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iter < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[iter++];
            }
        };
    }
}
