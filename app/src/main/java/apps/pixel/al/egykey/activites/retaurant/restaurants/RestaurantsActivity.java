package apps.pixel.al.egykey.activites.retaurant.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.retaurant.selectedRestaurant.SelectedRestaurantActivity;
import apps.pixel.al.egykey.adapters.restaurant.RestaurantAdapter;
import apps.pixel.al.egykey.models.retaurants.Restaurants;
import apps.pixel.al.egykey.utilities.Constant;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class RestaurantsActivity extends AppCompatActivity implements RestaurantAdapter.OnClickHandler, RestaurantInterface {

    private RecyclerView mRv;
    private List<String> arabicNames;
    private List<String> idList;
    private List<String> englishNames;
    private List<String> logoUrls;
    private List<String> sinceList;
    private List<String> bgUrls;

    private RestaurantsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_activity_main);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initViews() {

        presenter = new RestaurantsPresenter(this, this);
        presenter.getAllRestaurants();

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
        RestaurantAdapter adapter = new RestaurantAdapter(this, sinceList, idList, arabicNames, englishNames, bgUrls, logoUrls, this);
        mRv.setAdapter(adapter);
        Constant.runLayoutAnimation(mRv);

    }


    @Override
    public void getAllRestaurants(List<Restaurants> restaurants) {
        for (int i = 0; i < restaurants.size(); i++) {
            arabicNames.add(restaurants.get(i).getNameAR());
            englishNames.add(restaurants.get(i).getName());
            sinceList.add(restaurants.get(i).getSince());
            idList.add(restaurants.get(i).getID());
            bgUrls.add("http://pixelserver-001-site61.ctempurl.com".trim() + restaurants.get(i).getCoverImage());
            logoUrls.add("http://pixelserver-001-site61.ctempurl.com".trim() + restaurants.get(i).getLogo());
        }
        loadRecyclerData();
    }

    @Override
    public void onClick(String id) {
        Intent intent = new Intent(this, SelectedRestaurantActivity.class);
        intent.putExtra(Constant.RESTAURANT_SELECTED_ID, id);
        Log.d("ID_RESTAURANT", "onCreateView: " + id);

        startActivity(intent);
        Animatoo.animateFade(this);
    }
}
