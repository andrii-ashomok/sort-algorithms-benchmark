package com.example.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] {30, 80, 10, 90, 40, 50, 70};
        sort(arr);
    }

    public static void sort(int[] arr) {
        int index = sort(arr, arr.length, 0);
        System.out.println(Arrays.toString(arr));
        for (int i = index; i >= 2; i--) {
            sort(arr, i, 0);
            System.out.println(Arrays.toString(arr));
        }

        for (int i = arr.length - index; i >= 2; i--) {
            sort(arr, arr.length, index);
            System.out.println(Arrays.toString(arr));
        }

    }

    private static int sort(int[] arr, int length, int index) {
        int tempIndex = length - 1;
        int pivot = arr[tempIndex];
        int l = -1;
        for (int i = index; i < length; i++) {
            if (arr[i] < pivot) {
                l++;
                swap(arr, l , i);
            }
        }
        swap(arr, l + 1, tempIndex);
        return l + 1;
    }

    private static void swap(int[] arr, int l, int j) {
        int temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;
    }

}
