package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private  int anIn = 0;
    private int anOut = 0;

    public T poll() {
        if (anIn == 0 && anOut == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (anOut == 0) {
            while (anIn != 0) {
                out.push(in.pop());
                anOut++;
                anIn--;
            }
        }
        anOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        anIn++;
    }
}
