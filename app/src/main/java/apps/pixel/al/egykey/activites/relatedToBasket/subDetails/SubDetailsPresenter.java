package apps.pixel.al.egykey.activites.relatedToBasket.subDetails;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.models.menu.MainMenu;
import apps.pixel.al.egykey.models.menu.SubMenuDetails;
import apps.pixel.al.egykey.network.NetworkUtil;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.pixel.al.egykey.activites.relatedToBasket.subDetails.ViewSelectedItemActivity.swipeRefreshLayoutSubDetails;

public class SubDetailsPresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final SubDetailsInterface subDetailsInterface;

    public SubDetailsPresenter(Context context, SubDetailsInterface subDetailsInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.subDetailsInterface = subDetailsInterface;
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void getSubMenuDetailsData(String id) {
        if (Validation.isConnected(context)) {
          //  swipeRefreshLayoutSubDetails.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getSubMenuDetails(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseGetSubMenuData, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void responseGetSubMenuData(SubMenuDetails mainMenus) {
//        if (swipeRefreshLayoutSubDetails.isRefreshing()) {
//            swipeRefreshLayoutSubDetails.setRefreshing(false);
//        }

        subDetailsInterface.getSubDetails(mainMenus);

    }

    private void handleError(Throwable throwable) {
//        if (swipeRefreshLayoutSubDetails.isRefreshing()) {
//            swipeRefreshLayoutSubDetails.setRefreshing(false);
//        }

        Constant.handleError(context, throwable);
    }
}
