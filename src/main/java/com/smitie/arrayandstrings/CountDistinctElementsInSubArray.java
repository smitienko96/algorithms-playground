package com.smitie.arrayandstrings;

import java.util.HashMap;
import java.util.Map;

public class CountDistinctElementsInSubArray {

    private CountDistinctElementsInSubArray() {
        throw new AssertionError("Cannot be instantiated.");
    }

    public static void count(int[] m , int n){
        if (m == null){
            throw new IllegalArgumentException("The given array cannot be null");
        }

        if (n <= 0){
            throw new IllegalArgumentException("The given sub-array size cannot be <= 0.");
        }

        Map<Integer, Integer> frequencies = new HashMap<>();

        int countDistinct = 0;
        for (int i = 0; i < m.length; i++) {
            if (i >= n){
                frequencies.putIfAbsent(m[i -n], 0);
                frequencies.compute(m[i - n], (k, v) -> v != null ? v - 1 : 0);
            }
        }
    }


}
