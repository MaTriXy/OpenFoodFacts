package com.flavienlaurent.businesslayer;

import android.util.Log;

/**
 * Created by flav on 16/10/13.
 */
public abstract class BusinessService {

    private static final String TAG = "BusinessService";

    protected void onCreate(BusinessContext context) {
        Log.d(TAG, "onCreate " + getClass());
    }

    protected void onLowMemory() {
        Log.d(TAG, "onLowMemory " + getClass());
    }

    protected void onTrimMemory(int level) {
        Log.d(TAG, "onTrimMemory " + level + " " + getClass());
    }
}
