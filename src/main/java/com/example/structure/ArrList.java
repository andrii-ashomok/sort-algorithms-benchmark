package com.example.structure;

public class ArrList<T> {
    private Object[] arr;
    private int capacity = 14;
    private int currentPosition = 0;

    public ArrList() {
        arr = new Object[capacity];
    }

    public ArrList(int capacity) {
        this.arr = new Object[capacity];
        this.capacity = capacity;
    }


    public T get(int i) {
        if (i >= arr.length) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("Current length of array is %s, but put %s", arr.length, i));
        }

        return (T) arr[i];
    }

    public boolean add(T t) {
        if (currentPosition == arr.length) {
            Object[] dest = new Object[arr.length + capacity];
            System.arraycopy(arr, 0, dest, 0, arr.length);
            arr = dest;
        }
        arr[currentPosition] = t;
        currentPosition++;
        return false;
    }


    public int size() {
        return currentPosition;
    }

    public int capacity() {
        return arr.length;
    }

    public T[] toArray() {
        Object[] tmp = new Object[currentPosition];
        System.arraycopy(arr, 0, tmp, 0, currentPosition);
        return (T[]) tmp;
    }
}
