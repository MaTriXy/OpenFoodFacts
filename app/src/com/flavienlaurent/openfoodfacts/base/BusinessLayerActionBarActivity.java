package com.flavienlaurent.openfoodfacts.base;

import android.support.v7.app.ActionBarActivity;

import com.flavienlaurent.businesslayer.BusinessContext;
import com.flavienlaurent.businesslayer.BusinessLayerApplication;
import com.flavienlaurent.businesslayer.BusinessService;

/**
 * Created by f.laurent on 05/11/13.
 */
public class BusinessLayerActionBarActivity extends ActionBarActivity {

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
