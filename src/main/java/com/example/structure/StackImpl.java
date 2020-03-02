package com.example.structure;

public class StackImpl<T> implements Stack<T> {
    private Object[] arr;
    private int capacity = 14;
    private int currentPosition = 0;

    public StackImpl() {
        arr = new Object[capacity];
    }

    public StackImpl(Object[] arr) {
        this.arr = arr;
        this.currentPosition = this.arr.length;
    }

    public StackImpl(Stack<T> arr) {
        this.arr = arr.toArray();
        this.currentPosition = this.arr.length;
    }

    public StackImpl(int capacity) {
        this.arr = new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public T pop() {
        if (currentPosition > 0) {
            T t = (T) arr[--currentPosition];
            Object[] dest = new Object[arr.length];
            System.arraycopy(arr, 0, dest, 0, currentPosition);
            arr = dest;
            return t;
        }

        return null;
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
    public int size() {
        return currentPosition;
    }

    @Override
    public int capacity() {
        return arr.length;
    }

    @Override
    public T[] toArray() {
        Object[] tmp = new Object[currentPosition];
        System.arraycopy(arr, 0, tmp, 0, currentPosition);
        return (T[]) tmp;
    }
}
