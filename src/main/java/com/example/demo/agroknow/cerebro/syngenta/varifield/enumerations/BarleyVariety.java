package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum BarleyVariety {

    BAZOOKA("Bazooka"),
    BELFRY("Belfry"),
    FLETCHER("Fletcher"),
    VOLUME("Volume"),
    CELOONA("Celoona")
    ;


    private String barleyVariety;

    BarleyVariety(String barleyVariety) {
        this.barleyVariety = barleyVariety;
    }

    public String getBarleyVariety() {
        return barleyVariety;
    }

    public void setBarleyVariety(String barleyVariety) {
        this.barleyVariety = barleyVariety;
    }
}
























