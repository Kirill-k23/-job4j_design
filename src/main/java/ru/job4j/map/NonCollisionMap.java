package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int buckets(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int bucket = buckets(key);
        boolean result = table[bucket] == null;
        if (result) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                K key = entry.key;
                int bucket = buckets(key);
                newTable[bucket] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int bucket = buckets(key);
        if (table[bucket] != null) {
            if (equal(key, bucket)) {
                result = table[bucket].value;
            }
        }
        return result;
    }

    private boolean equal(K key, int bucket) {
        return Objects.hashCode(table[bucket].key) == Objects.hashCode(key)
                && Objects.equals(table[bucket].key, key);
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int bucket = buckets(key);
        if (table[bucket] != null) {
            if (equal(key, bucket)) {
                table[bucket] = null;
                result = true;
                count--;
                modCount++;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}