package com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate;

import java.util.Comparator;

public class PredictionSeedComparator implements Comparator<PredictionSeed> {

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(PredictionSeed o1, PredictionSeed o2) {

        if (o1.getPrediction() < o2.getPrediction()) return -1;
        if (o1.getPrediction() > o2.getPrediction()) return 1;
        return 0;

    }
}
