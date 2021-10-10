package com.smitie.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 6, 0, 10, 8, 2, 14, 12, 24, 18, 21};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && key < a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

}
