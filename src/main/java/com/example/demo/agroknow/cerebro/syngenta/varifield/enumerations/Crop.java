package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum Crop {
    BARLEY("Barley"),WHEAT("Wheat");

    private String crop;

    Crop(String crop) {
        this.crop = crop;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }
}
