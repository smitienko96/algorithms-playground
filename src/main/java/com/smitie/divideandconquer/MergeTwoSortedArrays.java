package com.smitie.divideandconquer;

import java.util.Arrays;

public class MergeTwoSortedArrays {

    public static void main(String[] args) {

        int[] p = {-3, -2, 0, 3, 8, 12, 16, 17, 24, 39, 0, 0, 0, 0, 0, 0};
        int[] q = {-4, -2, -1, 1, 4, 36};

        merge(p, q);

        System.out.println(Arrays.toString(p));

    }




    static void merge(int[] p, int[] q) {
        int pLast = p.length - q.length;
        int qLast = q.length;


        int pIdx = pLast - 1;
        int qIdx = qLast - 1;
        int midIdx = pLast + qLast - 1;

        while (qIdx >= 0) {
            if (pIdx >= 0 && p[pIdx] > q[qIdx]) {
                p[midIdx] = p[pIdx];
                pIdx--;
            } else {
                p[midIdx] = q[qIdx];
                qIdx--;
            }
            midIdx--;
        }


    }
}
