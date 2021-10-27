package com.smitie.other;

import java.util.Arrays;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCount {


    public static void main(String[] args) {

        String sentence = "java scala prime kotlin kotlin scala scala scala java kotlin";


        Map<String, Long> wordToCount = Arrays.stream(sentence.split(" ")).collect(groupingBy(identity(), counting()));

        System.out.println(wordToCount);

    }
}
