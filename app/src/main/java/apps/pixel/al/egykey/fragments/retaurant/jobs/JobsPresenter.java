package apps.pixel.al.egykey.fragments.retaurant.jobs;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.dialog.DialogLoader;
import apps.pixel.al.egykey.models.job.JobModel;
import apps.pixel.al.egykey.network.NetworkUtil;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

 class JobsPresenter {


    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final JobsInterface jobsInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;

     JobsPresenter(Context context, JobsInterface jobsInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.jobsInterface = jobsInterface;
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        dialogLoaderOne = new DialogLoader();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    void getSelectedRestaurantJobs(String id) {
        if (Validation.isConnected(context)) {
            dialogLoaderOne.show(fragmentManager, "");
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getAllJobs(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponse(List<JobModel> jobModels) {
        if (dialogLoaderOne.isAdded()) {
            dialogLoaderOne.dismiss();
        }
        jobsInterface.getAllJobs(jobModels);

    }

    private void handleError(Throwable throwable) {
        if (dialogLoaderOne.isAdded()) {
            dialogLoaderOne.dismiss();
        }
        Constant.handleError(context, throwable);
    }

}
