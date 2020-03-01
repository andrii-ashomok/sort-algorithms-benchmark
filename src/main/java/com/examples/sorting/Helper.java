package com.examples.sorting;

public final class Helper {

    private Helper() {}

    static int[] convertArgs(String[] args) {
        int[] arr = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        return arr;
    }

    static int leftSwap(int[] arr, int index1, int index2) {
        int prev = arr[index1];
        int next = arr[index2];
        arr[index1] = next;
        arr[index2] = prev;
        return next;
    }
}
