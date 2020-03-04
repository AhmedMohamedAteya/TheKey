package apps.pixel.the.key.activites.beforeHome.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.Home.HomeActivity;
import apps.pixel.the.key.activites.beforeHome.login.SignInActivity;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.models.login.UserModel;
import apps.pixel.the.key.models.register.RegisterRequestModel;
import apps.pixel.the.key.models.register.RegisterResponseModel;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RegisterPresenter {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final FragmentManager fragmentManager;
    private DialogLoader dialogLoader;
    private String password;

    public RegisterPresenter(Context context) {
        this.context = context;
        dialogLoader = new DialogLoader();
        mSubscriptions = new CompositeSubscription();
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void register(
              String name
            , String email
            , String phone
            , String Address
            , String password
            , String ConfirmPassword
            , String Image
    ) {
        if (Validation.isConnected(context)) {

            if (Validation.validateFields(name)) {
                Constant.showErrorDialog(context, context.getString(R.string.name_error));
            } else if (Validation.validateFields(email)) {
                Constant.showErrorDialog(context, context.getString(R.string.email_error));
            } else if (Validation.validateFields(phone)) {
                Constant.showErrorDialog(context, context.getString(R.string.phone_error));
            } else if (Validation.validateFields(Address)) {
                Constant.showErrorDialog(context, context.getString(R.string.address_error));
            } /*else if (Validation.validateFields(Image)) {
                Constant.showErrorDialog(context, context.getString(R.string.img_error));
            }*/else if (Validation.validateFields(password)) {
                Constant.showErrorDialog(context, context.getString(R.string.password_error));
            } else if (Validation.validateFields(ConfirmPassword)) {
                Constant.showErrorDialog(context, context.getString(R.string.confirm_error));
            } else if (!password.equals(ConfirmPassword)) {
                Constant.showErrorDialog(context, context.getString(R.string.Password_does_not_match));
            } else {
                RegisterRequestModel registerRequestModel = new RegisterRequestModel();

                registerRequestModel.setName(name);
                registerRequestModel.setEmail(email);
                registerRequestModel.setMobile(phone);
                registerRequestModel.setAddress(Address);
                registerRequestModel.setPassword(password);
                registerRequestModel.setConfirmPassword(ConfirmPassword);
                registerRequestModel.setImage(Image);

                if (!dialogLoader.isAdded()) {
                    dialogLoader.show(fragmentManager, "Loader");
                }

                mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                        .Register(registerRequestModel)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
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
        /*Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();*/
        Constant.showSuccessVERFIED(context,context.getResources().getString(R.string.account_created_successfully), HomeActivity.class);
    }
}
