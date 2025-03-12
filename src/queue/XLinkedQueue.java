package queue;

import java.util.Iterator;
import list.Node;

public class XLinkedQueue<T> implements XQueue<T> {
    private static final int INITIAL_CAPACITY = 4;
    private Node<T> node;
    private int size;

    @Override
    public boolean enqueue(T element) {
        if (element == null) {
            return false;
        }
        Node<T> newNode = new Node<>(element);
        if (node == null) {
            node = newNode;
            ++size;
            return true;
        }
        Node<T> tmpNode = node;
        for (int i = 0; i < size - 1; ++i) {
            tmpNode = tmpNode.getNext();
        }
        tmpNode.setNext(newNode);
        ++size;
        return true;
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        Node<T> returnNode = node;
        node = node.getNext();
        --size;
        return returnNode.getElement();
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return node.getElement();
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
        node = null;
        size = 0;
    }

    @Override
    public XQueue<T> copy() {
        XLinkedQueue<T> copiedQueue = new XLinkedQueue<>();
        copiedQueue.node = node;
        copiedQueue.size = size;
        return (XQueue<T>) copiedQueue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private Node<T> node = XLinkedQueue.this.node;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T val = node.getElement();
                node = node.getNext();
                ++index;
                return val;
            }
        };
    }
}
