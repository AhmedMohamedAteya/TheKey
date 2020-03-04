package apps.pixel.the.key.activites.retaurant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoBoldButton;

public class SortByActivity extends AppCompatActivity {

    private AppCompatImageView arrowBackPageTwo;
    private AppCompatRadioButton radioBtnOffers;
    private AppCompatRadioButton radioBtnDelivery;
    private AppCompatRadioButton radioBtnNearBy;
    private AppCompatRadioButton radioBtnRating;
    private CairoBoldButton btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_by);

        initViews();
    }

    private void initViews() {


        arrowBackPageTwo = findViewById(R.id.arrow_back_page_two);
        radioBtnOffers = findViewById(R.id.radio_btn_offers);
        radioBtnDelivery = findViewById(R.id.radio_btn_delivery);
        radioBtnNearBy = findViewById(R.id.radio_btn_near_by);
        radioBtnRating = findViewById(R.id.radio_btn_rating);
        btnDone = findViewById(R.id.btn_done);

    }
}
