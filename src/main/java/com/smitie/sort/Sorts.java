package com.smitie.sort;

import java.util.Arrays;

public class Sorts {

    public static void main(String[] args) {
        int[] array = {32, 4, 5, 6,2, 9, 8, 19};

        bubbleSort(array);

        System.out.println(Arrays.toString(array));
    }
    
    static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
}
