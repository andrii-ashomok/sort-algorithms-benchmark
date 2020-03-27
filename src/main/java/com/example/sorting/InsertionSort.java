package com.example.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[] {3, 6, 8, 8, 1, 0, 4, 5, 9, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] sort(int[] arr) {
        int current;
        for (int i = 0; i < arr.length; i ++) {
            for (int incr = i+1; incr < arr.length; incr++) {
                if (arr[i] > arr[incr]) {
                    current = arr[incr];
                    shiftRight(arr, i, incr - 1);
                    arr[i] = current;
                }
            }

        }
        return arr;
    }

    public static void shiftRight(int[] arr, int begin, int end) {
        if (end + 1 - begin >= 0) System.arraycopy(arr, begin, arr, begin + 1, end + 1 - begin);
    }
}
