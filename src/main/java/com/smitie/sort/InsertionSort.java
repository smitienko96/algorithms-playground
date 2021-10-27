package com.smitie.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 6, 0, 10, 8, 2, 14, 12, 24, 18, 21};
//        sort(a);
        System.out.println(Arrays.toString(insertionSort(a)));
    }

    public static void sort(int[] a) {
//        a.f
    }

    public static int[] insertionSort(int[] array) {
        // Write your code here.
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key){
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
        return array;
    }


}
