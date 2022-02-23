package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> last;
    private Node<T> first;
    private int size = 0;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();
        Arrays.stream(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node<T> newOne = new Node<>(element);
        if (isEmpty()) {
            first = newOne;
        } else {
            last.next = newOne;
        }

        last = newOne;
        size++;
    }

    private Node<T> prevToIndex(int index) {
        Node<T> prev = first;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        return prev;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index == 0 && isEmpty() || index == size) {
            add(element);
            return;
        }
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<T> prev = prevToIndex(index);
            newNode.next = prevToIndex(index).next;
            prev.next = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Objects.checkIndex(index, size);
        prevToIndex(index).next.value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return first.value;
        }
        return prevToIndex(index).next.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        Objects.checkIndex(index, size);
        if (index == 0) {
            T value = first.value;
            first = first.next;
            size--;
            return value;
        } else {
            Node<T> prev = prevToIndex(index);
            T value = prev.next.value;
            prev.next = prev.next.next;
            size--;
            return value;
        }
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (!isEmpty() && element != null) {
            Node<T> temp = first;
            for (int i = 0; i < size; i++) {
                if (temp.value == element) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}
