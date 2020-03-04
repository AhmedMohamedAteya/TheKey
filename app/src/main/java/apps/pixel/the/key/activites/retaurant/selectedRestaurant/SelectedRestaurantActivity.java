package apps.pixel.the.key.activites.retaurant.selectedRestaurant;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NavUtils;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import apps.pixel.the.key.R;
import apps.pixel.the.key.fragments.selectedCatHome.coupon.FragmentCoupon;
import apps.pixel.the.key.fragments.selectedCatHome.offers.OffersFramgent;
import apps.pixel.the.key.fragments.selectedCatHome.home.HomeRestFragment;
import apps.pixel.the.key.fragments.selectedCatHome.jobs.JobsFragment;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.CurvedBottomNavigationView;

public class SelectedRestaurantActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private String selectedResId;

    private CurvedBottomNavigationView mView;
    private AppCompatImageView mHome;
    private AppCompatImageView mImgForCoupon;
    private AppCompatImageView mImgBack;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_restaurant);

        selectedResId = getIntent().getStringExtra(Constant.ITEM_SELECTED_ID);

        initViews();

    }

    private void initViews() {
        mImgBack = findViewById(R.id.arrow_back_page_two);


        mImgBack.setOnClickListener(v -> onBackPressed());

        mImgForCoupon = findViewById(R.id.img_for_coupon_only);
        mImgForCoupon.setVisibility(View.GONE);

        mHome = findViewById(R.id.img_home);
        mHome.setOnClickListener(this);

        mView = findViewById(R.id.curved_bottom);
        mView.inflateMenu(R.menu.bottom_menu);

        handleHomeClick();

        mView.setSelectedItemId(R.id.nav_home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(SelectedRestaurantActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        handleHomeClick();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_jobs:
                JobsFragment jobsFragment=new JobsFragment();
                Bundle bundleJobs = new Bundle();
                bundleJobs.putString(Constant.ITEM_SELECTED_ID, selectedResId);
                jobsFragment.setArguments(bundleJobs);
                mImgForCoupon.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, jobsFragment)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).addToBackStack(null).commit();
                break;
            case R.id.nav_copoun:
                FragmentCoupon fragmentCoupon=new FragmentCoupon();
                Bundle bundleCoupon = new Bundle();
                bundleCoupon.putString(Constant.ITEM_SELECTED_ID, selectedResId);
                fragmentCoupon.setArguments(bundleCoupon);
                mImgForCoupon.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentCoupon)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).addToBackStack(null).commit();
                break;
            case R.id.nav_home:
                handleHomeClick();
                break;
            case R.id.nav_egy_key:

                mImgForCoupon.setVisibility(View.GONE);
                break;
            case R.id.nav_offers:
                mImgForCoupon.setVisibility(View.GONE);
                OffersFramgent offersFramgent=new OffersFramgent();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.ITEM_SELECTED_ID, selectedResId);
                offersFramgent.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, offersFramgent)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).addToBackStack(null).commit();
                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_home:
                handleHomeClick();
                break;
        }
    }

    private void handleHomeClick() {
        mImgForCoupon.setVisibility(View.GONE);
        HomeRestFragment homeRestFragment = new HomeRestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ITEM_SELECTED_ID, selectedResId);
        homeRestFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeRestFragment).addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).commit();
    }
}
