package com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations;

public enum SoilType {

    CALCAREOUSCLAYLOAM("CalcareousClayLoam"),
    CALCAREOUSLOAMY("CalcareousLoamy"),
    CLAYLOAM("ClayLoam"),
    LOAM("Loam"),
    LOAMYCLAY("LoamyClay"),
    LOAMYSAND("LoamySand"),
    SANDYCLAYLOAM("SandyClayLoam"),
    SANDYLOAM("SandyLoam"),
    SILTLOAM("SiltLoam"),
    SILTYCLAYLOAM("SiltyClayLoam"),
    SANDYSILTLOAM("SandySiltLoam"),
    ;

    private String soilType;

    SoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }
}
























