package com.afr.restcountryflags.model;

public class Country {

    //Atributos
    private String name;
    private String alpha2Code;
    private String capital;
    private String region;
    private String subregion;
    private int population;
    private String[] borders;

    //Constructores
    public Country() {
    }

    public Country(String name, String alpha2Code, String capital, String region, String subregion, int population, String[] borders) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
    }

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }
}
