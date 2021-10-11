package com.smitie.maps;

import java.util.*;

public class MySimpleMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private int size;

    @SuppressWarnings("unchecked")
    private MyEntry<K, V>[] entries = new MyEntry[DEFAULT_CAPACITY];

    public V get(K key) {
        for (MyEntry<K, V> entry : entries) {
            if (entry != null)
                if (entry.key.equals(key))
                    return entry.value;
        }
        return null;
    }

    public void put(K key, V value) {
        boolean alreadyExists = false;
        for (MyEntry<K, V> entry : entries) {
            if (entry != null)
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    alreadyExists = true;
                }
        }

        if (!alreadyExists) {
            checkCapacity();
            entries[size++] = new MyEntry<>(key, value);
        }
    }

    public int size() {
        return size;
    }

    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                entries[i] = null;
                size--;
                condenseArray(i);
            }
        }
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < size; i++) {
            keys.add(entries[i].key);
        }
        return keys;
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add(entries[i].value);
        }
        return values;
    }

    public String toString() {
        return Arrays.toString(entries);
    }

    private void condenseArray(int start) {
        int i;
        for (i = 0; i < start; i++) {
            entries[i] = entries[i + 1];
        }
        entries[i] = null;
    }

    private void checkCapacity() {
        if (size == entries.length) {
            entries = Arrays.copyOf(entries, size << 1);
        }
    }

    private final class MyEntry<K, V> {
        private final K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MyEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
