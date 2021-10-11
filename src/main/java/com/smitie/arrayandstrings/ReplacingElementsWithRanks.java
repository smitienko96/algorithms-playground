package com.smitie.arrayandstrings;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ReplacingElementsWithRanks {

    public static void main(String[] args) {
        int[] array = {44, 13, 34, 16, 28, 22, 39};
        System.out.println(Arrays.toString(array));
        replaceElementsWithRanks(array);
        System.out.println(Arrays.toString(array));
    }

    private ReplacingElementsWithRanks() {
        throw new AssertionError("Cannot be instantiated.");
    }

    public static void replaceElementsWithRanks(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        Map<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < array.length; i++) {
            treeMap.put(array[i], i);
        }

        int rank = 1;

        for (Map.Entry<Integer, Integer> integerIntegerEntry : treeMap.entrySet()) {
            array[integerIntegerEntry.getValue()] = rank++;
        }

    }


}
