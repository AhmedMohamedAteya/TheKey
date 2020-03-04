package apps.pixel.the.key.fragments.selectedCatHome.coupon;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.fragments.selectedCatHome.jobs.JobsFragment;
import apps.pixel.the.key.fragments.selectedCatHome.jobs.JobsInterface;
import apps.pixel.the.key.models.coupon.CouponModel;
import apps.pixel.the.key.models.job.JobModel;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class CouponPresenter {


    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final CouponInterface couponInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;

    CouponPresenter(Context context, CouponInterface couponInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.couponInterface = couponInterface;
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        dialogLoaderOne = new DialogLoader();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    void getSelectedRestaurantCoupon(String id) {
        if (Validation.isConnected(context)) {
            if (!dialogLoaderOne.isAdded())
            {
              dialogLoaderOne.show(fragmentManager,"show");
            }
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getCoupon(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponse(CouponModel couponModel) {
        if (dialogLoaderOne.isAdded())
            dialogLoaderOne.dismiss();
        couponInterface.getCoupon(couponModel);

    }

    private void handleError(Throwable throwable) {
        if (dialogLoaderOne.isAdded())
            dialogLoaderOne.dismiss();
        Constant.handleError(context, throwable);
    }

}
