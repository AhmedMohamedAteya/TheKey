package apps.pixel.the.key.activites.hotels;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.NavUtils;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.Zoom;

public class HotelsActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView mImgBack;
    private LinearLayoutCompat hotel;
    private LinearLayoutCompat tourism;
    private LinearLayoutCompat offers;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSwipeRight(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        initViews();
    }

    private void initViews() {
        mImgBack = findViewById(R.id.back);
        mImgBack.setOnClickListener(this);

        hotel = findViewById(R.id.hotel);
        tourism = findViewById(R.id.tourism);
        offers = findViewById(R.id.offers);

        AppCompatImageView imgBg = findViewById(R.id.background);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        imgBg.startAnimation(animation1);

        new Zoom(this);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HotelsActivity.this, HotelsSelectLocation.class);
//                startActivity(intent);
//                Animatoo.animateSwipeLeft(HotelsActivity.this);
            }
        });

        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HotelHome.this, TourismHome.class);
//                startActivity(intent);
//                Animatoo.animateSwipeLeft(HotelsActivity.this);
            }
        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HotelHome.this, Offers.class);
//                startActivity(intent);
//                Animatoo.animateSwipeLeft(HotelsActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
