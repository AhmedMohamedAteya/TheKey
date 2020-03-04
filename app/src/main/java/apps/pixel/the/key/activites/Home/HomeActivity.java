package apps.pixel.the.key.activites.Home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import apps.pixel.the.key.R;
import apps.pixel.the.key.fragments.allOffers.AllOffers;
import apps.pixel.the.key.fragments.home.HomeFragment;
import apps.pixel.the.key.fragments.profile.Profile;
import apps.pixel.the.key.fragments.settings.Settings;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, null).commit();
                return true;
            case R.id.navigation_all_orders:
                fragment = new AllOffers();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, null).commit();
                return true;
            case R.id.navigation_profile:
                fragment = new Profile();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, null).commit();
                return true;
            case R.id.navigation_settings:
                fragment = new Settings();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, null).commit();
                return true;

        }

        return false;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }

    private void initViews() {

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment(), null).commit();

    }
}
