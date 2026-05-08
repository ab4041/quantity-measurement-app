package com.quantity.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private double result;

    public QuantityMeasurementEntity(
            String operation,
            double result) {

        this.operation = operation;
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }
}