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

        var parameters = MatrixOperations.transpose(new double[][] {
                { 0.219, 1, 0.65, 0.2 },
        });
        // 4 x 1

        var result = MatrixOperations.multiply(features, parameters);
        System.out.println(Arrays.deepToString(result));
    }
}
