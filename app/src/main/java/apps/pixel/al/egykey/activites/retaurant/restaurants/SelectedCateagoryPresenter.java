package apps.pixel.al.egykey.activites.retaurant.restaurants;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.dialog.DialogLoader;
import apps.pixel.al.egykey.models.retaurants.Restaurants;
import apps.pixel.al.egykey.network.NetworkUtil;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.pixel.al.egykey.activites.retaurant.restaurants.SelectedCateagoryActivity.mRv;
import static apps.pixel.al.egykey.activites.retaurant.restaurants.SelectedCateagoryActivity.mTxtNoDAta;
import static apps.pixel.al.egykey.activites.retaurant.restaurants.SelectedCateagoryActivity.swipeContainer;


public class SelectedCateagoryPresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final SelectedCateagoryInterface selectedCateagoryInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;


    public SelectedCateagoryPresenter(Context context, SelectedCateagoryInterface selectedCateagoryInterface) {
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


    private void responseOfSearch(List<Restaurants> restaurants) {
        swipeContainer.setRefreshing(false);

        selectedCateagoryInterface.getAllRestaurants(restaurants);
    }

    private void handleResponse(List<Restaurants> restaurants) {
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

}
