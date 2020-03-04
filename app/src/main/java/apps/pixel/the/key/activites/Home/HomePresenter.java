package apps.pixel.the.key.activites.Home;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.models.home.sliderResponse;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class HomePresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final HomeSliderInterface homeInterface;
//    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;


    public HomePresenter(Context context, HomeSliderInterface homeInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.homeInterface = homeInterface;
        /*fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        fragmentManager.executePendingTransactions();*/
        dialogLoaderOne = new DialogLoader();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void getSliders() {
        if (Validation.isConnected(context)) {
            //swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getSliders()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseOfSearch, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }


    private void responseOfSearch(List<sliderResponse> sliderResponses) {
        //swipeContainer.setRefreshing(false);

        homeInterface.getSliders(sliderResponses);
    }

    private void handleError(Throwable throwable) {
        Constant.handleError(context, throwable);
    }
}
