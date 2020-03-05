package apps.pixel.the.key.fragments.selectedCatHome.jobs;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.jobs.jobDetails.SelectedJobActivity;
import apps.pixel.the.key.adapters.restaurant.JobsAdapter;
import apps.pixel.the.key.models.job.JobModel;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;


public class JobsFragment extends Fragment implements JobsAdapter.OnClickHandler, JobsInterface {

    private SharedPreferences sharedPreferences;

    public static CairoBoldTextView mTxtNoData ;

    private RecyclerView mRV;
    private List<String> listDescs;
    private List<String> listTitles;
    private List<String> listSalaries;
    private List<String> imgUrls;
    private List<String> listDates;
    private List<String> idList;

    public static SwipeRefreshLayout swipeContainer;

    private JobsPresenter presenter;

    private String selectedId;

    private CairoRegularTextView mTxtPrecentage, mTxtAfterScratch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jobs, container, false);


        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        mTxtNoData = rootView.findViewById(R.id.txt_no_data);

        sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        selectedId = sharedPreferences.getString(Constant.ITEM_SELECTED_ID, "");

        presenter = new JobsPresenter(getContext(), this);
        presenter.getSelectedRestaurantJobs(selectedId);
        swipeContainer.setOnRefreshListener(() -> presenter.getSelectedRestaurantJobs(selectedId));
        Constant.setSwipeLayourColor(getContext() , swipeContainer);


        idList = new ArrayList<>();
        listDates = new ArrayList<>();
        listDescs = new ArrayList<>();
        listTitles = new ArrayList<>();
        imgUrls = new ArrayList<>();
        listSalaries = new ArrayList<>();

        mRV = rootView.findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRV.setLayoutManager(layoutManager);
        loadRecyclerData();
    }

    private void loadRecyclerData() {
        JobsAdapter adapter = new JobsAdapter(getContext(), idList, listSalaries, listTitles, imgUrls, listDates, this);
        mRV.setAdapter(adapter);
        Constant.runLayoutAnimation(mRV);
    }

    @Override
    public void getAllJobs(List<JobModel> jobModels) {

        for (int i = 0; i < jobModels.size(); i++) {
            //listDescs.add(jobModels.get(i).getAddress());
            listTitles.add(jobModels.get(i).getTitle());
            listSalaries.add((int) jobModels.get(i).getSalary() + "  " + getString(R.string.pound));
            Log.d("DATE_", "getAllJobs: " + jobModels.get(i).getDate());
            if (jobModels.get(i).getDate().equals("1")) {
                listDates.add(jobModels.get(i).getDate() + " " + getString(R.string.day_ago));

            } else {
                listDates.add(jobModels.get(i).getDate() + " " + getString(R.string.days_ago));

            }
            idList.add(jobModels.get(i).getID());
            imgUrls.add(Constant.BASE_URL_HTTP + jobModels.get(i).getImage());
        }

        loadRecyclerData();
    }

    @Override
    public void onClick(int position) {
        Intent openSelectedJop = new Intent(getContext(), SelectedJobActivity.class);
        openSelectedJop.putExtra(Constant.SELECTED_JOB_ID, idList.get(position));
        startActivity(openSelectedJop);
        Animatoo.animateSlideRight(Objects.requireNonNull(getContext()));
    }
}