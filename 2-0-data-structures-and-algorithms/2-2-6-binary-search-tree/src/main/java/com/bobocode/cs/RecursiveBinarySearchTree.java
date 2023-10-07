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

    private static class Node<T extends Comparable<T>> {
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
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        return insertHelper(root, element);
    }


    private boolean insertHelper(Node<T> node, T element) {
        if (node.value.compareTo(element) > 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                size++;
                return true;
            }
            return insertHelper(node.left, element);
        } else if (node.value.compareTo(element) < 0) {
            if (node.right == null) {
                node.right = new Node<>(element);
                size++;
                return true;
            }
            return insertHelper(node.right, element);
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return containsHelper(root, element);
    }

    private boolean containsHelper(Node<T> node, T element) {
        if (node == null) {
            return false;
        }
        if (node.value.compareTo(element) == 0) {
            return true;
        }
        if (node.value.compareTo(element) > 0) {
            return containsHelper(node.left, element);
        } else {
            return containsHelper(node.right, element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root == null ? 0 : depthHelper(root) - 1;
    }

    private int depthHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depthHelper(node.left), depthHelper(node.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        traverseHelper(root, consumer);
    }

    private void traverseHelper(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        traverseHelper(node.left, consumer);
        consumer.accept(node.value);
        traverseHelper(node.right, consumer);
    }
}
