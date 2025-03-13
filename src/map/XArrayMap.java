package map;

import java.util.HashSet;
import java.util.Set;

public class XArrayMap<K, V> implements XMap<K, V> {
    private static final int DEFAULT_CAPACITY = 5;
    private Entry<K, V>[] entries;
    private int capacity;
    private int size;

    public XArrayMap() {
        this(DEFAULT_CAPACITY);
    }

    public XArrayMap(int capacity) {
        this.capacity = capacity;
        this.entries = new Entry[capacity];
    }

    public int getKeyIndex(K key) {
        return (key.hashCode() % capacity + capacity) % capacity;
    }

    @Override
    public V put(K key, V value) {
        if (size + 1 > capacity) {
            capacity *= 2;
            Entry<K, V>[] newEntries = new Entry[capacity];
            System.arraycopy(entries, 0, newEntries, 0, size);
            entries = newEntries;
        }
        int index = getKeyIndex(key);
        if (entries[index] == null) {
            entries[index] = new Entry<>(key, value);
            ++size;
            return value;
        } else if (entries[index] != null && entries[index].getKey().equals(key)) { //동일키 존재시
            entries[index].setValue(value);
        } else {
            while (entries[index++ % capacity] != null) {
            }
            entries[index % capacity] = new Entry<>(key, value);
            ++size;
        }
        return value;
    }

    @Override
    public V get(K key) {
        int index = getKeyIndex(key);
        if (entries[index] == null) {
            throw new NullPointerException();
        }
        return entries[index].getValue();
    }

    @Override
    public boolean containsKey(K key) {
        int index = getKeyIndex(key);
        return entries[index] != null && entries[index].getKey().equals(key);
    }

    @Override
    public boolean containsValue(V value) {
        for (Entry<K, V> e : entries) {
            if (e != null && e.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        int index = getKeyIndex(key);
        V value = entries[index].getValue();
        entries[index] = null;
        --size;
        return value;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; ++i) {
            entries[i] = null;
            size = 0;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Entry<K, V> entry : entries) {
            if (entry != null) { // null 체크 필요
                keySet.add(entry.getKey());
            }
        }
        return keySet;
    }

    @Override
    public Set<V> values() {
        Set<V> keySet = new HashSet<>();
        for (Entry<K, V> entry : entries) {
            if (entry != null) { // null 체크 필요
                keySet.add(entry.getValue());
            }
        }
        return keySet;
    }
}
