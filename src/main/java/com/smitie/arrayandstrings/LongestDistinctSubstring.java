package com.smitie.arrayandstrings;

public class LongestDistinctSubstring {

    public static void main(String[] args) {

        String string = "ahfkdljahfj";
        System.out.println(longestDistinctSubstring(string));
    }

    private LongestDistinctSubstring() {
        throw new AssertionError("Cannot be instantiated.");
    }

    private static final int ASCII_CODES = 256;


    public static String longestDistinctSubstring(String string) {

        if (string == null || string.isBlank()) {
            return "";
        }

        boolean[] flagWindows = new boolean[ASCII_CODES];

        int left = 0;
        int right = 0;

        for (int wl = 0, wr = 0; wr < string.length(); wr++) {
            if (flagWindows[string.charAt(wr)]) {

                while (string.charAt(wl) != string.charAt(wr)) {
                    flagWindows[string.charAt(wl)] = false;
                    wl++;
                }

                wl++;
                
            } else {
                flagWindows[string.charAt(wr)] = true;
                if ((right - left) < (wr - wl)) {
                    left = wl;
                    right = wr;
                }
            }
        }

        return string.substring(left, right + 1);
    }
}
