package apps.pixel.al.egykey.activites.beautyAndHealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.adapters.restaurant.RestaurantAdapter;
import apps.pixel.al.egykey.utilities.CairoBoldEditText;
import apps.pixel.al.egykey.utilities.Constant;

import static apps.pixel.al.egykey.utilities.Constant.setSwipeLayourColor;

public class SelectedDepartmentActivity extends AppCompatActivity {

    public static SwipeRefreshLayout swipeContainer;
    private RecyclerView mRv;
    private List<String> arabicNames;
    private List<String> idList;
    private List<String> englishNames;
    private List<String> logoUrls;
    private List<String> sinceList;
    private List<String> bgUrls;
    private RestaurantAdapter adapter;
   // private RestaurantsPresenter presenter;

    private AppCompatImageView mImgSearch;
    private CairoBoldEditText mEditTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_department);

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //presenter.getAllRestaurants();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (mEditTextSearch.getVisibility() == View.VISIBLE) {
            mEditTextSearch.setVisibility(View.GONE);

            if (mEditTextSearch.getText().length() > 0)
                mEditTextSearch.getText().clear();

          //  hideSoftKeyboardAfterSearch();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(SelectedDepartmentActivity.this, v);
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

        mEditTextSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mEditTextSearch.setVisibility(View.GONE);

                if (mEditTextSearch.getText().length() > 0)
               //     presenter.searchOnRestaurant(mEditTextSearch.getText().toString().trim());

                if (mEditTextSearch.getText().length() > 0)
                    mEditTextSearch.getText().clear();

            //    hideSoftKeyboardAfterSearch();
            }
            return false;
        });
       // presenter = new RestaurantsPresenter(this, this);
      //  presenter.getAllRestaurants();

      //  swipeContainer.setOnRefreshListener(() -> presenter.getAllRestaurants());

        // Configure the refreshing colors
        setSwipeLayourColor(this, swipeContainer);

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
       // adapter = new RestaurantAdapter(this, sinceList, idList, arabicNames, englishNames, bgUrls, logoUrls, this);
        mRv.setAdapter(adapter);
        Constant.runLayoutAnimation(mRv);

    }

//    @Override
//    public void getAllRestaurants(List<Restaurants> restaurants) {
//
//        try {
//            adapter.clear();
//        } catch (Exception ignored) {
//
//        }
//
//
//        for (int i = 0; i < restaurants.size(); i++) {
//            arabicNames.add(restaurants.get(i).getNameAR());
//            englishNames.add(restaurants.get(i).getName());
//            sinceList.add(restaurants.get(i).getSince());
//            idList.add(restaurants.get(i).getID());
//            bgUrls.add("http://pixelserver-001-site61.ctempurl.com".trim() + restaurants.get(i).getCoverImage());
//            logoUrls.add("http://pixelserver-001-site61.ctempurl.com".trim() + restaurants.get(i).getLogo());
//        }
//        loadRecyclerData();
//    }


//
//    @Override
//    public void onClick(String id) {
//        hideSoftKeyboardAfterSearch();
//
//     //   Intent intent = new Intent(this, SelectedRestaurantKotlinActivity.class);
//    //    intent.putExtra(Constant., id);
//
//        startActivity(intent);
//        Animatoo.animateSwipeLeft(this);
//    }
//
//    private void hideSoftKeyboardAfterSearch() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(mEditTextSearch.getWindowToken(), 0);
//    }

}
