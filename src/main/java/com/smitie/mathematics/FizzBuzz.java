package com.smitie.mathematics;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {

    public static void main(String[] args) {

        generate(500);
    }

    static void generate(int n) {

        Function<Integer, String> toFizzBuzz = i -> {
            if (i % 5 == 0 && i % 7 == 0) {
                return "fizzbuzz";
            } else if (i % 5 == 0) {
                return "fizz";
            } else if (i % 7 == 0) {
                return "buzz";
            } else return String.valueOf(i);
        };


        IntStream.rangeClosed(1, n)
                .boxed()
                .map(toFizzBuzz)
                .forEach(System.out::println);

    }
}
