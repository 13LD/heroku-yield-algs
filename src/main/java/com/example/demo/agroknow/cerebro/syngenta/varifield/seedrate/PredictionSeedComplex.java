package com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate;

public class PredictionSeedComplex extends PredictionSeed{

    protected double earsPrediction;

    /**
     *
     * @param seedRate
     * @param prediction
     * @param earsPrediction
     */
    public PredictionSeedComplex(int seedRate, double prediction,double earsPrediction) {
        super(seedRate,prediction);
        this.earsPrediction = earsPrediction;
    }

    public double getEarsPrediction() {
        return earsPrediction;
    }

    public void setEarsPrediction(double earsPrediction) {
        this.earsPrediction = earsPrediction;
    }
}
