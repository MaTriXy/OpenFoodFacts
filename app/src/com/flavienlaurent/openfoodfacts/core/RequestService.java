package com.flavienlaurent.openfoodfacts.core;

import com.flavienlaurent.businesslayer.BusinessContext;
import com.flavienlaurent.businesslayer.BusinessService;
import com.flavienlaurent.openfoodfacts.core.model.OpenFoodFactsResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by flav on 03/11/13.
 */
public class RequestService extends BusinessService {

    private static final String BASE_URL = "http://fr.openfoodfacts.org/api/v0";

    private CacheRequestService mCacheRequestService;
    private RetrofitService mRetrofitService;

    @Override
    protected void onCreate(BusinessContext context) {
        super.onCreate(context);
        mCacheRequestService = context.get(CacheRequestService.class);

        RestAdapter restAdapter = new RestAdapter.Builder()
        .setServer(BASE_URL)
        .build();

        mRetrofitService = restAdapter.create(RetrofitService.class);
    }

    public void getProduct(final String ean, final Callback<OpenFoodFactsResponse> callback) {
        OpenFoodFactsResponse openFoodFactsResponse = mCacheRequestService.getProductResponseFromCache(ean);
        if(openFoodFactsResponse != null) {
            openFoodFactsResponse.fromCache = true;
            callback.success(openFoodFactsResponse, null);
            return;
        }

        mRetrofitService.getProduct(ean, new Callback<OpenFoodFactsResponse>() {
            @Override
            public void success(OpenFoodFactsResponse openFoodFactsResponse, Response response) {
                if(openFoodFactsResponse != null && openFoodFactsResponse.status == 1) {
                    openFoodFactsResponse.fromCache = false;
                    mCacheRequestService.putOpenFoodFactsResponseInCache(ean, openFoodFactsResponse);
                }
                callback.success(openFoodFactsResponse, response);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                callback.failure(retrofitError);
            }
        });
    }
}
