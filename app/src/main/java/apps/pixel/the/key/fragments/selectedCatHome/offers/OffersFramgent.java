package apps.pixel.the.key.fragments.selectedCatHome.offers;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import apps.pixel.the.key.R;
import apps.pixel.the.key.adapters.restaurant.OfferResAdapter;
import apps.pixel.the.key.models.offers.OffersModel;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;

public class OffersFramgent extends Fragment implements OffersInterface {

    private RecyclerView mRV;
    private List<String> listResNames;
    private List<String> listMealNames;
    private List<String> listImgLocation;
    private List<String> listPresentange;
    private List<String> listImages;

    private CairoRegularTextView mTxtPrecentage, mTxtAfterScratch;
    private String selectedId;

    private OffersPresenter offersPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers_framgent, container, false);

        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        //selectedId = sharedPreferences.getString(Constant.ITEM_SELECTED_ID, "");
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

        offersPresenter = new OffersPresenter(getActivity(), this);
        offersPresenter.getSelectedRestaurantOffers();

    }

    /*
        @Override
        public void onClick(int position) {

        }
    */
    @Override
    public void getAllOffers(List<OffersModel> jobModels) {

        for (int i = 0; i < jobModels.size(); i++) {
            if (Constant.getLng(Objects.requireNonNull(getActivity())).equals("ar")) {
                listResNames.add(jobModels.get(i).getRestaurantNameAR());
                listMealNames.add(jobModels.get(i).getItemNameAR());
                listPresentange.add(jobModels.get(i).getDiscount());
                listImages.add(jobModels.get(i).getItemImage());
                listImgLocation.add(jobModels.get(i).getRestaurantAddress());
            }
            else
            {
                listResNames.add(jobModels.get(i).getRestaurantNameEN());
                listMealNames.add(jobModels.get(i).getItemNameEN());
                listPresentange.add(jobModels.get(i).getDiscount());
                listImages.add(jobModels.get(i).getItemImage());
                listImgLocation.add(jobModels.get(i).getRestaurantAddress());
            }
        }

        OfferResAdapter adapter = new OfferResAdapter(getContext(), listResNames, listMealNames, listImgLocation, listPresentange, listImages);
        mRV.setAdapter(adapter);
        Constant.runLayoutAnimation(mRV);
    }
}
