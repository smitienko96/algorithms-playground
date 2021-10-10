package com.smitie.arrayandstrings;

public final class MatrixUtils {

    private MatrixUtils() {
        throw new AssertionError();
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%5d", anInt);
            }
            System.out.println();
        }
    }

    public static void printRows(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j]);
            }
            System.out.println();
        }
    }

    public static void printColumns(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int[] ints : matrix) {
                System.out.print(ints[i]);
            }
            System.out.println();
        }
    }

}
