package apps.pixel.al.egykey.activites.beforeHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NavUtils;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoBoldButton;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;
import apps.pixel.al.egykey.utilities.Constant;

public class SignUpActivity extends AppCompatActivity {

    private CairoRegularTextView mTxtLogin;
    private AppCompatImageView mImgBG;
    private CairoBoldButton mBtnRegister;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
    }

    private void initViews() {
        mBtnRegister = findViewById(R.id.btn_register);

        mImgBG = findViewById(R.id.img_bg);
        mImgBG.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(SignUpActivity.this, v);
            return true;
        });

        mTxtLogin = findViewById(R.id.txt_login);
        mTxtLogin.setOnClickListener(v -> {
            onBackPressed();
        });

        mBtnRegister.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, VerficationActivity.class));
            Animatoo.animateSwipeLeft(this);
        });
    }
}
