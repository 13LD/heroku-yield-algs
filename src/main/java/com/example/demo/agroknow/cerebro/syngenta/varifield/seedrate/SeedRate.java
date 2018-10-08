package com.example.demo.agroknow.cerebro.syngenta.varifield.seedrate;

import com.example.demo.agroknow.cerebro.syngenta.varifield.EarInstance;
import com.example.demo.agroknow.cerebro.syngenta.varifield.YieldInstance;
import com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations.*;
import com.example.demo.agroknow.cerebro.syngenta.varifield.exceptions.CropNotAvailableException;
import com.example.demo.agroknow.cerebro.syngenta.varifield.exceptions.NoValidModelException;
import com.example.demo.agroknow.cerebro.wekabox.LibSVMWekaModel;


import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

public class SeedRate {

    final private int minimumSeedRate = 50;
    final private int maximumSeedRate = 851;

    final private int minimumAcceptableEars;
    final private int maximumAcceptableEars;

    private Crop crop;
    private SoilTexture soilTextureValue;
    private SoilType soilTypeValue;
    private double nightTemperatureMin;
    private double dayTemperatureMax;
    private double precipitationSummary;
    private double nightAverageTemperature;
    private double dayAverageTemperature;
    private double averagePrecipitation;

    private PredictionTarget predictionTarget;
    private PredictionSeed optimumPrediction;

    /**
     *
     * @param crop
     * @param soilTextureValue
     * @param soilTypeValue
     * @param nightTemperatureMin
     * @param dayTemperatureMax
     * @param precipitationSummary
     * @param nightAverageTemperature
     * @param dayAverageTemperature
     * @param averagePrecipitation
     * @param predictionTarget
     * @throws CropNotAvailableException
     */

    public SeedRate(Crop crop,SoilTexture soilTextureValue, SoilType soilTypeValue,
                    double nightTemperatureMin, double dayTemperatureMax,
                    double precipitationSummary, double nightAverageTemperature,
                    double dayAverageTemperature, double averagePrecipitation,
                    PredictionTarget predictionTarget) throws CropNotAvailableException {

        this.crop = crop;
        this.soilTextureValue = soilTextureValue;
        this.soilTypeValue = soilTypeValue;
        this.nightTemperatureMin = nightTemperatureMin;
        this.dayTemperatureMax = dayTemperatureMax;
        this.precipitationSummary = precipitationSummary;
        this.nightAverageTemperature = nightAverageTemperature;
        this.dayAverageTemperature = dayAverageTemperature;
        this.averagePrecipitation = averagePrecipitation;
        this.predictionTarget = predictionTarget;


        this.minimumAcceptableEars = 150;
        this.maximumAcceptableEars = 350;

    }

    /**
     *
     * @param crop
     * @param soilTextureValue
     * @param soilTypeValue
     * @param nightTemperatureMin
     * @param dayTemperatureMax
     * @param precipitationSummary
     * @param nightAverageTemperature
     * @param dayAverageTemperature
     * @param averagePrecipitation
     * @param predictionTarget
     * @param minimumAcceptableEars
     * @param maximumAcceptableEars
     * @throws CropNotAvailableException
     */


    public SeedRate(Crop crop, SoilTexture soilTextureValue, SoilType soilTypeValue,
                    double nightTemperatureMin, double dayTemperatureMax,
                    double precipitationSummary, double nightAverageTemperature,
                    double dayAverageTemperature, double averagePrecipitation,
                    PredictionTarget predictionTarget, int minimumAcceptableEars, int maximumAcceptableEars) throws CropNotAvailableException {
        this.crop = crop;
        this.soilTextureValue = soilTextureValue;
        this.soilTypeValue = soilTypeValue;
        this.nightTemperatureMin = nightTemperatureMin;
        this.dayTemperatureMax = dayTemperatureMax;
        this.precipitationSummary = precipitationSummary;
        this.nightAverageTemperature = nightAverageTemperature;
        this.dayAverageTemperature = dayAverageTemperature;
        this.averagePrecipitation = averagePrecipitation;
        this.predictionTarget = predictionTarget;

        this.minimumAcceptableEars = minimumAcceptableEars;
        this.maximumAcceptableEars = maximumAcceptableEars;

    }

    /**
     *
     * @throws CropNotAvailableException
     * @throws NoValidModelException
     * @throws URISyntaxException
     */
    public void findOptimumSeedRate() throws CropNotAvailableException, NoValidModelException, URISyntaxException {

        String selectedCrop = crop.getCrop();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        switch(selectedCrop){
            case "Barley":
                if(predictionTarget.getPredictionTarget().equalsIgnoreCase("Yield")){
                    String modelPathYield = Paths.get(classLoader.getResource("barley_yield.model").toURI()).toString();
                    optimumPrediction = optimumSeedByYield(modelPathYield);
                }else if(predictionTarget.getPredictionTarget().equalsIgnoreCase("Ears")){
                    String modelPathEars = Paths.get(classLoader.getResource("barley_ears.model").toURI()).toString();
                    String modelPathYield = Paths.get(classLoader.getResource("barley_yield.model").toURI()).toString();
                    optimumPrediction = optimumSeedByEars(modelPathEars,modelPathYield);

                    System.out.println(optimumPrediction.getSeedRate()+"-"+optimumPrediction.getPrediction()+"-"+((PredictionSeedComplex)optimumPrediction).getEarsPrediction());

                }else{
                    throw new NoValidModelException("Model Not Available: " + predictionTarget.getPredictionTarget());
                }
                break;
            case "Wheat":
                if(predictionTarget.getPredictionTarget().equalsIgnoreCase("Yield")){
                    String modelPathYield = Paths.get(classLoader.getResource("wheat_yield.model").toURI()).toString();
                    optimumPrediction = optimumSeedByYield(modelPathYield);
                }else if(predictionTarget.getPredictionTarget().equalsIgnoreCase("Ears")){
                    String modelPathEars = Paths.get(classLoader.getResource("wheat_ears.model").toURI()).toString();
                    String modelPathYield = Paths.get(classLoader.getResource("wheat_yield.model").toURI()).toString();
                    optimumPrediction = optimumSeedByEars(modelPathEars,modelPathYield);
                }else{
                    throw new NoValidModelException("Model Not Available: " +predictionTarget.getPredictionTarget());
                }
                break;
            default:
                throw new CropNotAvailableException("Unknown Crop:  " + selectedCrop);
        }
    }

    /**
     *
     * @param modelPath
     * @return
     */
    private PredictionSeed optimumSeedByYield(String modelPath){

        ArrayList<PredictionSeed> psList = new ArrayList<>();

        for(int i = this.minimumSeedRate;i < this.maximumSeedRate;i++){

            YieldInstance yi = new YieldInstance(soilTextureValue, soilTypeValue,
                        nightTemperatureMin, dayTemperatureMax,
                        precipitationSummary, nightAverageTemperature,
                        dayAverageTemperature, averagePrecipitation, i);

            LibSVMWekaModel wm = new LibSVMWekaModel(modelPath,yi.getInstance(),1);
            double yield = wm.predict();

            //System.out.println(i+";"+yield);

            if(yield <= 1){
                yield = 0;
            }

            PredictionSeed ps = new PredictionSeed(i,yield);
            psList.add(ps);
        }

        Collections.sort(psList,new PredictionSeedComparator());
        return psList.get(psList.size()-1);


    }



    private PredictionSeedComplex optimumSeedByEars(String modelPathEars,String modelPathYield){

        ArrayList<PredictionSeed> psList = new ArrayList<>();

        for(int i = this.minimumSeedRate;i < this.maximumSeedRate;i++){
            EarInstance ei = new EarInstance(soilTextureValue, soilTypeValue,
                    nightTemperatureMin, dayTemperatureMax,
                    precipitationSummary, nightAverageTemperature,
                    dayAverageTemperature, averagePrecipitation, i);


            LibSVMWekaModel wm = new LibSVMWekaModel(modelPathEars,ei.getInstance(),1);
            double ears = wm.predict();

            //System.out.println(i+";"+ears);
            if((ears>=this.minimumAcceptableEars) && (ears<=this.maximumAcceptableEars)){

                PredictionSeed ps = new PredictionSeed(i,ears);
                psList.add(ps);
            }
        }


        Iterator<PredictionSeed> itPsList = psList.iterator();

        ArrayList<PredictionSeedComplex> psListFinal = new ArrayList<>();
        while(itPsList.hasNext()){

            PredictionSeed psEars = itPsList.next();
            double seedRate = psEars.getSeedRate();
            double earsPrediction = psEars.getPrediction();

            YieldInstance yi = yi = new YieldInstance(soilTextureValue, soilTypeValue,
                    nightTemperatureMin, dayTemperatureMax,
                    precipitationSummary, nightAverageTemperature,
                    dayAverageTemperature, averagePrecipitation, seedRate);


            LibSVMWekaModel wm = new LibSVMWekaModel(modelPathYield,yi.getInstance(),1);
            double yield = wm.predict();

            PredictionSeedComplex ps = new PredictionSeedComplex((int)seedRate,yield,earsPrediction);
            psListFinal.add(ps);

        }

        if(psListFinal.size() > 0){
            Collections.sort(psListFinal,new PredictionSeedComparator());
        }else {
            PredictionSeedComplex ps = new PredictionSeedComplex(0,0,0);
            psListFinal.add(ps);
        }


        return psListFinal.get(psListFinal.size()-1);


    }

    public int getMinimumSeedRate() {
        return minimumSeedRate;
    }

    public int getMaximumSeedRate() {
        return maximumSeedRate;
    }

    public int getMinimumAcceptableEars() {
        return minimumAcceptableEars;
    }

    public int getMaximumAcceptableEars() {
        return maximumAcceptableEars;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public SoilTexture getSoilTextureValue() {
        return soilTextureValue;
    }

    public void setSoilTextureValue(SoilTexture soilTextureValue) {
        this.soilTextureValue = soilTextureValue;
    }

    public SoilType getSoilTypeValue() {
        return soilTypeValue;
    }

    public void setSoilTypeValue(SoilType soilTypeValue) {
        this.soilTypeValue = soilTypeValue;
    }

    public double getNightTemperatureMin() {
        return nightTemperatureMin;
    }

    public void setNightTemperatureMin(double nightTemperatureMin) {
        this.nightTemperatureMin = nightTemperatureMin;
    }

    public double getDayTemperatureMax() {
        return dayTemperatureMax;
    }

    public void setDayTemperatureMax(double dayTemperatureMax) {
        this.dayTemperatureMax = dayTemperatureMax;
    }

    public double getPrecipitationSummary() {
        return precipitationSummary;
    }

    public void setPrecipitationSummary(double precipitationSummary) {
        this.precipitationSummary = precipitationSummary;
    }

    public double getNightAverageTemperature() {
        return nightAverageTemperature;
    }

    public void setNightAverageTemperature(double nightAverageTemperature) {
        this.nightAverageTemperature = nightAverageTemperature;
    }

    public double getDayAverageTemperature() {
        return dayAverageTemperature;
    }

    public void setDayAverageTemperature(double dayAverageTemperature) {
        this.dayAverageTemperature = dayAverageTemperature;
    }

    public double getAveragePrecipitation() {
        return averagePrecipitation;
    }

    public void setAveragePrecipitation(double averagePrecipitation) {
        this.averagePrecipitation = averagePrecipitation;
    }

    public PredictionTarget getPredictionTarget() {
        return predictionTarget;
    }

    public void setPredictionTarget(PredictionTarget predictionTarget) {
        this.predictionTarget = predictionTarget;
    }

    public PredictionSeed getOptimumPrediction() {
        return optimumPrediction;
    }

    public void setOptimumPrediction(PredictionSeed optimumPrediction) {
        this.optimumPrediction = optimumPrediction;
    }


}
