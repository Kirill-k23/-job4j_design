package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        Node<E> tail = head;
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> eNode = head;
            int count = modCount;

            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return eNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = eNode.element;
                eNode = eNode.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.element = item;
            this.next = next;
        }
    }
}