package apps.pixel.the.key.activites;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;
import java.util.Objects;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.Constant;

public class SettingsActivity extends AppCompatActivity {

    private AppCompatImageView arrowBackPageTwo;
    private FrameLayout changePass;
    private FrameLayout changeLang;
    private Switch notifications;
    private FrameLayout aboutUs;
    private FrameLayout complaintAndSuggestions;
    private FrameLayout termsAndCondition;
    private FrameLayout signOut;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
    }

    private void initViews() {
        sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        arrowBackPageTwo = findViewById(R.id.arrow_back_page_two);
        changePass = findViewById(R.id.change_pass);
        changeLang = findViewById(R.id.change_lang);
        notifications = findViewById(R.id.notifications);
        aboutUs = findViewById(R.id.about_us);
        complaintAndSuggestions = findViewById(R.id.complaint_and_suggestions);
        termsAndCondition = findViewById(R.id.terms_and_condition);
        signOut = findViewById(R.id.sign_out);

        changeLang.setOnClickListener(v -> {
            changeLang();
        });

    }

    private void changeLang() {
        Dialog dialog;
        Rect displayRectangle = new Rect();
        Window window = Objects.requireNonNull(getWindow());
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        //  builder                        = new AlertDialog.Builder(getContext());
        @SuppressLint("InflateParams")
        View mview = getLayoutInflater().inflate(R.layout.dialog_change_lang, null);
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(mview);
        dialog.show();
        AppCompatTextView arabic = dialog.findViewById(R.id.arabic);
        AppCompatTextView english = dialog.findViewById(R.id.english);
        Button cancel = dialog.findViewById(R.id.cancel);
        arabic.setOnClickListener(v1 -> {

            Log.d("lang", "onCreate: " + "you click me");
            editor.putString(Constant.LANGUAGE, "ar");
            editor.apply();

            Constant.changeLang(this, Objects.requireNonNull(sharedPreferences.getString(Constant.LANGUAGE, Locale.getDefault().getDisplayLanguage())));


            dialog.dismiss();

            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Animatoo.animateZoom(this);


        });
        english.setOnClickListener(v12 -> {

            Log.d("lang", "onCreate: " + "you click me");

            editor.putString(Constant.LANGUAGE, "en");
            editor.apply();

            Constant.changeLang(this, Objects.requireNonNull(sharedPreferences.getString(Constant.LANGUAGE, Locale.getDefault().getDisplayLanguage())));

            dialog.dismiss();

            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Animatoo.animateZoom(this);


        });
        cancel.setOnClickListener(view -> dialog.cancel());

    }

}
