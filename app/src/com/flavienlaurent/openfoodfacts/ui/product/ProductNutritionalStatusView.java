package com.flavienlaurent.openfoodfacts.ui.product;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flavienlaurent.openfoodfacts.R;
import com.flavienlaurent.openfoodfacts.core.model.Nutriments;
import com.flavienlaurent.openfoodfacts.core.model.Product;

/**
 * Created by f.laurent on 05/12/13.
 */
public class ProductNutritionalStatusView extends LinearLayout {

    private static final String DECIMAL_FORMAT = "%.2f";

    private TextView mServingSize;
    private TextView mEnergy100;
    private TextView mEnergyServing;
    private TextView mProteins100;
    private TextView mProteinsServing;
    private TextView mCarbohydrates100;
    private TextView mCarbohydratesServing;
    private TextView mSugar100;
    private TextView mSugarServing;
    private TextView mFat100;
    private TextView mFatServing;
    private TextView mSaturedFat100;
    private TextView mSaturedFatServing;
    private TextView mFiber100;
    private TextView mFiberServing;
    private TextView mSodium100;
    private TextView mSodiumServing;
    private TextView mSalt100;
    private TextView mSaltServing;

    public ProductNutritionalStatusView(Context context) {
        this(context, null);
    }

    public ProductNutritionalStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductNutritionalStatusView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.view_product_nutritional_status, this);
        bindViews();
    }

    private void bindViews() {
        mServingSize = (TextView) findViewById(R.id.servingSize);
        mEnergy100 = (TextView) findViewById(R.id.energy100);
        mEnergyServing = (TextView) findViewById(R.id.energyServing);
        mProteins100 = (TextView) findViewById(R.id.proteins100);
        mProteinsServing = (TextView) findViewById(R.id.proteinsServing);
        mCarbohydrates100 = (TextView) findViewById(R.id.carbohydrates100);
        mCarbohydratesServing = (TextView) findViewById(R.id.carbohydratesServing);
        mSugar100 = (TextView) findViewById(R.id.sugar100);
        mSugarServing = (TextView) findViewById(R.id.sugarServing);
        mFat100 = (TextView) findViewById(R.id.fat100);
        mFatServing = (TextView) findViewById(R.id.fatServing);
        mSaturedFat100 = (TextView) findViewById(R.id.saturedFat100);
        mSaturedFatServing = (TextView) findViewById(R.id.saturedFatServing);
        mFiber100 = (TextView) findViewById(R.id.fiber100);
        mFiberServing = (TextView) findViewById(R.id.fiberServing);
        mSodium100 = (TextView) findViewById(R.id.sodium100);
        mSodiumServing = (TextView) findViewById(R.id.sodiumServing);
        mSalt100 = (TextView) findViewById(R.id.salt100);
        mSaltServing = (TextView) findViewById(R.id.saltServing);
    }

    public void fill(Product product) {
        mServingSize.setText(product.serving_size);

        Nutriments nutriments = product.nutriments;
        if(nutriments == null) {
            mEnergy100.setText("");
            mEnergyServing.setText("");
            mProteins100.setText("");
            mProteinsServing.setText("");
            mCarbohydrates100.setText("");
            mCarbohydratesServing.setText("");
            mSugar100.setText("");
            mSugarServing.setText("");
            mFat100.setText("");
            mFatServing.setText("");
            mSaturedFat100.setText("");
            mSaturedFatServing.setText("");
            mFiber100.setText("");
            mFiberServing.setText("");
            mSodium100.setText("");
            mSodiumServing.setText("");
            mSalt100.setText("");
            mSaltServing.setText("");
        } else {
            mEnergy100.setText(String.valueOf(nutriments.energy_100g));
            mEnergyServing.setText(String.valueOf(nutriments.energy_serving));

            formatAndSet(mProteins100, nutriments.proteins_100g);
            formatAndSet(mProteinsServing, nutriments.proteins_serving);

            formatAndSet(mCarbohydrates100, nutriments.carbohydrates_100g);
            formatAndSet(mCarbohydratesServing, nutriments.carbohydrates_serving);

            formatAndSet(mSugar100, nutriments.sugars_100g);
            formatAndSet(mSugarServing, nutriments.sugars_serving);

            formatAndSet(mFat100, nutriments.fat_100g);
            formatAndSet(mFatServing, nutriments.fat_serving);

            formatAndSet(mSaturedFat100, nutriments.saturated_fat_100g);
            formatAndSet(mSaturedFatServing, nutriments.saturated_fat_serving);

            formatAndSet(mFiber100, nutriments.fiber_100g);
            formatAndSet(mFiberServing, nutriments.fiber_serving);

            formatAndSet(mSodium100, nutriments.sodium_100g);
            formatAndSet(mSaltServing, 0.0);//todo
            formatAndSet(mSodiumServing, nutriments.sodium_serving);

            formatAndSet(mSalt100, nutriments.salt_100g);
        }
    }

    private static void formatAndSet(TextView textView, Double value) {
        textView.setText(String.format(DECIMAL_FORMAT, value));
    }
}
