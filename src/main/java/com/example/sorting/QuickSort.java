package com.example.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] {4, 7, 3, 4, 8, 9, 1, 6, 5, 4, 1, 6, 8, 3, 9};
        sort(arr);
    }

    public static void sort(int[] arr) {
        int index = arr.length / 2;
        int length = arr.length - 1;
        int lPosition = 0, rPosition = arr.length - 1;
        boolean lFlag, rFlag;
        while (length > 0) {
            lFlag = arr[lPosition] >= arr[index];

            rFlag = arr[rPosition] <= arr[index];

            if (lFlag && rFlag) {
                int temp = arr[lPosition];
                arr[lPosition] = arr[rPosition];
                arr[rPosition] = temp;
                lFlag = false;
                rFlag = false;
            }

            if (!lFlag && lPosition < index) { lPosition++; }
            if (!rFlag && rPosition > index) { rPosition--; }

            if (lPosition == index && rPosition > index) {

            }

            if (rPosition == index && lPosition < index) {

            }

            length--;
        }

        System.out.println(Arrays.toString(arr));
    }


    private void getBiggest(int[] arr, int index, int leftPosition, int rightPosition) {
        for (int i = rightPosition; i > index; i--) {
            if (arr[index] > arr[i]) {

            }
        }
    }
}
