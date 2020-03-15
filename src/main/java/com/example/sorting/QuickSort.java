package com.example.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        //left
        int[] arr = new int[] {4, 7, 3, 4, 1, 9, 1, 6, 5, 4, 1, 6, 2, 3, 9};
        //right
//        int[] arr = new int[] {9, 7, 8, 4, 7, 9, 8, 6, 5, 4, 7, 6, 9, 9, 9};
//        int[] arr = new int[] {30, 80, 10, 90, 40, 50, 70};
        sort(arr);
    }

    /*public static void sort(int[] arr) {
        int index = arr.length / 2;
        int length = arr.length - 1;
        int lPosition = 0, rPosition = arr.length - 1;
        boolean lFlag, rFlag;
        int currentPosition = index;
        while (length > 0) {
            lFlag = arr[lPosition] > arr[index];
            rFlag = arr[rPosition] < arr[index];

            if (lFlag && rFlag) {
                int temp = arr[lPosition];
                arr[lPosition] = arr[rPosition];
                arr[rPosition] = temp;
                lFlag = false;
                rFlag = false;
            }

            if (!lFlag && lPosition < index) { lPosition++; }
            if (!rFlag && rPosition > index) { rPosition--; }

            currentPosition = index;

            if (lPosition == index && rPosition > index) {
                int tempNumber = arr[index];
                int tempIndex = index;
                for (int i = rPosition; i > index; i--) {
                    if (arr[rPosition] < tempNumber) {
                        int temp = arr[rPosition];
                        int shift = i - tempIndex <= 1 ? rPosition - tempIndex : i - tempIndex;
                        System.arraycopy(arr, tempIndex, arr, tempIndex + 1, shift);
                        arr[tempIndex] = temp;
                        tempIndex++;
                    }
                }
                currentPosition = tempIndex + 1;
                break;
            }

            if (rPosition == index && lPosition < index) {
                int tempNumber = arr[index];
                int tempIndex = index;
                int tempPosition = lPosition;
                while (tempIndex - tempPosition > 0) {
                    if (arr[tempPosition] > tempNumber) {
                        int temp = arr[tempPosition];
                        int shift = tempIndex - lPosition;
                        System.arraycopy(arr, tempPosition + 1, arr, tempPosition, shift);
                        arr[tempIndex] = temp;
                        tempIndex--;
                    } else {
                        tempPosition++;
                    }
                }
                currentPosition = tempPosition;
                break;
            }

            length--;
        }

        if (currentPosition > 1 && currentPosition < arr.length - 2) {
            int[] left = new int[currentPosition - 1];
            int[] right = new int[arr.length - currentPosition + 1];
        }

        System.out.println(Arrays.toString(arr));
    }
*/

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
