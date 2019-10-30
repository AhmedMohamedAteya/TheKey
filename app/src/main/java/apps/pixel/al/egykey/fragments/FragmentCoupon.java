package apps.pixel.al.egykey.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;


public class FragmentCoupon extends Fragment {

    private CairoRegularTextView mTxtPrecentage, mTxtAfterScratch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fragment_coupon, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mTxtPrecentage = rootView.findViewById(R.id.txt_precentage);
        mTxtPrecentage.setVisibility(View.GONE);

//        mTxtAfterScratch = rootView.findViewById(R.id.txt_scratch_to);
//
//        mSctarch = rootView.findViewById(R.id.promo_code);
//        mSctarch.setRevealListener(new ScratchTextView.IRevealListener() {
//            @Override
//            public void onRevealed(ScratchTextView tv) {
//                mTxtPrecentage.setVisibility(View.VISIBLE);
//                mTxtAfterScratch.setText(getString(R.string.donee));
//            }
//
//            @Override
//            public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
//
//            }
//        });
    }

}
