package apps.pixel.the.key.fragments.selectedCatHome.jobs;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.models.job.JobModel;
import apps.pixel.the.key.network.NetworkUtil;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.Validation;
import apps.pixel.the.key.fragments.selectedCatHome.jobs.JobsFragment;
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
            JobsFragment.swipeContainer.setRefreshing(true);
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
        JobsFragment.swipeContainer.setRefreshing(false);
        JobsFragment.mTxtNoData.setVisibility(View.GONE);
        jobsInterface.getAllJobs(jobModels);

        try {
            jobModels.get(0).getID();
        } catch (Exception ignored) {
            JobsFragment.mTxtNoData.setVisibility(View.VISIBLE);
        }
    }

    private void handleError(Throwable throwable) {
        JobsFragment.swipeContainer.setRefreshing(false);
        JobsFragment.mTxtNoData.setVisibility(View.VISIBLE);
        Constant.handleError(context, throwable);
    }

}
