package apps.pixel.al.egykey.fragments.menu;

import android.content.Intent;
import android.os.Bundle;
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

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.relatedToBasket.subDetails.ViewSelectedItemActivity;
import apps.pixel.al.egykey.adapters.restaurant.ItemsAdapter;
import apps.pixel.al.egykey.adapters.restaurant.TypesAdapter;
import apps.pixel.al.egykey.models.menu.MainMenu;
import apps.pixel.al.egykey.models.menu.SubMenu;
import apps.pixel.al.egykey.utilities.Constant;

public class MenuFragment extends Fragment implements TypesAdapter.OnClickHandler, ItemsAdapter.OnClickHandler, SwipeRefreshLayout.OnRefreshListener, MenuInterface {

    public static SwipeRefreshLayout mRefreshLayoutMenu;
    private RecyclerView mRVTypes, mRVItems;
    private List<String> listTypes, listMealNames, listDescForItem;
    private TypesAdapter typesAdapter;
    private ItemsAdapter itemsAdapter;

    private MenuPresenter presenter;
    private String selectedRestaurantId;
    private String selectedCat;

    private List<String> mainItemsId;
    private List<String> listImgs;

    private String subId;
    private String selectedNameAr, selectedNameEn;
    private String imgLogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView) {

        selectedRestaurantId = getArguments().getString(Constant.ITEM_SELECTED_ID);
        selectedCat = getArguments().getString(Constant.CAT_THAT_SELECTED);
        imgLogo = getArguments().getString(Constant.ITEM_SELECTED_REST_LOGO);
        selectedNameAr = getArguments().getString(Constant.ITEM_SELECTED_NAME_AR);
        selectedNameEn = getArguments().getString(Constant.ITEM_SELECTED_NAME_EN);


        mRefreshLayoutMenu = rootView.findViewById(R.id.swipeContainer);

        if (selectedCat.equals(Constant.CAT_RESTAURANT_VALUE)) {
            presenter = new MenuPresenter(getContext(), this);
            presenter.getMenuData(selectedRestaurantId);
        }

        Constant.setSwipeLayourColor(getContext(), mRefreshLayoutMenu);
        mRefreshLayoutMenu.setOnRefreshListener(this);

        //types
        mRVTypes = rootView.findViewById(R.id.rv_types);
        mRVTypes.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //items
        mRVItems = rootView.findViewById(R.id.rv_items);
        mRVItems.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void loadItemsData() {
        listMealNames = new ArrayList<>();
        listDescForItem = new ArrayList<>();
        listImgs = new ArrayList<>();
    }

    private void loadTypesData() {
        listTypes = new ArrayList<>();
        mainItemsId = new ArrayList<>();
    }

    @Override
    public void onRefresh() {
        if (selectedCat.equals(Constant.CAT_RESTAURANT_VALUE)) {
            presenter.getMenuData(selectedRestaurantId);
        }
    }

    @Override
    public void getMenuData(List<MainMenu> mainMenus) {
        loadTypesData();
        for (int i = 0; i < mainMenus.size(); i++) {
            if (Constant.getLng(getContext()).equals("ar")) {
                listTypes.add(mainMenus.get(i).getNameAR());
            } else {
                listTypes.add(mainMenus.get(i).getName());
            }

            mainItemsId.add(mainMenus.get(i).getID());
        }
        typesAdapter = new TypesAdapter(getContext(), listTypes, this);
        mRVTypes.setAdapter(typesAdapter);
        Constant.runLayoutAnimation(mRVTypes);

    }

    @Override
    public void onTypeClick(int position) {
        subId = mainItemsId.get(position);

        if (selectedCat.equals(Constant.CAT_RESTAURANT_VALUE)) {
            presenter.getSubMenuData(selectedRestaurantId, subId);
        }

    }

    @Override
    public void getSubMenuData(List<SubMenu> subMenus) {
        loadItemsData();
        for (int i = 0; i < subMenus.size(); i++) {
            listMealNames.add(subMenus.get(i).getName());
            listDescForItem.add(subMenus.get(i).getDescription());
            listImgs.add(Constant.BASE_URL_HTTP + subMenus.get(i).getImage().trim());
        }
        itemsAdapter = new ItemsAdapter(getContext(), listMealNames, listDescForItem, listImgs, this);
        mRVItems.setAdapter(itemsAdapter);
        Constant.runLayoutAnimation(mRVItems);
    }

    @Override
    public void onItemClick(int position) {
        subId = mainItemsId.get(position);

        Intent openSubDetails = new Intent(getContext(), ViewSelectedItemActivity.class);
        openSubDetails.putExtra(Constant.SELECTED_SUB_DETAILS_KEY, subId);
        openSubDetails.putExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU, selectedRestaurantId);
        openSubDetails.putExtra(Constant.ITEM_SELECTED_REST_LOGO, imgLogo);
        openSubDetails.putExtra(Constant.SELECTED_RESTAURANT_AR_NAME_FOR_MENU, selectedNameAr);
        openSubDetails.putExtra(Constant.SELECTED_RESTAURANT_EN_NAME_FOR_MENU, selectedNameEn);

        startActivity(openSubDetails);
        Animatoo.animateSwipeLeft(getContext());
    }
}
