package apps.pixel.the.key.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.Home.HomePresenter;
import apps.pixel.the.key.activites.Home.HomeSliderInterface;
import apps.pixel.the.key.activites.retaurant.restaurants.SelectedCateagoryActivity;
import apps.pixel.the.key.adapters.homeSlider.HomeSliderAdapter;
import apps.pixel.the.key.fragments.selectedCatHome.home.HomeInterface;
import apps.pixel.the.key.models.home.sliderResponse;
import apps.pixel.the.key.models.selectedRestaurant.SelectedRestaurant;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.ViewPagerCustomDuration;

public class HomeFragment extends Fragment implements HomeSliderInterface {


    private View view;
    private LinearLayoutCompat itemRestaurant;
    private LinearLayoutCompat itemHealth;
    private LinearLayoutCompat holtels;

    private ViewPagerCustomDuration viewPager;
    private TabLayout indicator;
    private HomeSliderAdapter sliderAdapter;
    private List<String> imageViewPagerList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        view=inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        return view;
    }

    private void initView()
    {
        imageViewPagerList = new ArrayList<>();

        viewPager = view.findViewById(R.id.viewPager);
        indicator = view.findViewById(R.id.indicator);
        viewPager.setScrollDurationFactor(1.5);
        indicator.setupWithViewPager(viewPager, true);

        itemRestaurant = view.findViewById(R.id.item_restaurant);
        itemRestaurant.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SelectedCateagoryActivity.class);
            intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_RESTAURANT_VALUE);
            startActivity(intent);
            Animatoo.animateSwipeLeft(getContext());
        });

        itemHealth = view.findViewById(R.id.item_beauty);
        itemHealth.setOnClickListener(v -> {

        });

        HomePresenter homePresenter=new HomePresenter(getContext(),this);
        homePresenter.getSliders();
        holtels = view.findViewById(R.id.item_hotels);
        holtels.setOnClickListener(v ->{

        });
    }


    @Override
    public void getSliders(List<sliderResponse> sliderResponses) {
        List<String> IDS=new ArrayList<>();
        List<String> listLogo=new ArrayList<>();
        List<String> listNameAR=new ArrayList<>();
        List<String> listNameEn=new ArrayList<>();
        for (int i = 0; i < sliderResponses.size(); i++)
        {
            imageViewPagerList.add(Constant.BASE_URL_HTTP+sliderResponses.get(i).getImageUrl());
            listLogo.add(Constant.BASE_URL_HTTP+sliderResponses.get(i).getCategoryLogo());
            IDS.add(sliderResponses.get(i).getSubCategoryId());
            listNameAR.add(sliderResponses.get(i).getCategoryNameAr());
            listNameEn.add(sliderResponses.get(i).getCategoryNameEn());
        }
        sliderAdapter = new HomeSliderAdapter(getContext(), imageViewPagerList, listLogo, listNameAR, listNameEn,IDS);
        viewPager.setAdapter(sliderAdapter);
    }
}
