package apps.pixel.al.egykey.activites.relatedToBasket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoBoldButton;

public class ViewOrderActivity extends AppCompatActivity {

    private CairoBoldButton viewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        initViews();
    }

    private void initViews() {
        viewOrder = findViewById(R.id.view_order);
        viewOrder.setOnClickListener(v -> {
            startActivity(new Intent(ViewOrderActivity.this, MyOrdersActivity.class));
            Animatoo.animateSwipeLeft(ViewOrderActivity.this);
        });

    }
}