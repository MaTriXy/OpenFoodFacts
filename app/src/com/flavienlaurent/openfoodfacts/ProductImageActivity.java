package com.flavienlaurent.openfoodfacts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.flavienlaurent.openfoodfacts.base.BackActivity;
import com.flavienlaurent.openfoodfacts.ui.BetterPicassoCallback;
import com.squareup.picasso.Picasso;

/**
 * Created by f.laurent on 04/11/13.
 */
public class ProductImageActivity extends BackActivity {

    public static final String EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL";
    public static final String EXTRA_PHOTOGRAPHERS = "EXTRA_PHOTOGRAPHERS";

    private String mImageUrl;
    private String mPhotographerNames;

    private ImageView mImage;
    private View mProgressBar;
    private TextView mPhotographers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        injectExtras();
        setContentView(R.layout.activity_image);
        bindViews();
        setupView();
    }

    private void injectExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_IMAGE_URL)) {
            mImageUrl = extras.getString(EXTRA_IMAGE_URL);
        }
        if (extras != null && extras.containsKey(EXTRA_PHOTOGRAPHERS)) {
            mPhotographerNames = extras.getString(EXTRA_PHOTOGRAPHERS);
        }
    }

    private void bindViews() {
        mImage = (ImageView) findViewById(R.id.image);
        mProgressBar = findViewById(R.id.progressBar);
        mPhotographers = (TextView) findViewById(R.id.photographers);
    }

    private void setupView() {
        fillPhotographers();
        fillImage();
    }

    private void fillPhotographers() {
        if(!TextUtils.isEmpty(mPhotographerNames)) {
            mPhotographers.setText(mPhotographerNames);
        } else {
            mPhotographers.setVisibility(View.GONE);
        }
    }

    private void fillImage() {
        mProgressBar.setVisibility(View.VISIBLE);
        Picasso picasso = Picasso.with(this);
        picasso.load(mImageUrl)
            .placeholder(R.drawable.ic_stub_image)
            .error(R.drawable.ic_stub_image)
            .centerCrop().fit()
            .into(mImage, new BetterPicassoCallback() {
                @Override
                public void onFinish() {
                    mProgressBar.setVisibility(View.GONE);
                }
            });
    }
}
