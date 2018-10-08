package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum Classification {

    CONVENTIONAL("Conventional"),
    HYBRID("Hybrid"),
    ;

    private String classification;

    Classification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
























