package apps.pixel.the.key.activites.IntroSlider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.Home.HomeActivity;
import apps.pixel.the.key.activites.beforeHome.login.SignInActivity;
import apps.pixel.the.key.adapters.slider.TutorialsSliderAdapter;
import apps.pixel.the.key.utilities.CairoBoldButton;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.ViewPagerCustomDuration;

public class IntroSlider extends AppCompatActivity {

    private ViewPagerCustomDuration viewPager;
    private TabLayout indicator;
    private TutorialsSliderAdapter sliderAdapter;
    private List<Drawable> imageViewPagerList;
    private CairoRegularTextView btn_lets_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Constant.hideStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);

        if (mSharedPreferences.getString(Constant.FlagIntroSlider, "false").equals("true"))
        {
            if (mSharedPreferences.getString(Constant.TOKEN, "").equals(""))
            {
                Intent startIntent = new Intent(this, SignInActivity.class);
                startActivity(startIntent);
                startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                Animatoo.animateSplit(this);
            }
            else
            {
                Intent startIntent = new Intent(this, HomeActivity.class);
                startActivity(startIntent);
                startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                Animatoo.animateSplit(this);
            }
        }
        else
        {
            initViews();
        }
    }

    private void initViews() {
        viewPager = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);
        btn_lets_go = findViewById(R.id.btn_lets_go);
        viewPager.setScrollDurationFactor(1.5);

        loadSliderData();

        sliderAdapter = new TutorialsSliderAdapter(this, imageViewPagerList);
        indicator.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(sliderAdapter);

        Log.d("POSITION_", "initViews: " + viewPager.getCurrentItem());


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == sliderAdapter.getCount() - 1) {
                    btn_lets_go.setVisibility(View.VISIBLE);
                    btn_lets_go.setOnClickListener(v -> {

                        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        //editor.putString(Constant.NAME, signInReponse.getUser().getFullName());
                        editor.putString(Constant.FlagIntroSlider, "true");
                        editor.apply();

                        Intent openHome = new Intent(IntroSlider.this, SignInActivity.class);
                        finish();
                        openHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(openHome);
                        Animatoo.animateSplit(IntroSlider.this);
                    });
                } else {
                    btn_lets_go.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void loadSliderData() {
        imageViewPagerList = new ArrayList<>();
        imageViewPagerList.add(getResources().getDrawable(R.drawable.intro_1));
        imageViewPagerList.add(getResources().getDrawable(R.drawable.intro_2));
        imageViewPagerList.add(getResources().getDrawable(R.drawable.intro_3));
        imageViewPagerList.add(getResources().getDrawable(R.drawable.intro_4));
        imageViewPagerList.add(getResources().getDrawable(R.drawable.intro_5));
        imageViewPagerList.add(getResources().getDrawable(R.drawable.intro_6));

        /*titleList = new ArrayList<>();
        titleList.add("نرحب بيك فى مكوك ");
        titleList.add("أفضل العروض و الأسعار ");
        titleList.add("كن على إتصال ");
        titleList.add("رحلة تتبع طلبك ");
        titleList.add("وظائف شاغرة ");

        descList = new ArrayList<>();
        descList.add("كل الخدمات فى مكان واحد إختر الخدمة من القائمة" +
                "أو إستخدم خيار البحث");
        descList.add("إبحث عن العروض الأفضل لك .");
        descList.add("تحدث مع موصل طلبك مباشرة .. " +
                "للتعرف على حالة الطلب ");
        descList.add("ضع طلبك بإختيار أطلب الأن" +
                "اطلب أى حاجة توصلك فى اى حتة");
        descList.add("يمكنك إختيار خدمة ( طيار مكوك )");*/
    }
}
