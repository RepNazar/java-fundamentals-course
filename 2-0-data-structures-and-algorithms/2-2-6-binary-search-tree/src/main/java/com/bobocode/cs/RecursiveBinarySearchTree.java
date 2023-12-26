package com.bobocode.cs;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T> {
        Node<T> right;
        Node<T> left;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<T>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        if (root == null || size == 0) {
            this.root = new Node<>(element);
            size++;
            return true;
        }
        return insertHelper(root, element);
    }

    private boolean insertHelper(Node<T> current, T element) {
        if (current == null) {
            return false;
        }
        if (element.compareTo(current.value) == 0) {
            return false;
        } else if (element.compareTo(current.value) < 0) {
            if (current.left == null) {
                current.left = new Node<>(element);
                size++;
                return true;
            }
            return insertHelper(current.left, element);
        } else {
            if (current.right == null) {
                current.right = new Node<>(element);
                size++;
                return true;
            }
            return insertHelper(current.right, element);
        }
    }

    @Override
    public boolean contains(T element) {
        return containsHelper(root, Objects.requireNonNull(element));
    }

    private boolean containsHelper(Node<T> current, T element) {
        if (current == null) {
            return false;
        }
        if (element.compareTo(current.value) == 0) {
            return true;
        } else if (element.compareTo(current.value) < 0) {
            return containsHelper(current.left, element);
        } else {
            return containsHelper(current.right, element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (root == null) {
            return 0;
        }
        return depthHelper(root) - 1;
    }

    private int depthHelper(Node<T> current) {
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(depthHelper(current.left), depthHelper(current.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        traversHelper(root, consumer);
    }

    private void traversHelper(Node<T> current, Consumer<T> consumer) {
        if (current == null) {
            return;
        }
        traversHelper(current.left, consumer);
        consumer.accept(current.value);
        traversHelper(current.right, consumer);
    }
}
