package com.smitie.arrayandstrings;

import java.util.Arrays;

public class CountScore {


    public static void main(String[] args) {

        System.out.println(count(33));
    }

    private CountScore() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static int count(int score) {
        int[] table = new int[score + 1];

        table[0] = 1;

        for (int i = 3; i <= score; i++) {
            table[i] += table[i - 3];
        }

        for (int i = 5; i <= score; i++) {
            table[i] += table[i - 5];
        }

        for (int i = 10; i <= score; i++) {
            table[i] += table[i - 10];
        }

        System.out.println(Arrays.toString(table));

        return table[score];
    }
}
