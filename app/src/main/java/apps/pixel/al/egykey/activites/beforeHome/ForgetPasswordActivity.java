package apps.pixel.al.egykey.activites.beforeHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.widget.NestedScrollView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoBoldButton;
import apps.pixel.al.egykey.utilities.Constant;

public class ForgetPasswordActivity extends AppCompatActivity {

    private NestedScrollView mNested;
    private CairoBoldButton mBtnResetPassword;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initViews();
    }

    private void initViews() {
        mNested = findViewById(R.id.nested);
        mNested.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(ForgetPasswordActivity.this, v);
            return true;
        });

        mBtnResetPassword = findViewById(R.id.reset_password);
        mBtnResetPassword.setOnClickListener(v -> {
            startActivity(new Intent(ForgetPasswordActivity.this, VerficationActivity.class));
            Animatoo.animateSwipeLeft(this);
        });
    }
}
