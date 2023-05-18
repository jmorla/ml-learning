package com.jmorla;

import java.util.stream.IntStream;

public class MatrixUtils {
    
    public static double[] getColumn(double[][] matrix, int column) {
        return IntStream.range(0, matrix.length)
                .mapToDouble(i -> matrix[i][column]).toArray();
    }
}
