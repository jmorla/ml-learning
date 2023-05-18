package com.jmorla;

import java.util.Arrays;

public final class Matrix {
    double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix transpose() {
        var transposed = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("Invalid matrix");
            }
            for (int j = 0; j < matrix[i].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return new Matrix(transposed);
    }

    public Matrix multiply(Matrix other) {
        int rowsA = matrix.length;
        int colsA = matrix[0].length;
        int rowsB = other.matrix.length;
        int colsB = other.matrix[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Incompatible matrix sizes for multiplication");
        }

        double[][] resultMatrix = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                double sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrix[i][k] * other.matrix[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }

        return new Matrix(resultMatrix);
    }

    public double[][] toArray() {
        return matrix;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}