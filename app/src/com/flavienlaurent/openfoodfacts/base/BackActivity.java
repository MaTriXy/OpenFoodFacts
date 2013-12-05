package com.flavienlaurent.openfoodfacts.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by f.laurent on 04/11/13.
 */
public class BackActivity extends ActionBarActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}