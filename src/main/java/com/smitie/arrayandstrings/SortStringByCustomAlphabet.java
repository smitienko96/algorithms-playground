package com.smitie.arrayandstrings;

import java.util.stream.Collectors;

public class SortStringByCustomAlphabet {

    public static void main(String[] args) {
        String alphabet = "adgh";

        String input = "hgda";

        System.out.println(sort(input, alphabet));
    }

    private SortStringByCustomAlphabet() {
        throw new AssertionError("Cannot be instantiated.");
    }

    public static String sort(String input, String alphabet) {
        return input.chars().mapToObj(ch -> (char) ch).sorted((c1, c2) -> {
            int c1Index = alphabet.indexOf(c1);
            int c2Index = alphabet.indexOf(c2);

            return Integer.compare(c1Index, c2Index);

        }).map(String::valueOf).collect(Collectors.joining());
    }
}
