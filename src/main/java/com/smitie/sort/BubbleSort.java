package com.smitie.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 6, 0, 10, 8, 2, 14, 12, 24, 18, 21};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - 1; j++) {
                if (a[j - 1] > a[j])
                    swap(a, j, j - 1);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        a[i] = a[i] + a[j];
        a[j] = a[i] - a[j];
        a[i] = a[i] - a[j];
    }

}
