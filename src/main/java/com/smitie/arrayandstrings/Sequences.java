package com.smitie.arrayandstrings;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Sequences {

    public static void main(String[] args) {
        int[] array = {4, 2, 9, 5, 12, 6, 8};
        System.out.println(calculateLongestSequential(array));
    }

    private Sequences() {
        throw new AssertionError("Cannot be instantiated.");
    }

    /**
     * Problem: Consider that you've been given an array of integers.
     * Write a snippet of code that finds the longest sequence of integers.
     * Notice that a sequence contains only consecutive distinct elements.
     * The order of the elements in the given array is not important.
     *
     * @param array
     * @return
     */
    public static int calculateLongestSequential(int[] array) {

        Set<Integer> inputAsSet = Arrays
                .stream(array)
                .boxed()
                .collect(toSet());

        int longestSequenceLength = 1;

        for (Integer element : inputAsSet) {
            if (!inputAsSet.contains(element - 1)) {

                int currentSequenceLength = 1;

                while (inputAsSet.contains(element + currentSequenceLength)) {
                    currentSequenceLength++;
                }

                longestSequenceLength = Math.max(longestSequenceLength, currentSequenceLength);
            }
        }

        return longestSequenceLength;
    }

}
