package com.smitie.arrayandstrings;

import java.util.HashSet;
import java.util.Set;

public class CheckingForDuplicates {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        System.out.println(checkNaive(array));
        System.out.println(checkWithSet(array));
        System.out.println(check3(array));
    }

    private CheckingForDuplicates() {
        throw new AssertionError("Cannot be instantiated.");
    }

    public static boolean checkNaive(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j])
                    return true;
            }
        }
        return false;
    }

    public static boolean checkWithSet(int[] array) {

        Set<Integer> temp = new HashSet<>();

        for (int element : array) {
            if (!temp.add(element)) {
                return true;
            }
        }

        return false;
    }

    public static boolean check3(int[] array) {
        java.util.Arrays.sort(array);
        int prev = array[0];
        for (int i = 1; i < array.length; i++) {

            if (array[i] == prev)
                return true;

            prev = array[i];
        }
        return false;
    }


}
