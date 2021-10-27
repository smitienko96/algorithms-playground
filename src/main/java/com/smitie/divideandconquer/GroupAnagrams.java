package com.smitie.divideandconquer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] words = {
                "calipers", "caret", "slat", "cater", "thickset",
                "spiracle", "trace", "last", "salt", "bowel", "crate",
                "loop", "polo", "thickest", "below", "thickets",
                "pool", "elbow", "replicas"
        };


        System.out.println(group(words));
    }

    static Map<String, List<String>> group(String[] anagrams) {

        return Arrays
                .stream(anagrams)
                .map(str -> new Pair<>(sortedString(str), str))
                .collect(groupingBy(pair -> pair.first, mapping(pair -> pair.second, toList())));
    }


    static String sortedString(String str) {
        return str.chars().mapToObj(c -> (char) c).sorted().map(String::valueOf).collect(joining());
    }


    static class Pair<P, Q> {
        P first;
        Q second;

        public Pair(P first, Q second) {
            this.first = first;
            this.second = second;
        }
    }

}
