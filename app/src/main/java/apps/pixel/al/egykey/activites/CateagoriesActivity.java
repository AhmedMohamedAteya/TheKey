package apps.pixel.al.egykey.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.retaurant.restaurants.SelectedCateagoryActivity;
import apps.pixel.al.egykey.utilities.Constant;

public class CateagoriesActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView code;
    private FrameLayout catHospital;
    private FrameLayout catClinic;
    private FrameLayout catPharmacy;
    private FrameLayout catBeauty;
    private FrameLayout catGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cateagories);

        initViews();
    }

    private void initViews() {

        code = findViewById(R.id.code);

        catHospital = findViewById(R.id.cat_hospital);
        catHospital.setOnClickListener(this);

        catClinic = findViewById(R.id.cat_clinic);
        catClinic.setOnClickListener(this);

        catPharmacy = findViewById(R.id.cat_pharmacy);
        catPharmacy.setOnClickListener(this);

        catBeauty = findViewById(R.id.cat_beauty);
        catBeauty.setOnClickListener(this);

        catGym = findViewById(R.id.cat_gym);
        catGym.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectedCateagoryActivity.class);
        switch (v.getId()) {
            case R.id.cat_hospital:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_HOSPITAL_VALUE);
                startActivity(intent);
                break;
            case R.id.cat_clinic:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_CLINIC_VALUE);
                startActivity(intent);
                break;
            case R.id.cat_pharmacy:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_PHARMACY_VALUE);
                startActivity(intent);
                break;
            case R.id.cat_beauty:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_BEAUTY_VALUE);
                startActivity(intent);
                break;
            case R.id.cat_gym:
                intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_GYM_VALUE);
                startActivity(intent);
                break;
        }
    }
}
