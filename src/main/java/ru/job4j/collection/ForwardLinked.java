package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        Node<T> tail = head;
        if (tail == null) {
            head = node;
        } else {
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.element;
        head.element = null;
        head = head.next;
        size--;
        modCount++;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> eNode = head;
            int count = modCount;

            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return eNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = eNode.element;
                eNode = eNode.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T item, Node<T> next) {
            this.element = item;
            this.next = next;
        }
    }
}