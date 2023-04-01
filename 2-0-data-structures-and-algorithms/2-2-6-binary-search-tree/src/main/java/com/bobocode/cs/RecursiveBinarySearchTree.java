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
        if (node == null || element.compareTo(node.value) == 0) {
            return false;
        } else if (element.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                size++;
                return true;
            }
            return insertHelper(node.left, element);
        } else {
            if (node.right == null) {
                node.right = new Node<>(element);
                size++;
                return true;
            }
            return insertHelper(node.right, element);
        }
    }

    @Override
    public boolean contains(T element) {
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        return containsHelper(root, element);
    }

    private boolean containsHelper(Node<T> current, T element) {
        if (current == null) {
            return false;
        }
        if (current.value == element) {
            return true;
        }
        if (element.compareTo(current.value) < 0) {
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

    private int depthHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depthHelper(node.left), depthHelper(node.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        LinkedStack<Node<T>> stack = new LinkedStack<>();
        Node<T> current = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            while (current.left != null) {
                current = current.left;
                stack.push(current);
            }
            while (!stack.isEmpty()) {
                current = stack.pop();
                consumer.accept(current.value);
                if (current.right != null) {
                    current = current.right;
                    stack.push(current);
                    break;
                }
            }
        }
    }

    private void traversHelper(Node<T> current, Consumer<T> consumer) {
        if (current.left != null) {
            traversHelper(current.left, consumer);
        }
        consumer.accept(current.value);
        if (current.right != null) {
            traversHelper(current.right, consumer);
        }
    }
}
