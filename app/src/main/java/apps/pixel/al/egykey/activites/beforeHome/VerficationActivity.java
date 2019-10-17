package apps.pixel.al.egykey.activites.beforeHome;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.retaurant.restaurants.RestaurantsActivity;
import apps.pixel.al.egykey.utilities.CairoBoldButton;
import apps.pixel.al.egykey.utilities.CairoBoldEditText;
import apps.pixel.al.egykey.utilities.Constant;

public class VerficationActivity extends AppCompatActivity {

    private CairoBoldEditText editText1;
    private CairoBoldEditText editText2;
    private CairoBoldEditText editText3;
    private CairoBoldEditText editText4;

    private String numberOne, numberTwo, numberThree, numberFour;

    private NestedScrollView mNested;
    private CairoBoldButton mBtnContinue;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication);

        initViews();
        handleEditText();

    }

    private void initViews() {
        mBtnContinue = findViewById(R.id.btn_continue);


        mNested = findViewById(R.id.nested);
        mNested.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(VerficationActivity.this, v);
            return true;
        });

        editText1 = findViewById(R.id.txt_one);
        editText2 = findViewById(R.id.txt_two);
        editText3 = findViewById(R.id.txt_three);
        editText4 = findViewById(R.id.txt_four);

        mBtnContinue.setOnClickListener(v -> {
            Intent openHome = new Intent(VerficationActivity.this, RestaurantsActivity.class);
            openHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openHome);
            Animatoo.animateSplit(this);
        });
    }

    private void handleEditText() {
        editText1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText1.length() == 1) {

                    editText1.clearFocus();
                    editText2.requestFocus();
                    editText2.setCursorVisible(true);

                    numberOne = s.toString();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText2.length() == 1) {

                    editText2.clearFocus();
                    editText3.requestFocus();
                    editText3.setCursorVisible(true);

                    numberTwo = s.toString();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText3.length() == 1) {

                    editText3.clearFocus();
                    editText4.requestFocus();
                    editText4.setCursorVisible(true);

                    numberThree = s.toString();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText4.length() == 1) {
                    editText4.clearFocus();

                    numberFour = s.toString();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
    }
}
