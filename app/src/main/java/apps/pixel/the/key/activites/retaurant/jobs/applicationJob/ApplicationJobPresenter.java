package apps.pixel.the.key.activites.retaurant.jobs.applicationJob;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.restaurants.SelectedCateagoryInterface;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.models.retaurants.SelectedCat;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.pixel.the.key.activites.retaurant.restaurants.SelectedCateagoryActivity.mRv;
import static apps.pixel.the.key.activites.retaurant.restaurants.SelectedCateagoryActivity.mTxtNoDAta;
import static apps.pixel.the.key.activites.retaurant.restaurants.SelectedCateagoryActivity.swipeContainer;


public class ApplicationJobPresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final SelectedCateagoryInterface selectedCateagoryInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;


    public ApplicationJobPresenter(Context context, SelectedCateagoryInterface selectedCateagoryInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.selectedCateagoryInterface = selectedCateagoryInterface;
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        fragmentManager.executePendingTransactions();
        dialogLoaderOne = new DialogLoader();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }


    public void searchOnRestaurant(String partOnName) {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .searchOnRestaurant(partOnName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseOfSearch, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    public void getAllRestaurants() {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllRestaurants()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }


    private void responseOfSearch(List<SelectedCat> restaurants) {
        swipeContainer.setRefreshing(false);

        selectedCateagoryInterface.getAllRestaurants(restaurants);
    }

    private void handleResponse(List<SelectedCat> restaurants) {
        swipeContainer.setRefreshing(false);
        mTxtNoDAta.setVisibility(View.GONE);
        mRv.setVisibility(View.VISIBLE);
        selectedCateagoryInterface.getAllRestaurants(restaurants);
    }

    private void handleError(Throwable throwable) {
        mTxtNoDAta.setVisibility(View.VISIBLE);
        mRv.setVisibility(View.GONE);

        swipeContainer.setRefreshing(false);

        Constant.handleError(context, throwable);
    }


    //Beauty
    void getAllBeautyData() {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllBeautyCenterData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }


    public void searchOnBeauty(String partOnName) {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .searchOnBeauty(partOnName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseOfSearch, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //Gym
    public void getAllGymData() {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllGyms()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    public void searchOnGym(String partOnName) {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .searchOnGym(partOnName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseOfSearch, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //hospital
    public void getAllHospitalData() {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllHospital()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    public void searchOnHospital(String partOnName) {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .searchOnHospital(partOnName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseOfSearch, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    //pharmacies
    public void getAllPharmaciesData() {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllPharmacy()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    public void searchOnPharmacy(String partOnName) {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .searchOnPharmacy(partOnName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }


}
