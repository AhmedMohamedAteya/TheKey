package apps.pixel.the.key.fragments.selectedCatHome.offers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.models.offers.OffersModel;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class OffersPresenter {


    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final OffersInterface jobsInterface;
   // private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;

    OffersPresenter(Context context, OffersInterface offersInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.jobsInterface = offersInterface;
       // fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        dialogLoaderOne = new DialogLoader();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    void getSelectedRestaurantOffers() {
        if (Validation.isConnected(context)) {
            //swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllOffers(sharedPreferences.getString(Constant.ITEM_SELECTED_ID,""))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponse(List<OffersModel> jobModels) {
       // swipeContainer.setRefreshing(false);
        jobsInterface.getAllOffers(jobModels);
    }

    private void handleError(Throwable throwable) {
       // swipeContainer.setRefreshing(false);
        Constant.handleError(context, throwable);
    }

}
