package apps.pixel.the.key.fragments.selectedCatHome.coupon;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.cooltechworks.views.ScratchImageView;
import com.squareup.picasso.Picasso;

import apps.pixel.the.key.R;
import apps.pixel.the.key.models.coupon.CouponModel;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;


public class FragmentCoupon extends Fragment implements CouponInterface {

    CouponPresenter couponPresenter;
    private SharedPreferences sharedPreferences;

    private CairoRegularTextView mTxtDesc;
    private CairoBoldTextView mCode;

    private LinearLayoutCompat mParentCoupon;
    private CairoBoldTextView mNoData,txt_phone;


    AppCompatImageView scratch_img;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fragment_coupon, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {

        mTxtDesc = rootView.findViewById(R.id.txt_desc);
        txt_phone = rootView.findViewById(R.id.txt_phone);
        mCode = rootView.findViewById(R.id.promo_code);


        mParentCoupon = rootView.findViewById(R.id.parent_coupon);
        scratch_img = rootView.findViewById(R.id.scratch_img);
        mNoData = rootView.findViewById(R.id.no_data);


        sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);

        couponPresenter=new CouponPresenter(getContext(),this);
        couponPresenter.getSelectedRestaurantCoupon(sharedPreferences.getString(Constant.ITEM_SELECTED_ID, ""));
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

    @Override
    public void getCoupon(CouponModel couponModel) {
        if (couponModel.getID().equals("0"))
        {
            mNoData.setVisibility(View.VISIBLE);
            mParentCoupon.setVisibility(View.GONE);
        }
        else
        {
            mNoData.setVisibility(View.GONE);
            mParentCoupon.setVisibility(View.VISIBLE);
            mCode.setText(couponModel.getCode());
            if (Constant.getLng(getContext()).equals("ar"))
            {
                mTxtDesc.setText(couponModel.getDescriptionAR());
            }
            else
            {
                mTxtDesc.setText(couponModel.getDescription());
            }
            txt_phone.setText(couponModel.getPhone());
        }
/*

        ScratchImageView scratchImageView = new ScratchImageView(getContext());
        if (couponModel.getImage()!=null)
        scratchImageView.setImageURI(Uri.parse(Constant.BASE_URL_HTTP+couponModel.getImage()));

        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView tv) {
                // on reveal
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
                // on image percent reveal
            }
        });
*/

        Picasso.get()
                .load(Constant.BASE_URL_HTTP+couponModel.getImage())
                .fit()
                .into(scratch_img);


    }
}
