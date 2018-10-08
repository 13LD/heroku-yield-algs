package com.example.demo.agroknow.cerebro.syngenta.varifield;

import com.example.demo.agroknow.cerebro.syngenta.varifield.enumerations.*;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;

public abstract class VarifieldInstance {

    final private ArrayList soilTexture = new ArrayList(3) {{
        add("Light");
        add("Medium");
        add("Heavy");
    }};


    final private ArrayList soilType = new ArrayList(10) {{
        add("CalcareousClayLoam");
        add("CalcareousLoamy");
        add("ClayLoam");
        add("Loam");
        add("LoamyClay");
        add("LoamySand");
        add("SandyClayLoam");
        add("SandyLoam");
        add("SiltLoam");
        add("SiltyClayLoam");
        add("SandySiltLoam");
    }};

    final private ArrayList classification = new ArrayList(2) {{
        add("Conventional");
        add("Hybrid");
    }};

    final private ArrayList variety = new ArrayList(4) {{
        add("Gallant");
        add("Reflection");
        add("KWSSantiago");
        add("Graham");
        add("Bazooka");
        add("Belfry");
        add("Fletcher");
        add("Volume");
        add("Celoona");
    }};

    final private ArrayList season = new ArrayList(1) {{
        add("Winter");
        add("Spring");
        add("Summer");
        add("Autumn");

    }};



    protected Attribute SoilTexture = new Attribute("SoilTexture",soilTexture,0);
    protected Attribute SoilType = new Attribute("SoilType",soilType,1);
    protected Attribute Season = new Attribute("Season",season,2);
    protected Attribute PrePlanting15d_TempAir_C_NighttimeMin = new Attribute("PrePlanting15d_TempAir_C_NighttimeMin",3);
    protected Attribute PrePlanting15d_TempAir_C_DaytimeMax = new Attribute("PrePlanting15d_TempAir_C_DaytimeMax",4);
    protected Attribute PrePlanting15d_Precip_mm_dSum = new Attribute("PrePlanting15d_Precip_mm_dSum",5);
    protected Attribute AvgNT = new Attribute("AvgNT",6);
    protected Attribute AvgDT = new Attribute("AvgDT",7);
    protected Attribute AvgPrecip = new Attribute("AvgPrecip",8);
    protected Attribute Seeds = new Attribute("Seeds",9);

    protected ArrayList<Attribute> attributes = new ArrayList<Attribute>();


    private String soilTextureValue;
    private String soilTypeValue;
    private double nightTemperatureMin;
    private double dayTemperatureMax;
    private double precipitationSummary;
    private double nightAverageTemperature;
    private double dayAverageTemperature;
    private double averagePrecipitation;
    private double seeds;

    protected Instance instance;

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

    public VarifieldInstance(SoilTexture soilTexture, SoilType soilType,
                             double nightTemperatureMin, double dayTemperatureMax,
                             double precipitationSummary, double nightAverageTemperature,
                             double dayAverageTemperature, double averagePrecipitation, double seeds) {


        attributes.add(SoilTexture);
        attributes.add(SoilType);
        attributes.add(PrePlanting15d_TempAir_C_NighttimeMin);
        attributes.add(PrePlanting15d_TempAir_C_DaytimeMax);
        attributes.add(PrePlanting15d_Precip_mm_dSum);
        attributes.add(AvgNT);
        attributes.add(AvgDT);
        attributes.add(AvgPrecip);
        attributes.add(Seeds);

        this.soilTextureValue = soilTexture.getSoilTexture();
        this.soilTypeValue = soilType.getSoilType();
        this.nightTemperatureMin = nightTemperatureMin;
        this.dayTemperatureMax = dayTemperatureMax;
        this.precipitationSummary = precipitationSummary;
        this.nightAverageTemperature = nightAverageTemperature;
        this.dayAverageTemperature = dayAverageTemperature;
        this.averagePrecipitation = averagePrecipitation;
        this.seeds = seeds;

    }



    protected void createInstance() {

        Instances dataset = new Instances("Dataset",attributes,1);
        dataset.setClassIndex(attributes.size()-1);

        instance = new DenseInstance(attributes.size());

        instance.setValue(SoilTexture,this.soilTextureValue);
        instance.setValue(SoilType,this.soilTypeValue);
        instance.setValue(PrePlanting15d_TempAir_C_NighttimeMin,this.nightTemperatureMin);
        instance.setValue(PrePlanting15d_TempAir_C_DaytimeMax,this.dayTemperatureMax);
        instance.setValue(PrePlanting15d_Precip_mm_dSum,this.precipitationSummary);
        instance.setValue(AvgNT,this.nightAverageTemperature);
        instance.setValue(AvgDT,this.dayAverageTemperature);
        instance.setValue(AvgPrecip,this.averagePrecipitation);
        instance.setValue(Seeds,this.seeds);

        instance.setDataset(dataset);
    }

    public ArrayList getSoilTexture() {
        return soilTexture;
    }

    public void setSoilTexture(Attribute soilTexture) {
        SoilTexture = soilTexture;
    }

    public ArrayList getSoilType() {
        return soilType;
    }

    public void setSoilType(Attribute soilType) {
        SoilType = soilType;
    }

    public ArrayList getClassification() {
        return classification;
    }

    public ArrayList getVariety() {
        return variety;
    }

    public ArrayList getSeason() {
        return season;
    }

    public void setSeason(Attribute season) {
        Season = season;
    }


    public Attribute getPrePlanting15d_TempAir_C_NighttimeMin() {
        return PrePlanting15d_TempAir_C_NighttimeMin;
    }

    public void setPrePlanting15d_TempAir_C_NighttimeMin(Attribute prePlanting15d_TempAir_C_NighttimeMin) {
        PrePlanting15d_TempAir_C_NighttimeMin = prePlanting15d_TempAir_C_NighttimeMin;
    }

    public Attribute getPrePlanting15d_TempAir_C_DaytimeMax() {
        return PrePlanting15d_TempAir_C_DaytimeMax;
    }

    public void setPrePlanting15d_TempAir_C_DaytimeMax(Attribute prePlanting15d_TempAir_C_DaytimeMax) {
        PrePlanting15d_TempAir_C_DaytimeMax = prePlanting15d_TempAir_C_DaytimeMax;
    }

    public Attribute getPrePlanting15d_Precip_mm_dSum() {
        return PrePlanting15d_Precip_mm_dSum;
    }

    public void setPrePlanting15d_Precip_mm_dSum(Attribute prePlanting15d_Precip_mm_dSum) {
        PrePlanting15d_Precip_mm_dSum = prePlanting15d_Precip_mm_dSum;
    }

    public Attribute getAvgNT() {
        return AvgNT;
    }

    public void setAvgNT(Attribute avgNT) {
        AvgNT = avgNT;
    }

    public Attribute getAvgDT() {
        return AvgDT;
    }

    public void setAvgDT(Attribute avgDT) {
        AvgDT = avgDT;
    }

    public Attribute getAvgPrecip() {
        return AvgPrecip;
    }

    public void setAvgPrecip(Attribute avgPrecip) {
        AvgPrecip = avgPrecip;
    }

    public Attribute getSeeds() {
        return Seeds;
    }

    public void setSeeds(double seeds) {
        this.seeds = seeds;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public void setSeeds(Attribute seeds) {
        Seeds = seeds;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getSoilTextureValue() {
        return soilTextureValue;
    }

    public void setSoilTextureValue(String soilTextureValue) {
        this.soilTextureValue = soilTextureValue;
    }

    public String getSoilTypeValue() {
        return soilTypeValue;
    }

    public void setSoilTypeValue(String soilTypeValue) {
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

}
