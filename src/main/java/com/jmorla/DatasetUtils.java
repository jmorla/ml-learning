package com.jmorla;

import java.util.Random;

public final class DatasetUtils {

    public static double[][] doubleSquareDataset(int size, double start, double end) {
        double[][] dataset = new double[size][2];
        var r = new Random();
        for (int i = 0; i < size; i++) {
            var x = r.nextDouble(start, end);
            dataset[i][0] = x;
            dataset[i][1] = x * 2;
        }

        return dataset;
    }
    
}
