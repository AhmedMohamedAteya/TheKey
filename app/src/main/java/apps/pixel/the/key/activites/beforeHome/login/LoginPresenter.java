package apps.pixel.the.key.activites.beforeHome.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.Home.HomeActivity;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.models.externalLogin.RequestModel;
import apps.pixel.the.key.models.login.LoginRequestModel;
import apps.pixel.the.key.models.login.UserModel;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenter {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final FragmentManager fragmentManager;
    private DialogLoader dialogLoader;

    public LoginPresenter(Context context) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    void signIn(String phone, String password) {
        if (Validation.isConnected(context)) {


            if (phone.equals("")) {
                Constant.showErrorDialog(context, context.getString(R.string.email_error));
            } else if (!Patterns.EMAIL_ADDRESS.matcher(phone.trim()).matches()) {
                Constant.showErrorDialog(context, context.getString(R.string.email_inavlid));
            } else if (password.equals("")) {
                Constant.showErrorDialog(context, context.getString(R.string.password_error));
            } else {
                if (!dialogLoader.isAdded()) {
                    dialogLoader.show(fragmentManager, "Loader");
                }
                LoginRequestModel loginRequestModel=new LoginRequestModel();
                loginRequestModel.setEmail(phone);
                loginRequestModel.setPassword(password);
                mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                        .Login(loginRequestModel)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }

    }

    private void handleResponse(UserModel userModel) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(Constant.NAME, signInReponse.getUser().getFullName());
        editor.putString(Constant.Phone, userModel.getMobile());
        editor.putString(Constant.Email, userModel.getEmail());
        editor.putString(Constant.TOKEN, userModel.getToken());
        editor.apply();

        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public void signInWithSocialMedia(RequestModel RequestModel) {
        if (Validation.isConnected(context)) {
            /*if (dialogLoader.isAdded()) {
                return;
            } else {
                dialogLoader.show(fragmentManager, "LOADER");
            }
*/

            if (!dialogLoader.isAdded()) {
                dialogLoader.show(fragmentManager, "Loader");
            }
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .loginSocialMedia(RequestModel)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));

        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }

    }


    private void handleError(Throwable throwable) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        Constant.handleError(context, throwable);
    }


}

