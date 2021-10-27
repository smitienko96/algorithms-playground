package com.smitie.mathematics;

public class CommonMaths {

    public static void main(String[] args) {

//        System.out.println(log2(129));
        System.out.println(isPowerOfTwo(128));

        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(8));
    }

    static boolean isPowerOfTwo(int num) {

        return (num != 0) && ((num & (num - 1)) == 0);

    }

    boolean isPowerOf(int n, int b) {
        if (n > 1) {
            while (n % b == 0) {
                n /= b;
            }
        }
        return n == 1;
    }


    static int log2(int num) {
        return (int) (Math.log(num) / Math.log(2));
    }
}
