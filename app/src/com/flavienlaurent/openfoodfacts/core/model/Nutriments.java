package com.flavienlaurent.openfoodfacts.core.model;

import java.io.Serializable;
import java.util.HashMap;

//FIXME primitive type
//FIXME clean unused
//FIXME double
public class Nutriments implements Serializable {

    public Integer carbohydrates;
    public Double carbohydrates_100g;
    public Double carbohydrates_serving;
    public String carbohydrates_unit;
    public Integer energy;
    public Integer energy_100g;
    public Integer energy_serving;
    public String energy_unit;
    public Double fat;
    public Double fat_100g;
    public Double fat_serving;
    public String fat_unit;
    public Integer fiber;
    public Double fiber_100g;
    public Double fiber_serving;
    public String fiber_unit;
    public Double proteins;
    public Double proteins_100g;
    public Double proteins_serving;
    public String proteins_unit;
    public Double salt_100g;
    public Double saturated_fat;
    public Double saturated_fat_100g;
    public Double saturated_fat_serving;
    public String saturated_fat_unit;
    public Double sodium;
    public Double sodium_100g;
    public Double sodium_serving;
    public String sodium_unit;
    public Integer sugars;
    public Double sugars_100g;
    public Double sugars_serving;
    public String sugars_unit;
    public HashMap<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Nutriments{" +
                "carbohydrates=" + carbohydrates +
                ", carbohydrates_100g=" + carbohydrates_100g +
                ", carbohydrates_serving=" + carbohydrates_serving +
                ", carbohydrates_unit='" + carbohydrates_unit + '\'' +
                ", energy=" + energy +
                ", energy_100g=" + energy_100g +
                ", energy_serving=" + energy_serving +
                ", energy_unit='" + energy_unit + '\'' +
                ", fat=" + fat +
                ", fat_100g='" + fat_100g + '\'' +
                ", fat_serving=" + fat_serving +
                ", fat_unit='" + fat_unit + '\'' +
                ", fiber=" + fiber +
                ", fiber_100g=" + fiber_100g +
                ", fiber_serving=" + fiber_serving +
                ", fiber_unit='" + fiber_unit + '\'' +
                ", proteins=" + proteins +
                ", proteins_100g=" + proteins_100g +
                ", proteins_serving=" + proteins_serving +
                ", proteins_unit='" + proteins_unit + '\'' +
                ", salt_100g='" + salt_100g + '\'' +
                ", saturated_fat=" + saturated_fat +
                ", saturated_fat_100g='" + saturated_fat_100g + '\'' +
                ", saturated_fat_serving=" + saturated_fat_serving +
                ", saturated_fat_unit='" + saturated_fat_unit + '\'' +
                ", sodium=" + sodium +
                ", sodium_100g=" + sodium_100g +
                ", sodium_serving=" + sodium_serving +
                ", sodium_unit='" + sodium_unit + '\'' +
                ", sugars=" + sugars +
                ", sugars_100g='" + sugars_100g + '\'' +
                ", sugars_serving=" + sugars_serving +
                ", sugars_unit='" + sugars_unit + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}