package apps.pixel.the.key.activites.retaurant.restaurants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.selectedRestaurant.SelectedItemKotlinActivity;
import apps.pixel.the.key.adapters.restaurant.RestaurantAdapter;
import apps.pixel.the.key.models.retaurants.SelectedCat;
import apps.pixel.the.key.utilities.CairoBoldEditText;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.Constant;

public class SelectedCateagoryActivity extends AppCompatActivity implements RestaurantAdapter.OnClickHandler, SelectedCateagoryInterface {

    public static SwipeRefreshLayout swipeContainer;
    public static RecyclerView mRv;
    public static CairoBoldTextView mTxtNoDAta;
    private List<String> arabicNames;
    private List<String> idList;
    private List<String> englishNames;
    private List<String> logoUrls;
    private List<String> sinceList;
    private List<String> bgUrls;
    private RestaurantAdapter adapter;
    private SelectedCateagoryPresenter presenter;
    private AppCompatImageView mImgSearch;
    private CairoBoldEditText mEditTextSearch;
    private AppCompatImageView mImgBack;

    private AppCompatImageView mImgBackGround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_activity_main);

        initViews();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        presenter.getAllRestaurants();
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (mEditTextSearch.getVisibility() == View.VISIBLE) {
            mEditTextSearch.setVisibility(View.GONE);

            if (mEditTextSearch.getText().length() > 0)
                mEditTextSearch.getText().clear();

            hideSoftKeyboardAfterSearch();
        } else {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateSwipeRight(this);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {


        mImgBack = findViewById(R.id.arrow_back_page_two);
        mImgBack.setOnClickListener(v -> {
            onBackPressed();
        });

        mImgBackGround = findViewById(R.id.back_ground_image);

        mTxtNoDAta = findViewById(R.id.txt_no_data);
        mTxtNoDAta.setVisibility(View.GONE);


        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(SelectedCateagoryActivity.this, v);
            return true;
        });

        mImgSearch = findViewById(R.id.ic_search);
        mEditTextSearch = findViewById(R.id.searchEditText);
        mEditTextSearch.setVisibility(View.GONE);

        mImgSearch.setOnClickListener(v -> {
            mEditTextSearch.setVisibility(View.VISIBLE);
            mEditTextSearch.requestFocus();
            mEditTextSearch.setFocusableInTouchMode(true);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditTextSearch, InputMethodManager.SHOW_FORCED);
        });

        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEditTextSearch.getText().length() > 0)
                    if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_RESTAURANT_VALUE)) {
                        presenter.searchOnRestaurant(mEditTextSearch.getText().toString());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_BEAUTY_VALUE)) {
                        presenter.searchOnBeauty(mEditTextSearch.getText().toString());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_GYM_VALUE)) {
                        presenter.searchOnGym(mEditTextSearch.getText().toString());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_HOSPITAL_VALUE)) {
                        presenter.searchOnHospital(mEditTextSearch.getText().toString().trim());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_PHARMACY_VALUE)) {
                        presenter.searchOnPharmacy(mEditTextSearch.getText().toString().trim());
                    }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditTextSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mEditTextSearch.setVisibility(View.GONE);

                if (mEditTextSearch.getText().length() > 0)
                    if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_RESTAURANT_VALUE)) {
                        presenter.searchOnRestaurant(mEditTextSearch.getText().toString());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_BEAUTY_VALUE)) {
                        presenter.searchOnBeauty(mEditTextSearch.getText().toString());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_GYM_VALUE)) {
                        presenter.searchOnGym(mEditTextSearch.getText().toString());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_HOSPITAL_VALUE)) {
                        presenter.searchOnHospital(mEditTextSearch.getText().toString().trim());
                    } else if (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED).equals(Constant.CAT_PHARMACY_VALUE)) {
                        presenter.searchOnPharmacy(mEditTextSearch.getText().toString().trim());
                    }


                if (mEditTextSearch.getText().length() > 0)
                    mEditTextSearch.getText().clear();

                hideSoftKeyboardAfterSearch();
            }
            return false;
        });
        presenter = new SelectedCateagoryPresenter(this, this);
        if (getIntent().hasExtra(Constant.CAT_THAT_SELECTED)) {
            switch (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED)) {
                case Constant.CAT_RESTAURANT_VALUE:
                    presenter.getAllRestaurants();
                    mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.resta_bg));
                    break;
                case Constant.CAT_BEAUTY_VALUE:
                    presenter.getAllBeautyData();
                    mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                    break;
                case Constant.CAT_GYM_VALUE:
                    presenter.getAllGymData();
                    mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                    break;
                case Constant.CAT_HOSPITAL_VALUE:
                    presenter.getAllHospitalData();
                    mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                    break;
                case Constant.CAT_PHARMACY_VALUE:
                    presenter.getAllPharmaciesData();
                    mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                    break;
            }
        }


        swipeContainer.setOnRefreshListener(() -> {
            if (getIntent().hasExtra(Constant.CAT_THAT_SELECTED)) {
                switch (getIntent().getStringExtra(Constant.CAT_THAT_SELECTED)) {
                    case Constant.CAT_RESTAURANT_VALUE:
                        presenter.getAllRestaurants();
                        mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.resta_bg));
                        break;
                    case Constant.CAT_BEAUTY_VALUE:
                        presenter.getAllBeautyData();
                        mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                        break;
                    case Constant.CAT_GYM_VALUE:
                        presenter.getAllGymData();
                        mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                        break;
                    case Constant.CAT_HOSPITAL_VALUE:
                        presenter.getAllHospitalData();
                        mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                        break;
                    case Constant.CAT_PHARMACY_VALUE:
                        presenter.getAllPharmaciesData();
                        mImgBackGround.setImageDrawable(getResources().getDrawable(R.drawable.bg_health));
                        break;
                }
            }
        });

        // Configure the refreshing colors
        Constant.setSwipeLayourColor(this, swipeContainer);

        arabicNames = new ArrayList<>();
        englishNames = new ArrayList<>();
        idList = new ArrayList<>();
        logoUrls = new ArrayList<>();
        bgUrls = new ArrayList<>();
        sinceList = new ArrayList<>();


        mRv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);

    }

    public void loadRecyclerData() {
        adapter = new RestaurantAdapter(this, sinceList, idList, arabicNames, englishNames, bgUrls, logoUrls, this);
        mRv.setAdapter(adapter);
        Constant.runLayoutAnimation(mRv);

    }


    @Override
    public void getAllRestaurants(List<SelectedCat> restaurants) {

        try {
            adapter.clear();
        } catch (Exception ignored) {

        }


        for (int i = 0; i < restaurants.size(); i++) {
            arabicNames.add(restaurants.get(i).getNameAR());
            englishNames.add(restaurants.get(i).getName());
            sinceList.add(restaurants.get(i).getSince());
            idList.add(restaurants.get(i).getID());
            bgUrls.add(Constant.BASE_URL_HTTP + restaurants.get(i).getCoverImage());
            logoUrls.add(Constant.BASE_URL_HTTP+ restaurants.get(i).getLogo());
        }
        loadRecyclerData();
    }

    @Override
    public void onClick(String id, String englishName, String arabicName, String imgUrl) {
        hideSoftKeyboardAfterSearch();

        //Toast.makeText(this, imgUrl, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SelectedItemKotlinActivity.class);
        intent.putExtra(Constant.ITEM_SELECTED_ID, id);
        intent.putExtra(Constant.ITEM_SELECTED_REST_LOGO, imgUrl.trim());
        intent.putExtra(Constant.ITEM_SELECTED_NAME_EN, englishName);
        intent.putExtra(Constant.ITEM_SELECTED_NAME_AR, arabicName);
        intent.putExtra(Constant.CAT_THAT_SELECTED, getIntent().getStringExtra(Constant.CAT_THAT_SELECTED));
        Log.d("ID_RESTAURANT", "onCreateView: " + id);

        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
    }

    private void hideSoftKeyboardAfterSearch() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextSearch.getWindowToken(), 0);
    }
}
