package com.example.demo.agroknow.cerebro.wekabox;

import weka.classifiers.misc.SerializedClassifier;
import weka.core.Instance;

public class LibSVMWekaModel extends WekaModel {

    private Instance instance;
    private int capacity;

    /**
     *
     * @param modelPath
     * @param instance
     * @param capacity
     */

    public LibSVMWekaModel(String modelPath, Instance instance, int capacity) {
        super(modelPath);
        this.instance = instance;
        this.capacity = capacity;
    }

    /**
     *
     * @throws Exception
     */

    public void train() throws Exception {
        throw(new Exception("Not Implemented"));
    }


    /**
     *
     * @return
     */
    public double predict(){
        this.loadModel();
        double prediction = Double.NaN;
        SerializedClassifier model = this.getModel();

        try {
            prediction = model.classifyInstance(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prediction;
    }
}
