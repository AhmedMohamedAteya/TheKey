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
import apps.pixel.the.key.activites.retaurant.jobs.ApplicationJobActivity;
import apps.pixel.the.key.adapters.restaurant.DialogExperienceAdapter;
import apps.pixel.the.key.utilities.Constant;

public class DialogExperience extends DialogFragment implements DialogExperienceAdapter.OnClickHandler {
    private List<String> listDAta;
    private RecyclerView mRV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_experience_years, container, true);
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

    }

    private void loadRecyclerData() {
        listDAta = new ArrayList<>();
        listDAta.add("1");
        listDAta.add("2");
        listDAta.add("3");
        listDAta.add("4");
        listDAta.add("5");
        listDAta.add("6");
        listDAta.add("7");
        listDAta.add("8");


        DialogExperienceAdapter adapter = new DialogExperienceAdapter(getContext(), listDAta, this);
        mRV.setAdapter(adapter);
        Constant.runLayoutAnimation(mRV);
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
        ApplicationJobActivity.txtExperience.setText(listDAta.get(position));
        dismiss();
    }
}




