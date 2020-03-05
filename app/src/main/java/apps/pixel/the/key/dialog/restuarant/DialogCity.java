package apps.pixel.the.key.dialog.restuarant;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.jobs.applicationJob.ApplicationJobActivity;
import apps.pixel.the.key.activites.retaurant.jobs.applicationJob.ApplicationJobInterface;
import apps.pixel.the.key.activites.retaurant.jobs.applicationJob.ApplicationJobPresenter;
import apps.pixel.the.key.adapters.restaurant.DialogCityAdapter;
import apps.pixel.the.key.models.cities.cityModel;
import apps.pixel.the.key.utilities.Constant;

public class DialogCity extends DialogFragment implements DialogCityAdapter.OnClickHandler , ApplicationJobInterface {
    private List<String> listDAta,listIDS;
    private RecyclerView mRV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_cities, container, true);
        if (getDialog() != null && getDialog().getWindow() != null) {

            getDialog().setCanceledOnTouchOutside(true);
            getDialog().setCancelable(true);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }


        initViews(view);


        return view;
    }

    private void initViews(View view) {
        mRV = view.findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRV.setLayoutManager(layoutManager);
        loadRecyclerData();
        ApplicationJobPresenter applicationJobPresenter=new ApplicationJobPresenter(getContext(),this);
        applicationJobPresenter.getCity();

    }

    private void loadRecyclerData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commit();
        } catch (IllegalStateException e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }

    }

    @Override
    public void onClick(int position) {
        ApplicationJobActivity.txtCity.setText(listIDS.get(position));
        dismiss();
    }

    @Override
    public void getCities(List<cityModel> cityModels) {
        listDAta = new ArrayList<>();
        listIDS = new ArrayList<>();

        for (int i=0;i<cityModels.size();i++)
        {
            listDAta.add(cityModels.get(i).getName());
            listIDS.add(cityModels.get(i).getId());
        }

        DialogCityAdapter adapter = new DialogCityAdapter(getContext(), listDAta, listIDS, this);
        mRV.setAdapter(adapter);
        Constant.runLayoutAnimation(mRV);
    }
}




