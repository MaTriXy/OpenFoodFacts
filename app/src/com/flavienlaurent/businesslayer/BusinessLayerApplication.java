package com.flavienlaurent.businesslayer;

import android.app.Application;

/**
 * Created by flav on 16/10/13.
 */
public class BusinessLayerApplication extends Application {

    private BusinessContext mBusinessContext = new BusinessContext();

    @Override
    public void onCreate() {
        super.onCreate();
        mBusinessContext.onCreate(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mBusinessContext.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        mBusinessContext.onTrimMemory(level);
    }

    public BusinessContext getBusinessServiceContext() {
        return mBusinessContext;
    }
}

