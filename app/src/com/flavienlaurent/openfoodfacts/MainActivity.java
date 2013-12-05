package com.flavienlaurent.openfoodfacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flavienlaurent.openfoodfacts.base.BusinessLayerActionBarActivity;
import com.flavienlaurent.openfoodfacts.core.RequestService;
import com.flavienlaurent.openfoodfacts.core.model.OpenFoodFactsResponse;
import com.flavienlaurent.openfoodfacts.core.model.Product;
import com.flavienlaurent.openfoodfacts.scan.IntentIntegrator;
import com.flavienlaurent.openfoodfacts.scan.IntentResult;
import com.flavienlaurent.openfoodfacts.ui.BetterPicassoCallback;
import com.flavienlaurent.openfoodfacts.ui.product.ProductCharacteristicsView;
import com.flavienlaurent.openfoodfacts.ui.product.ProductNutritionalStatusView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BusinessLayerActionBarActivity {

    private static final boolean DEBUG = true;

    private ImageView mImage;
    private ImageView mImageFullScreen;
    private TextView mBarCode;
    private TextView mTitle;
    private LinearLayout mTags;
    private Button mBrowseProduct;
    private View mResult;
    private TextView mIntro;
    private ProgressBar mProgressBar;
    private TextView mAddedby;
    private TextView mLastModifedby;
    private ProductCharacteristicsView mProductCharacteristicsView;
    private ProductNutritionalStatusView mProductNutritionalStatusView;

    private RequestService mRequestService;
    public OpenFoodFactsResponse mOpenFoodFactsResponse;

    private DateFormat mDateFormat;
    private DateFormat mTimeFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestService = get(RequestService.class);
<<<<<<< HEAD:OpenFoodFactsApp/src/com/flavienlaurent/openfoodfacts/MainActivity.java
=======
        mDateFormat = android.text.format.DateFormat.getDateFormat(this);
        mTimeFormat = android.text.format.DateFormat.getTimeFormat(this);

>>>>>>> gradle + product file:app/src/com/flavienlaurent/openfoodfacts/MainActivity.java
        setContentView(R.layout.activity_main);

        bindViews();
        setupView();
    }

    private void bindViews() {
        mImage = (ImageView) findViewById(R.id.image);
        mImageFullScreen = (ImageView) findViewById(R.id.fullscreen);
        mBarCode = (TextView) findViewById(R.id.barcode);
        //mTitle = (TextView) findViewById(R.id.title);
        mTags = (LinearLayout) findViewById(R.id.tags);
        mBrowseProduct = (Button) findViewById(R.id.browse_product);
        mResult = findViewById(R.id.result);
        mIntro = (TextView) findViewById(R.id.intro);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAddedby = (TextView) findViewById(R.id.addedby);
        mLastModifedby = (TextView) findViewById(R.id.lastModifedby);
        mProductCharacteristicsView = (ProductCharacteristicsView) findViewById(R.id.characteristics);
        mProductNutritionalStatusView = (ProductNutritionalStatusView) findViewById(R.id.nutritional_status);
    }

    private void setupView() {
        setupImageFullScreen();
        setupBrowseProduct();
    }

    private void setupBrowseProduct() {
        mBrowseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenFoodFactsResponse == null || mOpenFoodFactsResponse.product == null) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, BrowseProductActivity.class);
                intent.putExtra(BrowseProductActivity.EXTRA_PRODUCT, mOpenFoodFactsResponse.product);
                startActivity(intent);
            }
        });
    }

    private void setupImageFullScreen() {
        mImageFullScreen.setVisibility(View.GONE);
        mImageFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = getImageUrl(mOpenFoodFactsResponse);
                if (mOpenFoodFactsResponse == null || mOpenFoodFactsResponse.product == null || imageUrl == null) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ProductImageActivity.class);
                intent.putExtra(ProductImageActivity.EXTRA_IMAGE_URL, imageUrl);
                intent.putExtra(ProductImageActivity.EXTRA_PHOTOGRAPHERS, mOpenFoodFactsResponse.product.photographers);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                break;
            case R.id.action_scan:
                onMenuScan();
                return true;
        }
        return false;
    }

    private void onMenuScan() {
        if(DEBUG) {
            fillBarCode("2165244002857");
        } else{
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.initiateScan(Arrays.asList("EAN_13"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String barCode = scanResult.getContents();
        if (scanResult != null && barCode != null) {
            fillBarCode(barCode);
        }
    }

    private void fillBarCode(String barCode) {
        mBarCode.setText(barCode);
        mBarCode.setVisibility(View.VISIBLE);
        getProduct(barCode);
    }

    private void getProduct(final String barcode) {
        mIntro.setText(getString(R.string.searching) + " " + barcode);
        mIntro.setVisibility(View.VISIBLE);
        mResult.setVisibility(View.GONE);

        mImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mImage.setImageResource(R.drawable.ic_stub_image);
        mImageFullScreen.setVisibility(View.GONE);
        mRequestService.getProduct(barcode, new Callback<OpenFoodFactsResponse>() {

            @Override
            public void success(OpenFoodFactsResponse offResponse, Response response) {
                //TODO si notfound redirect vers une page pr l'insertion
                //TODO afficher l'état de la fiche avec un code couleur

                mIntro.setVisibility(View.GONE);
                mResult.setVisibility(View.VISIBLE);

                mOpenFoodFactsResponse = offResponse;
                if (offResponse != null) {
                    Product product = offResponse.product;
                    if (product != null && offResponse.status == 1) {
                        fillImage(offResponse);
                        fillTags(offResponse);
                        fillDates(product);
                        mProductCharacteristicsView.fill(product);
                        mProductNutritionalStatusView.fill(product);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.product_not_found, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                mIntro.setText(getString(R.string.error_while_searching) + " (" + retrofitError.getResponse().getStatus() + ")");
            }
        });
    }

    private void fillDates(Product product) {
        Date date = new Date(product.created_t * 1000);
        mAddedby.setText(getString(R.string.addedbythe, mDateFormat.format(date), mTimeFormat.format(date), product.creator));
        date = new Date(product.last_modified_t * 1000);
        mLastModifedby.setText(getString(R.string.lastmodifiedbythe, mDateFormat.format(date), mTimeFormat.format(date), product.last_modified_by));
    }

    private void fillTags(OpenFoodFactsResponse offResponse) {
        String[] statusTags = offResponse.product.status != null ? offResponse.product.status.split(",") : null;
        if(statusTags == null) {
            return;
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        for(String statusTag : statusTags) {
            TextView statusTagView = (TextView) layoutInflater.inflate(R.layout.view_status_tag, mTags, false);
            statusTagView.setText(statusTag);
            mTags.addView(statusTagView);
        }
    }

    private void fillImage(OpenFoodFactsResponse offResponse) {
        mProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(this)
                .load(getImageUrl(offResponse))
                .placeholder(R.drawable.ic_stub_image)
                .error(R.drawable.ic_stub_image)
                .centerCrop().fit()
                .into(mImage, new BetterPicassoCallback() {
                    @Override
                    public void onFinish() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onSuccess() {
                        super.onSuccess();
                        mImageFullScreen.setVisibility(View.VISIBLE);
                    }
                });
    }

    private String getImageUrl(OpenFoodFactsResponse offResponse) {
        if(offResponse.product.image_url != null) {
            return offResponse.product.image_url;
        }
        return offResponse.product.image_small_url;
    }
}
