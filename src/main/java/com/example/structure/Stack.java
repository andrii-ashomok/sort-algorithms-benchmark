package com.example.structure;

public interface Stack<T> {

    T pop();

    void push(T t);

    int size();

    int capacity();

    T[] toArray();
}
