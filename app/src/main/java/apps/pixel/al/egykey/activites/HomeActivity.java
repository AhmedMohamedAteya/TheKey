package apps.pixel.al.egykey.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.hotels.HotelsActivity;
import apps.pixel.al.egykey.activites.retaurant.restaurants.SelectedCateagoryActivity;
import apps.pixel.al.egykey.utilities.Constant;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout itemRestaurant;
    private FrameLayout itemHealth;
    private FrameLayout holtels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

    }

    private void initViews() {
        itemRestaurant = findViewById(R.id.item_restaurant);
        itemRestaurant.setOnClickListener(this);

        itemHealth = findViewById(R.id.item_health);
        itemHealth.setOnClickListener(this);

        holtels = findViewById(R.id.holtels);
        holtels.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_restaurant:
                Intent intent = new Intent(this, SelectedCateagoryActivity.class);
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_RESTAURANT_VALUE);
                startActivity(intent);
                Animatoo.animateSwipeLeft(this);
                break;
            case R.id.item_health:
                Intent healthCats = new Intent(this, CateagoriesActivity.class);
                startActivity(healthCats);
                Animatoo.animateSwipeLeft(this);
                break;
            case R.id.holtels :
                Intent hotelsCats = new Intent(this, HotelsActivity.class);
                startActivity(hotelsCats);
                Animatoo.animateSwipeLeft(this);
                break;


        }
    }
}
