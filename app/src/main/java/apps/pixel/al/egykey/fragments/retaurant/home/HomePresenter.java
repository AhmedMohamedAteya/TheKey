package apps.pixel.al.egykey.fragments.retaurant.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.models.ResponseHomeOthers;
import apps.pixel.al.egykey.models.selectedRestaurant.SelectedRestaurant;
import apps.pixel.al.egykey.network.NetworkUtil;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.pixel.al.egykey.fragments.retaurant.home.HomeRestFragment.mRefreshLayout;
import static apps.pixel.al.egykey.fragments.retaurant.home.HomeRestFragment.mShimmer;

public class HomePresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final HomeInterface homeInterface;
    private final FragmentManager fragmentManager;


    public HomePresenter(Context context, HomeInterface homeInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.homeInterface = homeInterface;
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void getSelectedRestaurant(String id) {
        if (Validation.isConnected(context)) {
            if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(true);
            }

            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getSelectedRestaurant(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //LIKE
    public void getLikes(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getFavourite(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseLike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseLike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 1);
        editor.apply();


        Log.d("SUCCESS_LIKE", "handleResponseLike: ");

    }

    //UNLIKE

    public void getUnLikes(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getUnLike(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseUnlike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseUnlike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 0);
        editor.apply();

        Log.d("SUCCESS_UNLIKE", "handleResponseLike: ");
    }


    //SHARE

    public void getShares(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getShare(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseShare, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseShare(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_SHARE", "handleResponseLike: ");
    }


    //CALL

    public void getCalls(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getCalls(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseCalls, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseCalls(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_CALLS", "handleResponseLike: ");

    }


    //_________
    private void handleResponse(SelectedRestaurant selectedRestaurant) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
            mShimmer.stopShimmerAnimation();
        }
        homeInterface.getSelectedRestaurantData(selectedRestaurant);
    }

    private void handleError(Throwable throwable) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
            mShimmer.stopShimmerAnimation();
        }


        Constant.handleError(context, throwable);
    }


}
