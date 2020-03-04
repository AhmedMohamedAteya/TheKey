package apps.pixel.the.key.activites.beautyAndHealth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import apps.pixel.the.key.R;

public class BeautyAndHealthActivty extends AppCompatActivity {

    private AppCompatImageView arrowBackPageTwo;
    private AppCompatImageView icSearch;
    private AppCompatImageView code;
    private CardView hospital;
    private CardView doctor;
    private CardView pharmacy;
    private CardView beauty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_and_health_activty);


        initViews();
    }

    private void initViews() {


        arrowBackPageTwo = findViewById(R.id.arrow_back_page_two);
        icSearch = findViewById(R.id.ic_search);
        code = findViewById(R.id.code);
        hospital = findViewById(R.id.hospital);
        doctor = findViewById(R.id.doctor);
        pharmacy = findViewById(R.id.pharmacy);
        beauty = findViewById(R.id.beauty);

    }
}
