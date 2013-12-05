package com.flavienlaurent.openfoodfacts.core;

import com.flavienlaurent.businesslayer.BusinessContext;
import com.flavienlaurent.businesslayer.BusinessService;
import com.flavienlaurent.openfoodfacts.core.model.OpenFoodFactsResponse;

import java.util.HashMap;

/**
 * Created by flav on 03/11/13.
 */
public class CacheRequestService extends BusinessService {

    private HashMap<String, OpenFoodFactsResponse> mCache;

    @Override
    protected void onCreate(BusinessContext context) {
        super.onCreate(context);
        mCache = new HashMap<String, OpenFoodFactsResponse>();
    }

    @Override
    protected void onLowMemory() {
        super.onLowMemory();
        clearCache();
    }

    public OpenFoodFactsResponse getProductResponseFromCache(String ean) {
        return mCache.get(ean);
    }

    public void putOpenFoodFactsResponseInCache(String ean, OpenFoodFactsResponse bigObject) {
        mCache.put(ean, bigObject);
    }

    public void clearCache() {
        mCache.clear();
    }

    public int cacheSize() {
        return mCache.size();
    }
}
