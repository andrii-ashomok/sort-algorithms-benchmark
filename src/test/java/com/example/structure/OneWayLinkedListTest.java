package com.example.structure;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class OneWayLinkedListTest {

    private OneWayLinkedList<MyTest> linkedList;
    private final int COUNT = 10;

    @Test
    public void add() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest last = linkedList.last();
        MyTest first = linkedList.first();
        Assert.assertEquals(0, first.getNumber());
        Assert.assertEquals(COUNT - 1, last.getNumber());
        Assert.assertEquals(linkedList.size(), COUNT);
    }

    @Test
    public void contains() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        Assert.assertTrue(linkedList.contains(new MyTest(7)));
        Assert.assertFalse(linkedList.contains(new MyTest(100)));
    }

    @Test
    public void first() {
        linkedList = new OneWayLinkedList<>();
        MyTest test1 = new MyTest(-2);
        linkedList.first(test1);
        Assert.assertEquals(linkedList.first(), test1);
        Assert.assertEquals(linkedList.size(), 1);

        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest test2 = new MyTest(-1);
        linkedList.first(test2);
        Assert.assertEquals(linkedList.first(), test2);
        Assert.assertEquals(linkedList.size(), COUNT + 2);
    }

    @Test
    public void putIntoEmpty() {
        linkedList = new OneWayLinkedList<>();
        Assert.assertFalse(linkedList.put(new MyTest(1), new MyTest(2)));
        Assert.assertEquals(linkedList.size(), 0);
    }

    @Test
    public void put() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest test = new MyTest(-1);
        Assert.assertTrue(linkedList.put(new MyTest(1), test));
        Assert.assertEquals(linkedList.size(), COUNT + 1);
        Assert.assertTrue(linkedList.contains(test));
    }

    @Test
    public void putAsLast() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest test = new MyTest(-1);
        Assert.assertTrue(linkedList.put(new MyTest(COUNT - 1), test));
        Assert.assertEquals(linkedList.size(), COUNT + 1);
        Assert.assertTrue(linkedList.contains(test));
        Assert.assertEquals(test, linkedList.last());
    }

    @Test
    public void removeEmpty() {
        linkedList = new OneWayLinkedList<>();
        Assert.assertFalse(linkedList.remove(new MyTest(1)));
        Assert.assertEquals(linkedList.size(), 0);
    }

    @Test
    public void remove() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest test = new MyTest(1);
        Assert.assertTrue(linkedList.remove(test));
        Assert.assertEquals(linkedList.size(), COUNT - 1);
        Assert.assertFalse(linkedList.contains(test));
    }

    @Test
    public void removeFirst() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest test = new MyTest(0);
        Assert.assertTrue(linkedList.remove(test));
        Assert.assertEquals(linkedList.size(), COUNT - 1);
        Assert.assertFalse(linkedList.contains(test));
        Assert.assertEquals(new MyTest(1), linkedList.first());
    }


    @Test
    public void removeLast() {
        linkedList = new OneWayLinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            linkedList.add(new MyTest(i));
        }

        MyTest test = new MyTest(COUNT - 1);
        Assert.assertTrue(linkedList.remove(test));
        Assert.assertEquals(linkedList.size(), COUNT - 1);
        Assert.assertFalse(linkedList.contains(test));
        Assert.assertEquals(new MyTest(COUNT - 2), linkedList.last());
    }


    private static class MyTest {
        private int number;

        public MyTest(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyTest myTest = (MyTest) o;
            return number == myTest.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }

}
