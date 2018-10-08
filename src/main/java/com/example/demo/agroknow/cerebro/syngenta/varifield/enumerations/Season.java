package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum Season {

    WINTER("Winter"),
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    ;


    private String season;

    Season(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
























