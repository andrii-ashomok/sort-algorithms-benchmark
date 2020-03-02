package com.example.structure;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void push() {
        Stack<Integer> stack = new StackImpl<>();
        int size = stack.capacity() + 1;
        for(int i = 0; i < size; i++) {
            stack.push(i);
        }

        Assert.assertEquals(size, stack.size());
    }

    @Test
    public void pop() {
        Stack<Integer> stack = new StackImpl<>();
        Assert.assertNull(stack.pop());

        int size = stack.capacity() + 1;
        for(int i = 0; i < size; i++) {
            stack.push(i);
        }
        Assert.assertEquals(stack.size(), size);

        for(int i = size - 1; i >= 0; i--) {
            int x = stack.pop();
            Assert.assertEquals(i, x);
            Assert.assertEquals(i, stack.size());
        }
    }

    @Test
    public void init() {
        Stack<Integer> filledStack = new StackImpl<>();
        int size = filledStack.capacity() + 1;
        for(int i = 0; i < size; i++) {
            filledStack.push(i);
        }

        Stack<Integer> stack2 = new StackImpl<>(filledStack);
        Assert.assertEquals(size, stack2.size());
        Assert.assertEquals(size - 1, (int) stack2.pop());

        Stack<Integer> stack3 = new StackImpl<>(filledStack.toArray());
        Assert.assertEquals(size, stack3.size());
        Assert.assertEquals(size - 1, (int) stack3.pop());
    }

    @Test
    public void capacity() {
        Stack<Integer> stack = new StackImpl<>();
        Assert.assertEquals(stack.size(), 0);
        Assert.assertEquals(stack.capacity(), 14);

        int size = stack.capacity() + 1;
        for(int i = 0; i < size; i++) {
            stack.push(i);
        }

        Assert.assertEquals(stack.size(), size);
        Assert.assertEquals(stack.capacity(), 14*2);
    }
}
