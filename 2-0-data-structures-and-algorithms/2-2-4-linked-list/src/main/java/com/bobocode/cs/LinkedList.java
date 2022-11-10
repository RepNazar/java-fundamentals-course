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
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    private int size = 0;

    private Node<T> getNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<T> previous = head;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        return previous;
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();
        Arrays.stream(elements)
                .forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        Node<T> newOne = new Node<>(element);
        if (isEmpty()) {
            this.head = newOne;
            this.tail = this.head;
        } else {
            this.tail.next = newOne;
            this.tail = newOne;
        }
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
        if (Objects.isNull(element)) {
            throw new NullPointerException();
        }
        Objects.checkIndex(index, size + 1);
        if (isEmpty() || index == size) {
            add(element);
            return;
        }
        Node<T> newOne = new Node<>(element);
        if (index == 0) {
            newOne.next = head;
            head = newOne;
        } else {
            Node<T> previous = getNodeByIndex(index - 1);
            newOne.next = previous.next;
            previous.next = newOne;
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
        Node<T> node = getNodeByIndex(index);
        node.value = element;
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
        Objects.checkIndex(index, size);
        if (index == 0) {
            return head.value;
        }
        if (index == size - 1) {
            return tail.value;
        }
        Node<T> node = getNodeByIndex(index);
        return node.value;
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
        return head.value;
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
        return tail.value;
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
        if (index == 0) {
            T val = head.value;
            head = head.next;
            size--;
            return val;
        }
        Node<T> previous = getNodeByIndex(index - 1);
        T val = previous.next.value;
        if (index == size - 1) {
            this.tail = previous;
        } else {
            previous.next = previous.next.next;
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
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.value == element) {
                return true;
            }
            current = current.next;
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
        head = null;
        tail = null;
        size = 0;
    }
}
