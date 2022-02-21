package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Objects;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;


    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        //solved but stuck
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        Node<T> newOne = new Node<>(element);
        if (isEmpty()) {
            head = newOne;
        }
        if (size == 1){
            head.next = newOne;
        }
        newOne.next = tail;
        tail = newOne;
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            tail = null;
        }
        T oldHeadVal = head.value;
        head = head.next;
        size--;

        return oldHeadVal;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
