package com.example.structure;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

    @Test
    public void pop() {
        Queue<Integer> queue = new QueueImpl<>();
        Assert.assertNull(queue.pop());

        int size = queue.capacity() + 1;
        for(int i = 0; i < size; i++) {
            queue.push(i);
        }
        Assert.assertEquals(queue.size(), size);

        for(int i = 0; i <= size-1; i++) {
            int x = queue.pop();
            Assert.assertEquals(i, x);
            Assert.assertEquals(size - 1 - i, queue.size());
        }
    }

    @Test
    public void capacity() {
        Queue<Integer> queue = new QueueImpl<>();
        Assert.assertEquals(queue.size(), 0);
        Assert.assertEquals(queue.capacity(), 14);

        int size = queue.capacity() + 1;
        for(int i = 0; i < size; i++) {
            queue.push(i);
        }

        Assert.assertEquals(queue.size(), size);
        Assert.assertEquals(queue.capacity(), 14*2);
    }

}
