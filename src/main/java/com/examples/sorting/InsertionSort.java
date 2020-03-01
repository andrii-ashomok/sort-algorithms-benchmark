package com.examples.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = Helper.convertArgs(args);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] sort(int[] arr) {
        int prev, current, position;
        for (int incr = 1; incr < arr.length; incr++) {
            position = incr - 1;
            prev = arr[position];
            if (prev > arr[incr]) {
                current = arr[incr];
                arr[position] = current;
                arr[incr] = prev;
                if (position > 0) {
                    for (int decr = position - 1; decr >= 0; decr--) {
                        if (current < arr[decr]) {
                            prev = arr[decr];
                            arr[decr] = current;
                            arr[decr + 1] = prev;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return arr;
    }
}
