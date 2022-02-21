package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

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
    private int size;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree tree = new RecursiveBinarySearchTree();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    private boolean insertHelper(Node<T> current, T element) {
        if (current.value.compareTo(element) == 0) {
            return false;
        }
        if (current.value.compareTo(element) > 0) {
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
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        return insertHelper(root, element);
    }


    private boolean containsHelper(Node<T> current, T val) {
        if (current == null) {
            return false;
        }
        if (current.value.compareTo(val) == 0) {
            return true;
        }
        if (current.value.compareTo(val) > 0) {
            return containsHelper(current.left, val);
        } else {
            return containsHelper(current.right, val);
        }
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            return false;
        }
        return containsHelper(root, element);
    }

    @Override
    public int size() {
        return size;
    }


    private int dh2(Node<T> node) {
        return node != null ? 1 : 0;
    }

    //...7...
    //.5...9..
    //3.......
    private int depthHelper(Node<T> current, int currentDepth) {
        if (current == null) {
            return currentDepth;
        }
        currentDepth += Math.max(dh2(current.left), dh2(current.right));
        return Math.max(depthHelper(current.left, currentDepth), depthHelper(current.right, currentDepth));
    }

    @Override
    public int depth() {
        return depthHelper(root, 0);
    }

    //SEEN

    private void travelHelper(Node<T> current, Consumer<T> consumer) {
        if (current != null) {
            travelHelper(current.left, consumer);
            consumer.accept(current.value);
            travelHelper(current.right, consumer);
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        travelHelper(root, consumer);
    }
}
