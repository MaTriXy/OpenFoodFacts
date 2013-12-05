package com.flavienlaurent.openfoodfacts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flavienlaurent.openfoodfacts.base.BackActivity;
import com.flavienlaurent.openfoodfacts.core.model.Product;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by f.laurent on 05/11/13.
 */
public class BrowseProductActivity extends BackActivity {

    public static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";

    private Product mProduct;

    private LinearLayout mEntries;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectExtras();
        setContentView(R.layout.activity_browse_product_file);

        mEntries = (LinearLayout) findViewById(R.id.entries);

        setupView();
    }

    private void injectExtras() {
        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey(EXTRA_PRODUCT)) {
            mProduct = (Product) extras.get(EXTRA_PRODUCT);
        }
    }

    private void setupView() {
        for(Field field : Product.class.getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            View fieldView = null;

            if (Collection.class.isAssignableFrom(fieldType)) {
                fieldView = buildCollectionField(field);
            } else if (Map.class.isAssignableFrom(fieldType)) {
                fieldView = buildMapField(field);
            } else {
                fieldView = buildSimpleField(field);
            }

            if(fieldView == null) {
                continue;
            }
            mEntries.addView(fieldView);
        }
    }

    private View buildCollectionField(Field field) {
        try {
            Collection collection = (Collection) field.get(mProduct);
            return buildSimpleField(field.getName(), collection.toString());
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    private View buildMapField(Field field) {
        return null;
    }

    private View buildSimpleField(Field field) {
        if(field == null) {
            return null;
        }
        try {
            Object valueObject = field.get(mProduct);
            return buildSimpleField(field.getName(), valueObject != null ? valueObject.toString(): "NULL");
        } catch (IllegalAccessException e) {
        }
        return null;
    }


    private View buildSimpleField(String keyValue, String valueValue) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View simpleFieldView = layoutInflater.inflate(R.layout.view_browse_simple_field, mEntries, false);

        TextView key = (TextView) simpleFieldView.findViewById(R.id.key);
        TextView value = (TextView) simpleFieldView.findViewById(R.id.value);

        key.setText(keyValue);
        value.setText(valueValue);

        return simpleFieldView;
    }
}