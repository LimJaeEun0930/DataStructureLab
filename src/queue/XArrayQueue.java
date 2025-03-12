package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class XArrayQueue<T> implements XQueue<T>{
    private static final int INITIAL_CAPACITY = 4;

    private Object[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public XArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    public XArrayQueue(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public Object[] getArray() {
        return array;
    }

    @Override
    public boolean enqueue(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size + 1 > capacity) {
            capacity = capacity * 2;
            Object[]extendedArr =new Object[capacity];
            System.arraycopy(array, 0, extendedArr, 0, size);
            array = extendedArr;
        }
        array[rear++ % capacity] = element;
        rear = rear % capacity;
        ++size;
        return true;
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        T returnValue = (T) array[front++ % capacity];
        front = front % capacity;
        --size;
        return returnValue;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return (T) array[front];
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
        for (int i = 0; i < capacity; ++i) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public XQueue<T> copy() {
        XQueue<T> copiedQueue = new XArrayQueue<>(capacity);
        System.arraycopy(array, 0, ((XArrayQueue<T>)copiedQueue).array , 0, capacity);
        ((XArrayQueue<Object>) copiedQueue).size = size;
        return copiedQueue;
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
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }

        };
    }
}
