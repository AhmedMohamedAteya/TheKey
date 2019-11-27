package apps.pixel.al.egykey.activites.relatedToBasket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.adapters.CurrentAdapter;
import apps.pixel.al.egykey.adapters.PerviousAdapter;
import apps.pixel.al.egykey.utilities.Constant;

public class MyOrdersActivity extends AppCompatActivity implements CurrentAdapter.OnClickHandler, PerviousAdapter.OnClickHandlerPervious {


    private RecyclerView mRVCurrent;
    private CurrentAdapter currentAdapter;
    private List<String> listRestaurantNamesCurrent, listMealNamesCurrent, listDescCurrent, listImgsCurrent, listPricesCurrent;
    private List<String> listRestaurantNamesPervious, listMealNamesPervious, listDescPervious, listImgsPervious, listPricesPervious;

    private RecyclerView mRVPervious;
    private PerviousAdapter perviousAdapter;

    private LinearLayoutCompat itemCurrent;
    private View viewCurrent;
    private LinearLayoutCompat itemPervious;
    private View viewPervious;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        initViews();

    }

    private void initViews() {
        //current
        mRVCurrent = findViewById(R.id.rv_current);
        mRVCurrent.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        loadRecyclerDataCurrent();
        currentAdapter = new CurrentAdapter(this, listRestaurantNamesCurrent, listMealNamesCurrent, listPricesCurrent, listDescCurrent, listImgsCurrent, this);
        mRVCurrent.setAdapter(currentAdapter);
        Constant.runLayoutAnimation(mRVCurrent);

        //Pervious
        mRVPervious = findViewById(R.id.rv_pervious);
        mRVPervious.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        loadRecyclerDataPervious(); //TODO MAKE NEW fun() FOR PERVIOUS LATER
        perviousAdapter = new PerviousAdapter(this, listRestaurantNamesPervious, listMealNamesPervious, listPricesPervious, listDescPervious, listImgsPervious, this);
        mRVPervious.setAdapter(perviousAdapter);
        Constant.runLayoutAnimation(mRVPervious);


        handlingOfSecondToolBar();

    }

    void handlingOfSecondToolBar() {
        itemCurrent = findViewById(R.id.item_current);
        viewCurrent = findViewById(R.id.view_current);


        itemPervious = findViewById(R.id.item_pervious);
        viewPervious = findViewById(R.id.view_pervious);

        mRVPervious.setVisibility(View.GONE);
        viewPervious.setVisibility(View.GONE);
        viewCurrent.setVisibility(View.VISIBLE);
        mRVCurrent.setVisibility(View.VISIBLE);

        itemCurrent.setOnClickListener(v -> {
            viewCurrent.setVisibility(View.VISIBLE);
            mRVCurrent.setVisibility(View.VISIBLE);
            mRVPervious.setVisibility(View.GONE);
            viewPervious.setVisibility(View.GONE);

        });

        itemPervious.setOnClickListener(v -> {
            viewCurrent.setVisibility(View.GONE);
            mRVCurrent.setVisibility(View.GONE);
            mRVPervious.setVisibility(View.VISIBLE);
            viewPervious.setVisibility(View.VISIBLE);

        });


    }

    private void loadRecyclerDataPervious() {
        listRestaurantNamesPervious = new ArrayList<>();
        listMealNamesPervious = new ArrayList<>();
        listPricesPervious = new ArrayList<>();
        listDescPervious = new ArrayList<>();
        listImgsPervious = new ArrayList<>();

        listRestaurantNamesPervious.add("KFC");
        listRestaurantNamesPervious.add("PIZZA HUT");
        listRestaurantNamesPervious.add("KFC");

        listMealNamesPervious.add("COMBO");
        listMealNamesPervious.add("COMBO");
        listMealNamesPervious.add("COMBO");

        listPricesPervious.add("100");
        listPricesPervious.add("100");
        listPricesPervious.add("100");

        listDescPervious.add("Description will be written here");
        listDescPervious.add("Description will be written here");
        listDescPervious.add("Description will be written here");


    }

    private void loadRecyclerDataCurrent() {
        listRestaurantNamesCurrent = new ArrayList<>();
        listMealNamesCurrent = new ArrayList<>();
        listPricesCurrent = new ArrayList<>();
        listDescCurrent = new ArrayList<>();
        listImgsCurrent = new ArrayList<>();

        listRestaurantNamesCurrent.add("KFC");
        listRestaurantNamesCurrent.add("PIZZA HUT");
        listRestaurantNamesCurrent.add("KFC");

        listMealNamesCurrent.add("COMBO");
        listMealNamesCurrent.add("COMBO");
        listMealNamesCurrent.add("COMBO");

        listPricesCurrent.add("100");
        listPricesCurrent.add("100");
        listPricesCurrent.add("100");

        listDescCurrent.add("Description will be written here");
        listDescCurrent.add("Description will be written here");
        listDescCurrent.add("Description will be written here");

    }

    @Override
    public void onItemClickedCurrent(int position) {


    }

    @Override
    public void onItemClickedPervious(int position) {
        startActivity(new Intent(this, OrderDetailsActivity.class));
        Animatoo.animateSwipeLeft(this);
    }
}
