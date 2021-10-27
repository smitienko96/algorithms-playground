package com.smitie.mathematics;

import java.util.*;
import java.util.stream.Collectors;

public class SetsIntersection {

    public static void main(String[] args) {
        int[] a = {5, 1, 2, 7, 3, 2};
        int[] b = {1, 2, 3, 2, 0};

        long start0 =  System.nanoTime();
        System.out.println(Arrays.toString(intersection(a, b)));
        System.out.println(System.nanoTime() - start0);
        long start1 =  System.nanoTime();
        System.out.println(Arrays.toString(intersection1(a, b)));
        System.out.println(System.nanoTime() - start1);
    }

    static int[] intersection(int[] a, int[] b) {

        List<Integer> res = new ArrayList<>();

        for (int j : a) {
            if (containsInArray(j, b)) {
                res.add(j);
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    static int[] intersection1(int[] a, int[] b) {

        HashMap<Integer, Boolean> cache = new HashMap<>();

        List<Integer> res = new ArrayList<>();

        for (int j : a) {
            if (cache.containsKey(j))
                res.add(j);
            else if (containsInArray(j, b)) {
                cache.put(j, true);
                res.add(j);
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    public String reverseWords(String s) {

        String strArray[] = s.split("\n", Integer.MAX_VALUE);

        String[] arr = s.split(" ");

        List<String> asList = Arrays.asList(arr);

        Collections.reverse(asList);

        return String.join(" ", asList);

    }

    static boolean containsInArray(int num, int[] arr) {
        for (int i : arr) {
            if (i == num)
                return true;
        }
        return false;
    }

}
