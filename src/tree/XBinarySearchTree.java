package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XBinarySearchTree<T extends Comparable<T>> implements XBinaryTree<T> {
    private Node<T> root;
    private int size;

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) {
            ++size;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertRec(node.left, value);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    @Override
    public boolean search(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            return searchRec(node.left, value);
        } else if (cmp > 0) {
            return searchRec(node.right, value);
        }
        return true;
    }

    @Override
    public void delete(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = deleteRec(node.left, value);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, value);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            }
            Node<T> minNode = findMin(node.right);
            node.value = minNode.value;
            node.right = deleteRec(node.right, minNode.value);
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    @Override
    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inorderRec(node.left, result);
        result.add(node.value);
        inorderRec(node.right, result);
    }
}
