package list;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class XArrayList<T> implements Iterable<T>, XList<T>{

    private static final int DEFAULT_CAPACITY = 4;
    private Object[] array;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public XArrayList(int size) {
        array = new Object[size];
        this.capacity = size;
    }
    public XArrayList() {
        array = new Object[capacity];
        this.size = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size + 1 > capacity) {
            capacity = size * 2;
            Object[] tmp = new Object[capacity];
            System.arraycopy(array, 0, tmp, 0, capacity / 2);
            array = tmp;
        }
        array[size] = element;
        ++size;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size + 1 > capacity) {
            capacity = size * 2;
            Object[] tmp = new Object[capacity];
            System.arraycopy(array, 0, tmp, 0, capacity / 2);
            array = tmp;
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        ++size;

    }

    @Override
    public T remove(int index) {

        T returnValue = (T) array[index];
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1);
        }
        --size;
        return returnValue;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; ++i) {
            if (array[i].equals((T) element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object element) {
        for (int i = 0; i < size; ++i) {
            if (array[i].equals((T) element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; ++i) {
            if (array[i].equals((T) element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    @Override
    public void sort(Comparator comparator) {
        Arrays.sort(array, 0, size, comparator);

    }

    @Override
    public XList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > size - 1 || toIndex <= 0 || toIndex > size - 1 || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        XList<T> list = new XArrayList<T>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; ++i) {
            list.add((T) array[i]);
        }
        return list;
    }

    @Override
    public void addAll(List<T> otherList) {
        for (int i = 0; i < otherList.size(); ++i) {
            add(otherList.get(i));
        }
    }

    @Override
    public void forEach(Consumer action) {
        for (int i = 0; i < size; ++i) {
            action.accept(array[i]);
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
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public XList<T> copy() {
        XList<T> copiedList = new XArrayList<>();
        for (int i = 0; i < size; ++i) {
            copiedList.add(get(i));
        }
        return copiedList;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                return (T) array[index++];
            }
        };
    }
}
