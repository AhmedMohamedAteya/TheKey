package apps.pixel.al.egykey.activites.retaurant.jobs.jobDetails;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.dialog.DialogLoader;
import apps.pixel.al.egykey.models.jobDetails.JobDetailsModel;
import apps.pixel.al.egykey.network.NetworkUtil;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.pixel.al.egykey.activites.retaurant.jobs.jobDetails.SelectedJobActivity.swipeContainer;

public class JobDetailsPresenter {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final JobDetailsInterface jobDetailsInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;

    public JobDetailsPresenter(Context context, JobDetailsInterface jobDetailsInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.jobDetailsInterface = jobDetailsInterface;
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        dialogLoaderOne = new DialogLoader();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    void getSeletedJobDetails(String id) {
        if (Validation.isConnected(context)) {
            swipeContainer.setRefreshing(true);
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getJobDetails(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }
    }

    private void handleResponse(JobDetailsModel jobDetailsModel) {
        swipeContainer.setRefreshing(false);

        jobDetailsInterface.getJobDetails(jobDetailsModel);
    }


    private void handleError(Throwable throwable) {
        swipeContainer.setRefreshing(false);

        Constant.handleError(context, throwable);
    }

}
