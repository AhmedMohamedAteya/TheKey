package apps.pixel.al.egykey.activites.beforeHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.retaurant.restaurants.RestaurantsActivity;
import apps.pixel.al.egykey.utilities.CairoBoldButton;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;
import apps.pixel.al.egykey.utilities.Constant;

public class SignInActivity extends AppCompatActivity {

    private NestedScrollView mNested;
    private CairoRegularTextView mTxtRegister, mTxtForgotPassword;
    private CairoBoldButton mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
    }

    private void initViews() {
        mBtnLogin = findViewById(R.id.btn_login);

        mNested = findViewById(R.id.nested);
        mNested.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(SignInActivity.this, v);
            return true;
        });

        mTxtRegister = findViewById(R.id.txt_register);
        mTxtRegister.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            Animatoo.animateSwipeLeft(this);
        });

        mTxtForgotPassword = findViewById(R.id.txt_forgt_password);
        mTxtForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, ForgetPasswordActivity.class));

            Animatoo.animateSwipeLeft(this);

        });

        mBtnLogin.setOnClickListener(v -> {
            Intent openHome = new Intent(SignInActivity.this, RestaurantsActivity.class);
            openHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openHome);
            Animatoo.animateSplit(this);
        });

    }
}
