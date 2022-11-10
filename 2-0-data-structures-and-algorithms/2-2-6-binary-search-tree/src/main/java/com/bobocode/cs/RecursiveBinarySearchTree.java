package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.Objects;
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
        Arrays.stream(elements)
                .forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        Node<T> newOne = new Node<>(element);
        if (root == null) {
            root = newOne;
            size++;
            return true;
        } else {
            return insertHelper(root, newOne);
        }
    }

    private boolean insertHelper(Node<T> current, Node<T> newOne) {
        if (current == null || current.value.compareTo(newOne.value) == 0) {
            return false;
        }
        if (current.value.compareTo(newOne.value) > 0) {
            if (current.left == null) {
                current.left = newOne;
                size++;
                return true;
            }
            return insertHelper(current.left, newOne);
        } else {
            if (current.right == null) {
                current.right = newOne;
                size++;
                return true;
            }
            return insertHelper(current.right, newOne);
        }
    }

    @Override
    public boolean contains(T element) {
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        if (size == 0) {
            return false;
        }
        return containsHelper(root, element);
    }

    private boolean containsHelper(Node<T> current, T element) {
        if (current == null) {
            return false;
        }
        if (current.value.compareTo(element) == 0) {
            return true;
        } else if (current.value.compareTo(element) > 0) {
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
        return size == 0 ? 0 : depthHelper(root) - 1;
    }

    private int depthHelper(Node<T> current) {
        if (current != null) {
            return 1 + Math.max(depthHelper(current.left), depthHelper(current.right));
        } else {
            return 0;
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversalHelper(root, consumer);
    }

    private void inOrderTraversalHelper(Node<T> current, Consumer<T> consumer) {
        if (current != null) {
            inOrderTraversalHelper(current.left, consumer);
            consumer.accept(current.value);
            inOrderTraversalHelper(current.right, consumer);
        }
    }
}
