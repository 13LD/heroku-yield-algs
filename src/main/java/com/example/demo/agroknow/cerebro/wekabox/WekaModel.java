package com.example.demo.agroknow.cerebro.wekabox;

import weka.classifiers.misc.SerializedClassifier;

import java.io.File;

public class WekaModel {


    private String modelPath = "";
    private SerializedClassifier model = new SerializedClassifier();


    /**
     *
     * @param modelPath
     */
    public WekaModel(String modelPath) {
        this.modelPath = modelPath;
    }

    /**
     *
     */
    protected void loadModel(){
        model.setModelFile(new File(modelPath));
    }

    /**
     *
     * @return
     */
    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    /**
     *
     * @return
     */
    public SerializedClassifier getModel() {
        return model;
    }

    public void setModel(SerializedClassifier model) {
        this.model = model;
    }
}
