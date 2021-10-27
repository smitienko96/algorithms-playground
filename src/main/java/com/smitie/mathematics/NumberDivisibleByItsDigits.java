package com.smitie.mathematics;

public class NumberDivisibleByItsDigits {


    public static void main(String[] args) {
        int n = 4123;

        System.out.println(test(n));
    }


    public static boolean test(int n) {
        int t = n;

        while (t > 0) {
            int k = t % 10;
            if (k > 0 && n % k != 0)
                return false;
            t /= 10;
        }
        return true;
    }


}
