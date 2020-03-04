package apps.pixel.the.key.fragments.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.pixel.the.key.R;

public class Profile extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view =inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }
}
