package apps.pixel.the.key.fragments.menu;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.models.menu.MainMenu;
import apps.pixel.the.key.models.menu.SubMenu;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.pixel.the.key.fragments.menu.MenuFragment.mRefreshLayoutMenu;

public class MenuPresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final MenuInterface menuInterface;

    private String selectedId;

    public MenuPresenter(Context context, MenuInterface menuInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.menuInterface = menuInterface;
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void getMenuData(String id) {
        this.selectedId = id;
        if (Validation.isConnected(context)) {
            mRefreshLayoutMenu.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getMainMenu(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseGetMenuData, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }


    private void responseGetMenuData(List<MainMenu> mainMenus) {
        if (mRefreshLayoutMenu.isRefreshing())
            mRefreshLayoutMenu.setRefreshing(false);

        menuInterface.getMenuData(mainMenus);
        getSubMenuData(selectedId, mainMenus.get(0).getID());
    }

    public void getSubMenuData(String id, String subID) {
        if (Validation.isConnected(context)) {
            mRefreshLayoutMenu.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getSubMenu(id, subID)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::responseGetSubMenuData, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void responseGetSubMenuData(List<SubMenu> subMenus) {
        if (mRefreshLayoutMenu.isRefreshing())
            mRefreshLayoutMenu.setRefreshing(false);

        menuInterface.getSubMenuData(subMenus);
    }

    private void handleError(Throwable throwable) {
        if (mRefreshLayoutMenu.isRefreshing())
            mRefreshLayoutMenu.setRefreshing(false);

        Constant.handleError(context, throwable);
    }
}
