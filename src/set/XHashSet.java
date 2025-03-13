package set;

import java.util.HashMap;
import java.util.Iterator;

public class XHashSet<T> implements XSet<T>{
    private static final Object PRESENT = new Object();
    private HashMap<T, Object> map;

    public XHashSet() {
        map = new HashMap<>();
    }

    @Override
    public boolean add(T element) {
        return map.put(element, PRESENT) == null;
    }

    @Override
    public boolean remove(T element) {
        return map.remove(element) != null;
    }

    @Override
    public boolean contains(T element) {
        return map.containsKey(element);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public XSet<T> union(XSet<T> other) {
        XHashSet<T> newSet = new XHashSet<T>();
        for (T t : this) {
            newSet.add(t);
        }
        for (T t : other) {
            newSet.add(t);
        }
        return newSet;
    }

    @Override
    public XSet<T> intersection(XSet<T> other) {
        XHashSet<T> newSet = new XHashSet<T>();
        for (T t : this) {
            if (other.contains(t)) {
                newSet.add(t);
            }
        }
        return newSet;
    }

    @Override
    public XSet<T> difference(XSet<T> other) {
       XHashSet<T> newSet = new XHashSet<T>();
        for (T t : this) {
            newSet.add(t);
        }
        for (T t : other) {
            newSet.remove(t);
        }
        return newSet;
    }

    @Override
    public XSet<T> symmetricDifference(XSet<T> other) {
        XSet<T> newSet = union(other);
        for (T t : intersection(other)) {
            newSet.remove(t);
        }
        return newSet;
    }

    @Override
    public boolean isSubsetOf(XSet<T> other) {
        for (T t : this) {
            if (!other.contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSupersetOf(XSet<T> other) {
        return other.isSubsetOf(this) && this.size() >= other.size();
    }

    @Override
    public XSet<T> copy() {
        XHashSet<T> newSet = new XHashSet<>();
        newSet.map.putAll(this.map);
        return newSet;
    }
}
