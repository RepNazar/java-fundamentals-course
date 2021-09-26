package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        Node(T value){
            this.value = value;
        }
        Node<T> nodeOf(T value){
            return new Node<>(value);
        }
    }

    private Node<T> root;
    private int size;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean insert(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean contains(T element) {
        if(element == null){
            throw new NullPointerException();
        }
        return searchRecursively(root, element) != null;
    }

    private Node<T> searchRecursively(Node<T> node, T searchedValue){
        if(node == null || node.value == searchedValue){
            return node;
        }
        if(searchedValue.compareTo(node.value) < 0) {
            return searchRecursively(node.left, searchedValue);
        }
        return searchRecursively(node.right, searchedValue);
    }

    @Override
    public int size() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int depth() {
        return findDepth(root, 0);
    }

    public int findDepth(Node<T> node, int depth){
        if(node == null){
            return depth;
        }
        depth++;
        findDepth(node.left, depth);
        findDepth(node.right, depth);
        return depth;
    }
    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        throw new ExerciseNotCompletedException();
    }
}
