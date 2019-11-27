package apps.pixel.al.egykey.activites.relatedToBasket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;

public class AddressActivity extends AppCompatActivity {

    private CairoRegularTextView mTxtSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        initViews();
    }

    private void initViews() {
        mTxtSave = findViewById(R.id.btn_save);
        mTxtSave.setOnClickListener(v -> {
            startActivity(new Intent(AddressActivity.this, CheckoutActivity.class));
            Animatoo.animateSwipeLeft(AddressActivity.this);
        });
    }
}
