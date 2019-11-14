package apps.pixel.al.egykey.dialog.restuarant;

import android.content.Context;
import android.content.SharedPreferences;
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

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.adapters.restaurant.AllBranchesAdapter;
import apps.pixel.al.egykey.fragments.selectedCatHome.home.HomeInterface;
import apps.pixel.al.egykey.fragments.selectedCatHome.home.HomePresenter;
import apps.pixel.al.egykey.models.selectedRestaurant.SelectedRestaurant;
import apps.pixel.al.egykey.utilities.Constant;

public class DialogAllBranchesRes extends DialogFragment implements AllBranchesAdapter.OnClickHandler, HomeInterface {
    private List<String> listDAta;
    private RecyclerView mRV;
    private HomePresenter presenter;
    private SharedPreferences sharedPreferences;


    private String selectedId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dilaog_all_branches, container, true);
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

        sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        selectedId = sharedPreferences.getString(Constant.ITEM_SELECTED_ID, "");

        presenter = new HomePresenter(getContext(), this);
        presenter.getSelectedRestaurant(selectedId);


        initViews(view);


        return view;
    }

    private void initViews(View view) {
        mRV = view.findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRV.setLayoutManager(layoutManager);

    }

    private void loadRecyclerData() {
        AllBranchesAdapter adapter = new AllBranchesAdapter(getContext(), listDAta, this);
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

    }

    @Override
    public void getSelectedItemInCatData(SelectedRestaurant selectedRestaurant) {
        listDAta = new ArrayList<>();
        for (int i = 0; i < selectedRestaurant.getOurbranches().size(); i++) {
            listDAta = new ArrayList<>();
            listDAta.add(selectedRestaurant.getOurbranches().get(i).getAddress());
        }
        loadRecyclerData();
    }
}



