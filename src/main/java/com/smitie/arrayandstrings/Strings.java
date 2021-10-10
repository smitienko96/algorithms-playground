package com.smitie.arrayandstrings;

import java.util.*;

import static com.smitie.arrayandstrings.MatrixUtils.printMatrix;

public class Strings {


    public static void main(String[] args) {

        String test = "dada";
//        System.out.println(isUnique0(test));
//        System.out.println(isUnique1(test));
//        System.out.println(isUnique2(test));
//        System.out.println(isUnique3(test));
//        System.out.println(isOneEditAway("doc", "doc"));
//        System.out.println(shrink("abbb vvvv s rttt rr eeee f"));

//        System.out.println(extract("4 k 2321 2 11 k4k2 66 4d"));

        int[][] matrix = new int[4][4];

        Random rnd = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = rnd.nextInt(10);
            }
        }

        printMatrix(matrix);

        rotateWithTranspose(matrix);

        System.out.println("========AFTER ROTATE============");

        printMatrix(matrix);
//
//        printColumns(matrix);

    }


    private static final int MAX_CODE = 65535;
    private static final char A_CHAR = 'a';

    public static boolean isUnique0(String input) {

        Map<Character, Boolean> cache = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.codePointAt(i) <= MAX_CODE) {
                char ch = input.charAt(i);
                if (!Character.isWhitespace(ch)) {
                    if (cache.put(ch, true) != null) {
                        return false;
                    }
                }
            } else {
                System.out.println("The given string contains unallowed characters");
                return false;
            }
        }
        return true;
    }

    public static boolean isUnique1(String input) {
        return input.chars().mapToObj(c -> (char) c).distinct().count() == input.length();
    }

    public static boolean isUnique2(String input) {

        Set<Character> chars = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!chars.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUnique3(String input) {
        int marker = 0;
        for (int i = 0; i < input.length(); i++) {
            System.out.println("marker: " + marker);
            int s = input.charAt(i) - A_CHAR;
            System.out.println("s: " + s);
            int mask = 1 << s;
            System.out.println("mask: " + mask);
            if ((marker & mask) > 0)
                return false;

            marker = marker | mask;
        }
        return true;
    }

    /**
     * One edit way.
     * Problem: Consider two given strings, q and p.
     * Write a snippet of code that determines whether we can obtain two identical strings by performing a single edit in q or p.
     * More precisely, we can insert, remove, or replace a single character in q or in p, and q will become equal to p.
     */
    public static boolean isOneEditAway(String p, String q) {

        if (Math.abs(p.length() - q.length()) > 1)
            return false;

        String shorter = p.length() > q.length() ? q : p;
        String longer = q.length() > p.length() ? q : p;

        int is = 0;
        int il = 0;

        boolean marker = false;
        while (is < shorter.length() && il < longer.length()) {
            if (shorter.charAt(is) != longer.charAt(il)) {
                if (marker) {
                    return false;
                }
                marker = true;
                if (shorter.length() == longer.length()) {
                    is++;
                }
            } else {
                is++;
            }
            il++;
        }
        return true;
    }


    /**
     * Problem: Consider a given string containing only letters a-z and whitespaces.
     * This string contains a lot of consecutive repeated characters.
     * Write a snippet of code that shrinks this string by counting the consecutive repeated characters and creating another string that appends each character and the number of consecutive occurrences.
     * The whitespaces should be copied in the resulting string as they are (don't shrink the whitespaces).
     * If the resulting string is not shorter than the given string, then return the given string.
     *
     * @param string
     * @return
     */
    public static String shrink(String string) {
        StringBuilder resultBuilder = new StringBuilder();

        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            count++;
            char current = string.charAt(i);
            if (!Character.isWhitespace(current)) {
                if ((i + 1) >= string.length() || current != string.charAt(i + 1)) {
                    resultBuilder.append(current).append(count);
                    count = 0;
                }
            } else {
                resultBuilder.append(current);
                count = 0;
            }
        }

        return resultBuilder.length() > string.length() ? string : resultBuilder.toString();
    }

    /**
     * Problem: Consider a given string containing whitespaces and a-z and 0-9 characters.
     * Write a snippet of code that extracts integers from this string.
     * You can assume that any sequence of consecutive digits forms a valid integer.
     *
     * @param string
     * @return
     */
    public static List<Integer> extract(String string) {
        List<Integer> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char current = string.charAt(i);
            if (Character.isDigit(current)) {
                temp.append(current);
            } else {
                if (!temp.isEmpty()) {
                    result.add(Integer.parseInt(temp.toString()));
                    temp.delete(0, temp.length());
                }
            }
        }
        return result;
    }

    /**
     * Consider a given n x n matrix of integers, M.
     * Write a snippet of code that rotates this matrix by 90 degrees in a counterclockwise direction without using any extra space.
     *
     * @param m - input matrix
     */
    private static void transpose(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
    }

    public static void rotateWithTranspose(int[][] m) {
        if (m == null || m.length == 0) {
            throw new IllegalArgumentException("The given matrix cannot be null or empty");
        }

        if (m.length != m[0].length) {
            throw new IllegalArgumentException("The given matrix must be of type nxn");
        }

        transpose(m);

        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0, k = m[0].length - 1; j < k; j++, k--) {
                int tmp = m[j][i];
                m[j][i] = m[k][i];
                m[k][i] = tmp;
            }
        }
    }





}
