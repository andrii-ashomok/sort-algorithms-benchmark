package com.example.sorting;

import java.util.Arrays;

public class BucketSort {

    public static void main(String[] args) {
        int[] arr = new int[] {4, 67, 3, 4, 78, 9, 1, 0, 55, 43, 21, 56, 78, 63, 989, 901, 100};
        sort(arr);
    }

    public static int[] sort(int[] arr) {
        Bucket bucket = new Bucket(arr.length);
        for (int value : arr) {
            int index = Character.getNumericValue(String.valueOf(value).charAt(0));
            bucket.put(index, value);
        }
        System.out.println(bucket.toString());
        int[] result = bucket.sort();
        System.out.println("Result: { " + Arrays.toString(result) + " }");
        return new int[]{};
    }

    private static class Bucket {
        private final int[][] buckets;
        private final int[] counters;
        private final int total = 10;
        private final int length;
        private int zeroInsertion = 0;

        private Bucket(int length) {
            this.length = length;
            this.buckets = new int[total][length];
            this.counters = new int[total];
        }

        private void put(int index, int number) {
            if (index == 0) {
                zeroInsertion++;
            } else {
                int count = counters[index];
                buckets[index][count] = number;
                counters[index] += 1;
            }
        }

        private int[] sort() {
            int[] result = new int[length];
            int position = 0;
            if (zeroInsertion > 0) {
                while (zeroInsertion-- != 0) {
                    result[position] = 0;
                    position++;
                }
            }

            for (int x = 1; x < total; x++) {
                if (buckets[x][0] == 0) {
                    break;
                }
                int[] temp = buckets[x];
                int[] arr = removeZero(temp);
                InsertionSort.sort(arr);
                System.arraycopy(arr, 0, result, position, arr.length);
                position += arr.length;
            }

            return result;
        }

        private int[] removeZero(int[] arr) {
            int count = 0;
            for (int numb : arr) {
                if (numb == 0) {
                    break;
                } else {
                    count++;
                }
            }

            int[] temp = new int[count];
            System.arraycopy(arr, 0, temp, 0, count);
            return temp;
        }

        private int[][] getBuckets() {
            return buckets;
        }

        @Override
        public String toString() {
            return new StringBuffer("Buckets: { ")
                    .append(Arrays.deepToString(buckets))
                    .append(" }")
                    .toString();
        }
    }

}
