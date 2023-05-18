package com.jmorla;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    public static void main(String[] args) throws JsonProcessingException {

        double[][] parameters = { { 1.99 }, { 0 } };

        var dataset = DatasetUtils.doubleSquareDataset(10, 0.1, 1.0);

        var trainingDataset = Arrays.copyOfRange(dataset, 0, 6);

        var predictions = MatrixOperations.multiply(trainingDataset, parameters);
        var expectations = MatrixOperations.multiply(trainingDataset, new double[][] { { 0 }, { 1.0 } });

        var mapper = new ObjectMapper();

        var mse = CostFunctions.mse(predictions, expectations);

        var result = Map.of("dataset", trainingDataset, "predictions",
                predictions, "mse", mse);

        System.out.println(mapper.writeValueAsString(result));

    }

}
