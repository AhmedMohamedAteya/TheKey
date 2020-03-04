package apps.pixel.the.key.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NavUtils;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.restaurants.SelectedCateagoryActivity;
import apps.pixel.the.key.utilities.Constant;

public class CateagoriesActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView code;
    private FrameLayout catClinic;
    private FrameLayout catPharmacy;
    private FrameLayout catHospital;
    private FrameLayout catBeauty;
    private FrameLayout catGym;

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
        setContentView(R.layout.activity_cateagories);

        initViews();
    }

    private void initViews() {

        mImgBack = findViewById(R.id.arrow_back_page_two);
        mImgBack.setOnClickListener(v -> {
            onBackPressed();
        });

        code = findViewById(R.id.code);

        catClinic = findViewById(R.id.cat_clinic);
        catClinic.setOnClickListener(this);

        catPharmacy = findViewById(R.id.cat_pharmacy);
        catPharmacy.setOnClickListener(this);

        catBeauty = findViewById(R.id.cat_beauty);
        catBeauty.setOnClickListener(this);

        catGym = findViewById(R.id.cat_gym);
        catGym.setOnClickListener(this);

        catHospital = findViewById(R.id.cat_hospital);
        catHospital.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectedCateagoryActivity.class);
        switch (v.getId()) {
//            case R.id.cat_restaurant:
//                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_RESTAURANT_VALUE);
//                startActivity(intent);
//                Animatoo.animateSwipeLeft(this);
//                break;
//            case R.id.cat_clinic:
//                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_CLINIC_VALUE);
//                startActivity(intent);
//                Animatoo.animateSwipeLeft(this);
//                break;
            case R.id.cat_pharmacy:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_PHARMACY_VALUE);
                startActivity(intent);
                Animatoo.animateSwipeLeft(this);
                break;
            case R.id.cat_beauty:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_BEAUTY_VALUE);
                startActivity(intent);
                Animatoo.animateSwipeLeft(this);
                break;
            case R.id.cat_gym:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_GYM_VALUE);
                startActivity(intent);
                Animatoo.animateSwipeLeft(this);
                break;
            case R.id.cat_hospital:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_HOSPITAL_VALUE);
                startActivity(intent);
                Animatoo.animateSwipeLeft(this);
                break;
        }
    }
}
