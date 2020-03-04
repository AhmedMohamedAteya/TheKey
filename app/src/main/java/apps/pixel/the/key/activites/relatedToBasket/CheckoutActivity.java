package apps.pixel.the.key.activites.relatedToBasket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoBoldButton;

public class CheckoutActivity extends AppCompatActivity {

    private CairoBoldButton placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initViews();
    }

    private void initViews() {
        placeOrder = findViewById(R.id.place_order);
        placeOrder.setOnClickListener(v -> {
            startActivity(new Intent(CheckoutActivity.this, ViewOrderActivity.class));
            Animatoo.animateSwipeLeft(CheckoutActivity.this);
        });
    }
}
