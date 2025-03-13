package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class XArrayStack<T> implements XStack<T>{
    private static final int DEFAULT_CAPACITY = 4;
    private Object[] array;
    private int size;
    private int capacity;

    public XArrayStack() {
        this(DEFAULT_CAPACITY);
    }
    public XArrayStack(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    @Override
    public void push(T element) {
//        if (element == null) {
            Objects.requireNonNull(element, "Value cannot be null");
        //            throw new NullPointerException();
        //}
        if (size + 1 > capacity) {
            capacity *= 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size++] = element;
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T returnValue = (T) array[--size];
        return returnValue;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return (T) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return pop();
            }
        };
    }
}
