package com.bobocode.cs;

import java.util.Arrays;
import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        return insertHelper(root, element);
    }

    private boolean insertHelper(Node<T> node, T value) {
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node<>(value);
                size++;
                return true;
            }
            return insertHelper(node.left, value);
        }
        if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node<>(value);
                size++;
                return true;
            }
            return insertHelper(node.right, value);
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return containsHelper(root, element);
    }

    private boolean containsHelper(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        if (value.compareTo(node.value) < 0) {
            return containsHelper(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            return containsHelper(node.right, value);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return Math.max(depthHelper(root) - 1, 0);
    }

    private int depthHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depthHelper(node.left), depthHelper(node.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        travelHelper(root, consumer);
    }

    private void travelHelper(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            travelHelper(node.left, consumer);
            consumer.accept(node.value);
            travelHelper(node.right, consumer);
        }
    }
}
