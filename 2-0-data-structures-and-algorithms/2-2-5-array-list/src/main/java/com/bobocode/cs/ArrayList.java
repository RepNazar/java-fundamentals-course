package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private int capacity;
    private Object[] data;

    private int size;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = initCapacity;
        this.data = new Object[initCapacity];
        this.size = 0;
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new ArrayList<>(elements.length + elements.length / 2);
        Arrays.stream(elements)
                .forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        checkCapacity();
        data[size] = element;
        size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        if (index == size || isEmpty()) {
            add(element);
            return;
        }
        checkCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
        return (T) data[index];
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
        return (T) data[0];
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
        return (T) data[size - 1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (Objects.isNull(element)){
            throw new NullPointerException();
        }
        Objects.checkIndex(index, size);
        data[index] = element;
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
        Objects.checkIndex(index, size);
        T val = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return val;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        return Arrays.stream(data)
                .anyMatch(e -> e == element);
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
     * @return amount of saved elements
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
        data = null;
        size = 0;
    }

    private void checkCapacity() {
        if (size + 1 >= capacity) {
            int newCapacity = capacity + capacity << 1;
            Object[] temp = new Object[newCapacity];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
            capacity = newCapacity;
        }
    }
}
