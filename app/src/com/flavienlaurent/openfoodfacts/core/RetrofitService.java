package com.flavienlaurent.openfoodfacts.core;

import com.flavienlaurent.openfoodfacts.core.model.OpenFoodFactsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by f.laurent on 31/10/13.
 */
public interface RetrofitService {

    @GET("/product/{ean}.json")
    void getProduct(@Path("ean") String ean, Callback<OpenFoodFactsResponse> callback);
}
