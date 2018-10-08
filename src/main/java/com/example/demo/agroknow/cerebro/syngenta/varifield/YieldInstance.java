package com.example.demo.agroknow.cerebro.syngenta.varifield;

import com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations.*;
import weka.core.Attribute;

public class YieldInstance extends VarifieldInstance {

    private Attribute Yield = new Attribute("Yield",10);


    /**
     *
     * @param soilTexture
     * @param soilType
     * @param nightTemperatureMin
     * @param dayTemperatureMax
     * @param precipitationSummary
     * @param nightAverageTemperature
     * @param dayAverageTemperature
     * @param averagePrecipitation
     * @param seeds
     */

    public YieldInstance(SoilTexture soilTexture, SoilType soilType,
                         double nightTemperatureMin, double dayTemperatureMax,
                         double precipitationSummary, double nightAverageTemperature,
                         double dayAverageTemperature, double averagePrecipitation, double seeds) {
        super(soilTexture,soilType,nightTemperatureMin,
                dayTemperatureMax,precipitationSummary,nightAverageTemperature,dayAverageTemperature,averagePrecipitation,seeds);
        attributes.add(Yield);

        createInstance();
    }


}
