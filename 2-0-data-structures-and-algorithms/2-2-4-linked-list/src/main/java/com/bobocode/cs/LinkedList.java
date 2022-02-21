package com.bobocode.cs;


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

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

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
        Node<T> newOne = new Node<>(element);
        if (isEmpty()) {
            first = newOne;
        } else {
            last.next = newOne;
        }
        last = newOne;
        size++;
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
        Node<T> newOne = new Node<>(element);

        if (index == 0) {
            newOne.next = first;
            first = newOne;
            size++;
            return;
        }

        Node<T> prev = prevToIndex(Objects.checkIndex(index, size + 1));
        newOne.next = prev.next;
        prev.next = newOne;
        size++;
    }

    private Node<T> prevToIndex(int index) {
        Node<T> prev = first;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
            if (prev == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return prev;
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
        prevToIndex(Objects.checkIndex(index, size)).next.value = element;
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
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return first.value;
        } else {
            return prevToIndex(Objects.checkIndex(index, size)).next.value;
        }
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
        T val;
        if (index == 0) {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            val = first.value;
            first = first.next;
        } else {
            Node<T> prev = prevToIndex(Objects.checkIndex(index, size));
            val = prev.next.value;
            prev.next = prev.next.next;
        }
        size--;
        return val;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }
        Node<T> temp = first;
        while (temp.next != null) {
            if (temp.value == element) {
                return true;
            }
            temp = temp.next;
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
