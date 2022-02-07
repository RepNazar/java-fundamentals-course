package com.bobocode.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Test
public class LinkedListTest {
    List<Integer> list = new LinkedList<>();

    public void size() {
        list = LinkedList.of(2, 3, 4, 5);
        Assertions.assertEquals(4, list.size());
        list = LinkedList.of(2, 3, 4, 5, 6, 7);
        Assertions.assertEquals(6, list.size());
    }

    public void ofTest(Integer... elements) {
        list = LinkedList.of(elements);
        Assertions.assertEquals(list.size(), elements.length);
        for (int e : elements) {
            Assertions.assertTrue(list.contains(e));
        }
    }

    public void addInEmptyTest(int element) {
        list.add(element);
        Assertions.assertTrue(list.contains(element));
        Assertions.assertEquals(1, list.size());
    }

    public void addInNotEmptyTest(int element) {
        list.add(element);
        Assertions.assertTrue(list.contains(element));
        Assertions.assertTrue(list.get(element));

        Assertions.assertEquals(1, list.size());
    }
}
