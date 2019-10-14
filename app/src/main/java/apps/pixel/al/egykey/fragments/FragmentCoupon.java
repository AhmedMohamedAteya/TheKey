package apps.pixel.al.egykey.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.pixel.al.egykey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCoupon extends Fragment {


    public FragmentCoupon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_coupon, container, false);
    }

}
