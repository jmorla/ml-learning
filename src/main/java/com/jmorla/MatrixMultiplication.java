package com.jmorla;

import java.util.Arrays;

public class MatrixMultiplication {

    public static void main(String[] args) {
        var features = new double[][] {
                { 2104, 5, 1, 45 },
                { 1416, 3, 2, 40 },
                { 1534, 3, 2, 30 },
                { 852, 2, 1, 36 }
                // 3 x 4
        };

        var parameters = new double[][] {
                { 0.219, 1, 0.65, 0.2 },
        };
        // 4 x 1

        // using utility class
        var parametersT = MatrixOperations.transpose(parameters);
        var result = MatrixOperations.multiply(features, parametersT);
        System.out.println("classic=" + Arrays.deepToString(result));


        // using Matrix
        var featuresMatrix = new Matrix(features);
        var paramMatrix = new Matrix(parameters);

        featuresMatrix.multiply(paramMatrix.transpose());
        System.out.println("new=" + Arrays.deepToString(result));
    }
}
