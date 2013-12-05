package com.flavienlaurent.businesslayer;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by flav on 16/10/13.
 */
public class BusinessContext {

    private static final String TAG = "BusinessContext";

    private HashMap<Class<? extends BusinessService>, BusinessService> mServices = new HashMap<Class<? extends BusinessService>, BusinessService>();

    private Context mContext;

    public void onCreate(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public <B extends BusinessService> B get(Class<B> businessServiceClass) {
        BusinessService businessService = mServices.get(businessServiceClass);
        if(businessService == null) {
            businessService = createBusinessService(businessServiceClass);
        }
        return (B) businessService;
    }

    private <B extends BusinessService> B createBusinessService(Class<B> businessServiceClass) {
        try {
            B businessService = businessServiceClass.newInstance();
            mServices.put(businessServiceClass, businessService);
            businessService.onCreate(this);
            Log.d(TAG, "businessService " + businessServiceClass + " created from " + mContext);
            return (B) businessService;
        } catch (InstantiationException e) {
            Log.e(TAG, "failed to create business service: " + businessServiceClass, e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "failed to create business service: " + businessServiceClass, e);
        }
        throw new IllegalStateException("failed to create business service: " + businessServiceClass);
    }

    public void onLowMemory() {
        dispatchOnLowMemory();
    }

    public void onTrimMemory(int level) {
        dispatchOnTrimMemory(level);
    }

    private void dispatchOnLowMemory() {
        Log.d(TAG, "dispatch low memory on " + this);
        for(BusinessService businessService : mServices.values()) {
            businessService.onLowMemory();
        }
    }

    private void dispatchOnTrimMemory(int level) {
        Log.d(TAG, "dispatch trim memory " + level + " on " + this);
        for(BusinessService businessService : mServices.values()) {
            businessService.onTrimMemory(level);
        }
    }
}
