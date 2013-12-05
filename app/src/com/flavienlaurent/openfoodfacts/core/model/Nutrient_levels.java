package com.flavienlaurent.openfoodfacts.core.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//FIXME clean unused
public class Nutrient_levels implements Serializable {

    public String fat;
    public String salt;
    public String saturated_fat;
    public String sugars;
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Nutrient_levels{" +
                "fat='" + fat + '\'' +
                ", salt='" + salt + '\'' +
                ", saturated_fat='" + saturated_fat + '\'' +
                ", sugars='" + sugars + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}