package apps.pixel.al.egykey.activites.retaurant.restaurants;

import android.content.Context;
import android.content.SharedPreferences;

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

import static apps.pixel.al.egykey.activites.retaurant.restaurants.RestaurantsActivity.swipeContainer;


public class RestaurantsPresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final RestaurantInterface restaurantInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;


    public RestaurantsPresenter(Context context, RestaurantInterface restaurantInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.restaurantInterface = restaurantInterface;
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

        restaurantInterface.getAllRestaurants(restaurants);
    }

    private void handleResponse(List<Restaurants> restaurants) {
        swipeContainer.setRefreshing(false);

        restaurantInterface.getAllRestaurants(restaurants);
    }

    private void handleError(Throwable throwable) {
        swipeContainer.setRefreshing(false);

        Constant.handleError(context, throwable);
    }

}
