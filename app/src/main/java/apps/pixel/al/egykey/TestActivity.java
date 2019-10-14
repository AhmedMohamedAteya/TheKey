package apps.pixel.al.egykey;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import apps.pixel.al.egykey.utilities.CairoBoldButton;

public class TestActivity extends AppCompatActivity {


    private CairoBoldButton mBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(v -> {

        });
    }

}
