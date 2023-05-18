package com.jmorla.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmorla.CostFunctions;
import com.jmorla.DatasetUtils;
import com.jmorla.Matrix;

public class LearnMultiply {

    public static void main(String[] args) throws JsonProcessingException {
        var params = new Matrix(new double[][] { { 2, 0 } }).transpose();
        var dataset = new Matrix(DatasetUtils.doubleSquareDataset(10, 0.1, 1));

        var expectations = dataset.multiply(new Matrix(new double[][] { { 0, 1 } }).transpose());

        var predictions = dataset.multiply(params);

        var mse = CostFunctions.mse(predictions.toArray(), expectations.toArray());

        var response = Map.of(
                "mse", mse,
                "predictions", predictions.toArray(),
                "dataset", dataset.toArray());

        System.out.println(new ObjectMapper().writeValueAsString(response));
    }
}
