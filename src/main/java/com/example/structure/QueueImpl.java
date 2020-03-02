package com.example.structure;

public class QueueImpl<T> implements Queue<T> {
    private int capacity = 14;
    private int currentPosition = 0;
    private Object[] arr;

    public QueueImpl() {
        this.arr = new Object[capacity];
    }

    @Override
    public T pop() {
        T t = null;
        if (currentPosition > 0) {
            t = (T) arr[0];
            Object[] dest = new Object[arr.length];
            System.arraycopy(arr, 1, dest, 0, --currentPosition);
            arr = dest;
        }
        return t;
    }

    @Override
    public void push(T t) {
        if (currentPosition == arr.length) {
            Object[] dest = new Object[arr.length + capacity];
            System.arraycopy(arr, 0, dest, 0, arr.length);
            arr = dest;
        }
        arr[currentPosition] = t;
        currentPosition++;
    }

    @Override
    public int capacity() {
        return arr.length;
    }

    @Override
    public int size() {
        return currentPosition;
    }
}
