package com.flavienlaurent.openfoodfacts.core.model;

import java.io.Serializable;
import java.util.HashMap;

//FIXME clean unused
public class Ingredient implements Serializable {

    private String id;
    private Integer rank;
    private String text;
    private HashMap<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", rank=" + rank +
                ", text='" + text + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}