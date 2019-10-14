package apps.pixel.al.egykey.fragments.retaurant;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.adapters.restaurant.OfferResAdapter;
import apps.pixel.al.egykey.utilities.Constant;

public class OffersFramgent extends Fragment implements OfferResAdapter.OnClickHandler {

    private RecyclerView mRV;
    private List<String> listResNames;
    private List<String> listMealNames;
    private List<String> listImgLocation;
    private List<String> listPresentange;
    private List<String> listImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers_framgent, container, false);

        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        listImages = new ArrayList<>();
        listMealNames = new ArrayList<>();
        listPresentange = new ArrayList<>();
        listResNames = new ArrayList<>();
        listImgLocation = new ArrayList<>();

        mRV = rootView.findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRV.setLayoutManager(layoutManager);
        loadRecyclerData();

    }

    private void loadRecyclerData() {
        listMealNames.add("COMPO");
        listMealNames.add("COMPO");
        listMealNames.add("COMPO");
        listMealNames.add("COMPO");
        listMealNames.add("COMPO");
        listMealNames.add("COMPO");

        OfferResAdapter adapter = new OfferResAdapter(getContext(), listResNames, listMealNames, listImgLocation, listPresentange, listImages, this);

        mRV.setAdapter(adapter);
        Constant.runLayoutAnimation(mRV);
    }

    @Override
    public void onClick(int position) {

    }
}
