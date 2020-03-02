package com.example.structure;

public interface Queue<T> {
    T pop();

    void push(T t);

    int capacity();

    int size();
}
