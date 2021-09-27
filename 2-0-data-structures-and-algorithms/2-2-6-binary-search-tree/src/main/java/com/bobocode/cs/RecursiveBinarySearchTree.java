package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
        } else {
            Node<T> temp = searchRecursively(root, element);
            if (element.compareTo(temp.value) == 0) {
                return false;
            } else if (element.compareTo(temp.value) > 0) {
                temp.right = new Node<>(element);
            } else {
                temp.left = new Node<>(element);
            }
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return searchRecursively(root, element).value == element;
    }

    private Node<T> searchRecursively(Node<T> node, T searchedValue) {
        boolean searchedGreater = searchedValue.compareTo(node.value) > 0;
        if (node.left == null && !searchedGreater || node.right == null && searchedGreater) {
            return node;
        }
        if (!searchedGreater) {
            return searchRecursively(node.left, searchedValue);
        }
        return searchRecursively(node.right, searchedValue);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return findDepth(root, 0);
    }

    //TODO:Rewrite
    public int findDepth(Node<T> node, int depth) {
        if (node != null) {
            throw new ExerciseNotCompletedException();//Marked as non-valid
        }
        if (node == null) {
            return depth;
        }
        if (node.left != null) {
            depth++;
            return findDepth(node.left, depth);
        }
        if (node.right != null) {
            depth++;
            return findDepth(node.right, depth);
        }
        return depth;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.right, consumer);
        }
    }
}
