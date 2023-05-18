package com.jmorla;

public final class CostFunctions {
    
    public static double mse(double[][] predicted, double[][] expected) {
        if (predicted.length != expected.length || predicted[0].length != expected[0].length) {
            throw new IllegalArgumentException("Arrays must have same size");
        }

        double error = 0.0;
        for (int i = 0; i < expected.length; i++) {
            error = Math.pow(predicted[i][0] - expected[i][0], 2);
        }

        return error / expected.length;
    }
}
