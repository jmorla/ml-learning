package com.jmorla.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmorla.DatasetUtils;
import com.jmorla.Matrix;

public class LearnMultiply {

    public static void main(String[] args) {

        var trainingDataset = new Matrix(DatasetUtils.doubleSquareDataset(10, 0, 1));

        // anulates x's column multiplying it by 0
        var squares = trainingDataset.multiply(new Matrix(new double[][] { { 0, 1 } }).transpose());
        // anulates square's column multiplying it by 0
        var design = trainingDataset.multiply(new Matrix(new double[][] { { 1, 0 } }).transpose());

        // calculating optimal parameter using normal equation
        // P = (Xt * X)^-1 * Xt * Y
        var params = design.transpose()
                .multiply(design)
                .inverse()
                .multiply(design.transpose())
                .multiply(squares);

        var testingDataset = new Matrix(DatasetUtils.doubleSquareDataset(5, 0, 5));
        var testInputs = testingDataset.multiply(new Matrix(new double[][] { { 1 }, { 0 } }));

        // predict the squares
        var predictions = testInputs.multiply(params);

        printAsJson(Map.of(
                "dataset", testingDataset.toArray(),
                "predictions", predictions.toArray(),
                "param", params.toArray()));

    }

    private static void printAsJson(Map<String, Object> map) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var str = mapper.writeValueAsString(map);
            System.out.println(str);
        } catch (JsonProcessingException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
