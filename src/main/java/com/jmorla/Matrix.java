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

    public Matrix inverse() {
        int n = matrix.length;
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("Matrix is not square");
        }

        double[][] augmentedMatrix = new double[n][2 * n];

        // Augment the matrix with an identity matrix
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][i + n] = 1.0;
        }

        // Apply Gaussian elimination with partial pivoting
        for (int i = 0; i < n; i++) {
            int maxRow = i;
            double maxValue = augmentedMatrix[i][i];
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(augmentedMatrix[j][i]) > Math.abs(maxValue)) {
                    maxRow = j;
                    maxValue = augmentedMatrix[j][i];
                }
            }
            swapRows(augmentedMatrix, i, maxRow);

            double pivot = augmentedMatrix[i][i];
            if (pivot == 0.0) {
                throw new IllegalArgumentException("Matrix is singular");
            }

            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];
                    for (int k = 0; k < 2 * n; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }
        }

        // Extract the inverted matrix
        double[][] invertedMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, invertedMatrix[i], 0, n);
        }

        return new Matrix(invertedMatrix);
    }

    private void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public double[][] toArray() {
        return matrix;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}