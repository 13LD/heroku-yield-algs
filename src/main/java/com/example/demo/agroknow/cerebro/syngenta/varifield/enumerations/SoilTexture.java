package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum SoilTexture {
    LIGHT("Light"),MEDIUM("Medium"),HEAVY("Heavy");

    private String soilTexture;

    SoilTexture(String soilTexture) {
        this.soilTexture = soilTexture;
    }

    public String getSoilTexture() {
        return soilTexture;
    }

    public void setSoilTexture(String soilTexture) {
        this.soilTexture = soilTexture;
    }
}
