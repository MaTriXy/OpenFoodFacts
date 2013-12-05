package com.flavienlaurent.openfoodfacts.core.model;

import java.util.HashMap;

//FIXME primitive type
//FIXME clean unused
public class OpenFoodFactsResponse {

    public String code;
    public Product product;
    public Integer status;
    public String status_verbose;
    public HashMap<String, Object> additionalProperties = new HashMap<String, Object>();
    public boolean fromCache;

    @Override
    public String toString() {
        return "OpenFoodFactsResponse{" +
                "code='" + code + '\'' +
                ", product=" + product +
                ", status=" + status +
                ", status_verbose='" + status_verbose + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}