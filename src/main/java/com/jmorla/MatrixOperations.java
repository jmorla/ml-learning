package com.jmorla;


public final class MatrixOperations {

    public static double[][] transpose(double[][] matrix) {
        var trasposed = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("Invalid matrix");
            }
            for (int j = 0; j < matrix[i].length; j++) {
                trasposed[j][i] = matrix[i][j];
            }
        }
        return trasposed;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("cannot multiply matrises");
        }
        var C = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    C[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return C;
    }

}
