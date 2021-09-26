package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the tail and head of the queue.
 *
 * @param <T> a generic parameter
 */

public class LinkedQueue<T> implements Queue<T> {

    public static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private int size = 0;
    Node<T> tail;
    Node<T> head;

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        if (tail != null) {
            Node<T> currentTail = tail;
            tail = new Node<T>(element);
            currentTail.next = tail;
        } else {
            tail = new Node<T>(element);
            head = tail;
        }
        size++;
    }

    /**
     * Retrieves and removes queue tail.
     *
     * @return an element that was retrieved from the tail or null if queue is empty
     */
    public T poll() {
        if (head == null) {
            return null;
        } else {
            T temp = head.value;
            head = head.next;
            size--;
            if(size == 0){
                tail = null;
            }
            return temp;
        }
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
