package apps.pixel.the.key.fragments.selectedCatHome.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import apps.pixel.the.key.R;
import apps.pixel.the.key.models.ResponseHomeOthers;
import apps.pixel.the.key.models.selectedRestaurant.SelectedRestaurant;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

//import static apps.pixel.the.key.fragments.selectedCatHome.home.HomeRestFragment.mRefreshLayout;
import static apps.pixel.the.key.fragments.selectedCatHome.home.HomeRestFragment.mShimmer;

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
            /*if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(true);
            }*/

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
        /*if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
            mShimmer.stopShimmerAnimation();
        }*/
        homeInterface.getSelectedItemInCatData(selectedRestaurant);
    }

    private void handleError(Throwable throwable) {
        /*if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
            mShimmer.stopShimmerAnimation();
        }
*/

        Constant.handleError(context, throwable);
    }


    //Beauty
    public void getSelectedBeautyCenter(String id) {
        if (Validation.isConnected(context)) {
            /*if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(true);
            }*/

            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllBeautyData(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //call Beauty

    public void getCallsBeauty(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getCallsBeauty(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseBeautyCalls, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseBeautyCalls(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_CALLS", "handleResponseLike: ");

    }

    //SHARE Beauty

    public void getSharesBeauty(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getShareBeauty(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseBeautyShare, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseBeautyShare(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_SHARE", "handleResponseLike: ");
    }


    //UNLIKE Beauty

    public void getUnLikesBeauty(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getUnLikeBeauty(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseBeautyUnlike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseBeautyUnlike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 0);
        editor.apply();

        Log.d("SUCCESS_UNLIKE", "handleResponseLike: ");
    }

    // like beauty
    public void getLikesBeauty(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getLikeBeauty(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseBeautyLike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseBeautyLike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 1);
        editor.apply();


        Log.d("SUCCESS_LIKE", "handleResponseLike: ");

    }

    //GYM
    public void getSelectedGymCenter(String id) {
        if (Validation.isConnected(context)) {
            /*if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(true);
            }
*/
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllGymData(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //call gym

    public void getCallsGym(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getCallsGym(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymCalls, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseGymCalls(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_CALLS", "handleResponseLike: ");

    }

    //SHARE gym

    public void getSharesGym(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getShareGym(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymShare, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseGymShare(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_SHARE", "handleResponseLike: ");
    }


    //UNLIKE gym

    public void getUnLikesGym(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getUnLikeGym(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymUnlike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseGymUnlike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 0);
        editor.apply();

        Log.d("SUCCESS_UNLIKE", "handleResponseLike: ");
    }

    // like gym
    public void getLikesGym(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getLikeGym(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymLike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseGymLike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 1);
        editor.apply();


        Log.d("SUCCESS_LIKE", "handleResponseLike: ");

    }

    //Hospital
    public void getSelectedHospital(String id) {
        if (Validation.isConnected(context)) {
            /*if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(true);
            }*/

            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getHospitalDetails(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //call Hospital

    public void getCallsHospital(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getCallsHospital(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymCalls, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseHospitalCalls(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_CALLS", "handleResponseLike: ");

    }

    //SHARE Hospital

    public void getSharesHospital(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getShareHospital(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymShare, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseHospitalShare(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_SHARE", "handleResponseLike: ");
    }


    //UNLIKE Hospital

    public void getUnLikesHospital(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getUnLikeHospital(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymUnlike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseHospitalUnlike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 0);
        editor.apply();

        Log.d("SUCCESS_UNLIKE", "handleResponseLike: ");
    }

    // like Hospital
    public void getLikesHospital(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getLikeHospital(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymLike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponseHospitalLike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 1);
        editor.apply();


        Log.d("SUCCESS_LIKE", "handleResponseLike: ");

    }

    //pharmacies
    public void getSelectedPharmacy(String id) {
        if (Validation.isConnected(context)) {
           /* if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(true);
            }*/

            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getPharmacyDetails(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }


    //call pharmacies

    public void getCallspharmacies(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getCallsPharmacy(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymCalls, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponsepharmaciesCalls(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_CALLS", "handleResponseLike: ");

    }

    //SHARE pharmacies

    public void getSharespharmacies(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getSharePharmacy(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymShare, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponsepharmaciesShare(ResponseHomeOthers responseHomeOthers) {


        Log.d("SUCCESS_SHARE", "handleResponseLike: ");
    }


    //UNLIKE pharmacies

    public void getUnLikespharmacies(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getUnLikePharmacy(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymUnlike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponsepharmaciesUnlike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 0);
        editor.apply();

        Log.d("SUCCESS_UNLIKE", "handleResponseLike: ");
    }

    // like pharmacies
    public void getLikespharmacies(String id) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getLikePharmacy(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseGymLike, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponsepharmaciesLike(ResponseHomeOthers responseHomeOthers) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.Like_STATUS, 1);
        editor.apply();


        Log.d("SUCCESS_LIKE", "handleResponseLike: ");

    }
}
