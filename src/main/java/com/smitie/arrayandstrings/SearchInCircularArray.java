package com.smitie.arrayandstrings;

public class SearchInCircularArray {

    public static void main(String[] args) {
        int[] array = {11, 14, 23, 24, -1, 3, 5, 6, 8, 9, 10};
        System.out.println(find(array, 14));
    }

    /**
     * Problem: Consider that you've been given a circularly sorted array of integers with no duplicates, m.
     * Write a program that searches for the given x in O(log n) complexity time.
     *
     * @param array input array
     * @param num   number to be found
     * @return index of the found number or -1 in case it's not in the array
     */
    public static int find(int[] array, int num) {

        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {

            int middle = (left + right) / 2;

            if (num == array[middle]) {
                return middle;
            }

            if (array[middle] <= array[right]) {
                if (num > array[middle] && num <= array[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                if (num >= array[left] && num < array[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return -1;
    }
}
