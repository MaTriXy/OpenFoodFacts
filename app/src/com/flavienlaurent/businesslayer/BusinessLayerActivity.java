package com.flavienlaurent.businesslayer;

import android.app.Activity;

/**
 * Created by flav on 16/10/13.
 */
public class BusinessLayerActivity extends Activity {

    protected <B extends BusinessService> B get(Class<B> businessServiceClass) {
        return getBusinessServiceContext().get(businessServiceClass);
    }

    protected BusinessContext getBusinessServiceContext() {
        return getBaseApplication().getBusinessServiceContext();
    }

    private BusinessLayerApplication getBaseApplication() {
        return (BusinessLayerApplication)getApplication();
    }
}
