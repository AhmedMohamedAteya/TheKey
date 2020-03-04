package apps.pixel.the.key.fragments.allOffers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.pixel.the.key.R;

public class AllOffers extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.fragment_all_offers, container, false);

        return view;
    }
}
