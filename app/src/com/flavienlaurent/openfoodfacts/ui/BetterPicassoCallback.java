package com.flavienlaurent.openfoodfacts.ui;

import com.squareup.picasso.Callback;

/**
 * Created by f.laurent on 05/12/13.
 */
public abstract class BetterPicassoCallback implements Callback {

    @Override
    public void onSuccess() {
        onFinish();
    }

    @Override
    public void onError() {
        onFinish();
    }

    abstract public void onFinish();
}
