package com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate;

public class PredictionSeed {

    protected int seedRate;
    protected double prediction;

    /**
     *
     * @param seedRate
     * @param prediction
     */
    public PredictionSeed(int seedRate, double prediction) {
        this.seedRate = seedRate;
        this.prediction = prediction;
    }

    public int getSeedRate() {
        return seedRate;
    }

    public void setSeedRate(int seedRate) {
        this.seedRate = seedRate;
    }

    public double getPrediction() {
        return prediction;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }
}
