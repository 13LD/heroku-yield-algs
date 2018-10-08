package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum PredictionTarget {
    YIELD("Yield"),EARS("Ears");

    private String predictionTarget;

    PredictionTarget(String predictionTarget) {
        this.predictionTarget = predictionTarget;
    }

    public String getPredictionTarget() {
        return predictionTarget;
    }

    public void setPredictionTarget(String predictionTarget) {
        this.predictionTarget = predictionTarget;
    }
}
