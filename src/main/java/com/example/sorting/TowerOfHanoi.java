package com.example.sorting;

public class TowerOfHanoi {

    public static void hanoi(int n, char a, char b, char c) {
        if (n > 0) {
            hanoi(n - 1, a, c, b);
            System.out.println(String.format("Disk %s from %s to %s", n, a, c));
            hanoi(n - 1, b, a, c);
        }

    }

    public static void main(String[] args) {
        hanoi(3, 'a', 'b', 'c');
    }

}
