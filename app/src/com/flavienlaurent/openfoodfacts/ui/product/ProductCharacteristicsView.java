package com.flavienlaurent.openfoodfacts.ui.product;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flavienlaurent.openfoodfacts.R;
import com.flavienlaurent.openfoodfacts.core.model.Product;

/**
 * Created by f.laurent on 05/12/13.
 */
public class ProductCharacteristicsView extends LinearLayout {

    private TextView mPackagingCodes;
    private TextView mOrigins;
    private TextView mCategories;
    private TextView mGenericName;
    private TextView mQuantity;
    private TextView mPackaging;
    private TextView mBrands;

    public ProductCharacteristicsView(Context context) {
        this(context, null);
    }

    public ProductCharacteristicsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductCharacteristicsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.view_product_characteristics, this);
        bindViews();
    }

    private void bindViews() {
        mGenericName = (TextView) findViewById(R.id.genericName);
        mQuantity = (TextView) findViewById(R.id.quantity);
        mPackaging = (TextView) findViewById(R.id.packaging);
        mBrands = (TextView) findViewById(R.id.brands);
        mCategories = (TextView) findViewById(R.id.categories);
        mOrigins= (TextView) findViewById(R.id.origins);
        mPackagingCodes = (TextView) findViewById(R.id.packagingCodes);
    }

    public void fill(Product product) {
        mGenericName.setText(product.generic_name);
        mQuantity.setText(product.quantity);
        mPackaging.setText(product.packaging);
        mBrands.setText(product.brands);
        mCategories.setText(splitCategories(product.categories));
        mOrigins.setText(product.origins);
        mPackagingCodes.setText(product.emb_codes);
    }

    private String splitCategories(String categories) {
        StringBuilder sb = new StringBuilder();
        for(String category : categories.split(",")) {
            sb.append("- ");
            sb.append(category);
            sb.append("\n");
        }
        return sb.toString();
    }
}
