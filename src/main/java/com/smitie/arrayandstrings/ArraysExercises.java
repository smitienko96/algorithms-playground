package com.smitie.arrayandstrings;

import static com.smitie.arrayandstrings.MatrixUtils.printMatrix;

public class ArraysExercises {

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 4, 0, 9}, {6, 0, 8, 1, 4}, {2, 3, 5, 9, 4}, {1, 7, 2, 4, 5}, {7, 9, 6, 2, 3}};

        printMatrix(matrix);

        System.out.println("===== AFTER ALIGN ===== ");
        alignZeros(matrix);
        printMatrix(matrix);
    }

    private ArraysExercises() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static void alignZeros(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("The given matrix cannot be null or empty");
        }

        boolean firstRowHasZeros = false;
        boolean firstColumnHasZeros = false;

        // search at least a zero on first row
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowHasZeros = true;
                break;
            }
        }

        // search at least a zero on first column
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                firstColumnHasZeros = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // loop first column and propagate each found zero on the row
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                setRowOfZero(matrix, i);
            }
        }

        // loop the first row and propagate each found zero on the column
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                setColumnOfZero(matrix, i);
            }
        }

        if (firstRowHasZeros) {
            setColumnOfZero(matrix, 0);
        }

        if (firstColumnHasZeros) {
            setRowOfZero(matrix, 0);
        }

    }

    private static void setRowOfZero(int[][] matrix, int i) {
        java.util.Arrays.fill(matrix[i], 0);
    }

    private static void setColumnOfZero(int[][] matrix, int i) {
        for (int j = 0; j < matrix.length; j++) {
            matrix[j][i] = 0;
        }
    }
}
