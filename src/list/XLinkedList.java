package list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class XLinkedList<T> implements XList<T>{
    Node<T> node;
    int size;

    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<>(element);
        newNode.setNext(null);
        if (size == 0) {
            node = newNode;
            ++size;
            return;
        }
        Node<T> tmpNode = node;
        while (tmpNode.getNext() != null) {
            tmpNode = tmpNode.getNext();
        }
        tmpNode.setNext(newNode);
        ++size;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (element == null) {
            throw new NullPointerException();
        }

        int count = 0;
        Node<T> newNode = new Node<>(element);
        ++size;
        Node<T> tmpNode = node;
        while (count++ < index - 1) {
            tmpNode = tmpNode.getNext();

        }
        if (index == 0) {
            newNode.setNext(node);
            node = newNode;
            return;
        }
        newNode.setNext(tmpNode.getNext());
        tmpNode.setNext(newNode);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size || size == 0) {
            throw new IndexOutOfBoundsException();
        }
        --size;
        int count = 0;

        Node<T> tmpNode = node;
        Node<T> targetNode;
        while (count++ < index - 1) {
            tmpNode = tmpNode.getNext();
        }
        if (index == 0) {
            targetNode = node;
            node = node.getNext();
            return targetNode.getElement();
        }
        targetNode = tmpNode.getNext();
        tmpNode.setNext(tmpNode.getNext().getNext());

        return targetNode.getElement();
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node<T> tmpNode = node;
        --size;
        if (tmpNode.getElement().equals(element)) {
            node = node.getNext();
            return true;
        }
        while (tmpNode.getNext() != null) {
            if (tmpNode.getNext().getElement().equals(element)) {
                tmpNode.setNext(tmpNode.getNext().getNext());
                return true;
            }
            tmpNode = tmpNode.getNext();
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        Node<T> tmpNode = node;
        while (tmpNode.getNext() != null) {
            if (tmpNode.getElement().equals(element)) {
                return true;
            }
            tmpNode = tmpNode.getNext();
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        Node<T> tmpNode = node;
        int count = 0;
        while (tmpNode.getNext() != null) {
            if (tmpNode.getElement().equals(element)) {
                return count;
            }
            ++count;
            tmpNode = tmpNode.getNext();
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> tmpNode = node;
        for (int i = 0; i < index; ++i) {
            tmpNode = tmpNode.getNext();
        }
        return tmpNode.getElement();
    }

    @Override
    public void set(int index, T element) {
        Node<T> tmpNode = node;
        for (int i = 0; i < index; ++i) {
            tmpNode = tmpNode.getNext();
        }
        tmpNode.setElement(element);
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (size <= 1 || node == null) {
            return;
        }
        Node<T> tmp = node;
        T[] arr = (T[]) new Object[size];

        for (int i = 0; i < size; ++i) {
            arr[i] = tmp.getElement();
            tmp = tmp.getNext();
        }
        Arrays.sort(arr, comparator);
        tmp = node;
        for (Object o : arr) {
            tmp.setElement((T) o);
            tmp = tmp.getNext();
        }
    }

    @Override
    public XList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size - 1 || toIndex - fromIndex < 1) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> tmpNode = node;
        for (int i = 0; i < fromIndex; ++i) {
            tmpNode = tmpNode.getNext();
        }
        XList<T> xList = new XLinkedList<>();
        for (int i = 0; i < toIndex - fromIndex; ++i) {
            xList.add(tmpNode.getElement());
            tmpNode = tmpNode.getNext();
        }
        return xList;
    }

    @Override
    public void addAll(List<T> otherList) {
        for (T t : otherList) {
            add(t);
        }
    }

    @Override
    public void forEach(Consumer<T> action) {
        Node<T> tmpNode = node;
        for (int i = 0; i < size; ++i) {
            action.accept(tmpNode.getElement());
            tmpNode = tmpNode.getNext();
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
        node = null;
        size = 0;
    }

    @Override
    public XList<T> copy() {
        XList<T> linkedList = new XLinkedList<>();
        Node<T> tmpNode = node;
        for (int i = 0; i < size; ++i) {
            linkedList.add(tmpNode.getElement());
            tmpNode = tmpNode.getNext();
        }
        return linkedList;
    }
}
