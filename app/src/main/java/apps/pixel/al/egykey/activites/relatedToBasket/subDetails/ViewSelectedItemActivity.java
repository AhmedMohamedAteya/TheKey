package apps.pixel.al.egykey.activites.relatedToBasket.subDetails;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.adapters.realtedToBakset.ExtraAdapter;
import apps.pixel.al.egykey.adapters.realtedToBakset.SizeAdapter;
import apps.pixel.al.egykey.models.menu.SubMenuDetails;
import apps.pixel.al.egykey.models.orderDetails.ExtraOrderDetails;
import apps.pixel.al.egykey.models.orderDetails.OrderDetailsModel;
import apps.pixel.al.egykey.utilities.CairoBoldButton;
import apps.pixel.al.egykey.utilities.CairoBoldEditText;
import apps.pixel.al.egykey.utilities.CairoBoldTextView;
import apps.pixel.al.egykey.utilities.CairoRegularTextView;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.viewModel.OrderDetailsViewModel;
import libs.mjn.prettydialog.PrettyDialog;

public class ViewSelectedItemActivity extends AppCompatActivity implements SizeAdapter.SelectedInterface, SizeAdapter.OnClickHandler, ExtraAdapter.OnClickHandler, ExtraAdapter.CheckItems, SubDetailsInterface, ExtraAdapter.UnCheckItems, ExtraAdapter.OnItemCheckListener {

    public static SwipeRefreshLayout swipeRefreshLayoutSubDetails;
    String flagForTrue = Constant.TRUE;
    String flagForFalse = Constant.TRUE;
    private CairoBoldEditText mETDesc;
    private OrderDetailsModel orderDetailsModel;
    private SubDetailsPresenter presenter;
    //first rv
    private RecyclerView mRVSize;
    private SizeAdapter sizeAdapter;
    private List<String> listSizes, listPrices;
    //second rv
    private RecyclerView mRVExtras;
    private ExtraAdapter extraAdapter;
    private List<String> listPriceExtra, listExtraName;
    private AppCompatImageView close;
    private CairoBoldButton addToBasket;
    private CairoBoldTextView mTxtMealName;
    private String selectedId;
    private CairoRegularTextView mTxtTotalPrice;
    private AppCompatImageView imgIncrease;
    private CairoRegularTextView numerOfItems;
    private AppCompatImageView imgDecrease;
    private int numberOfItems = 1;
    private SubMenuDetails subMenuDetails;
    private String itemPrice;
    private List<String> listExtras;
    private double totalPrice = 0;
    private double totalExtras = 0;
    private double totalMainMeal = 0;
    private String description;
    private String hasExtra = Constant.FALSE;
    private String flag;
    private List<ExtraOrderDetails> currentSelectedItems = new ArrayList<>();

    private OrderDetailsViewModel orderViewModel;

    private String englishName;
    private String arabicName;
    private String logoUrl;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_selected_items);

        if (getIntent().hasExtra(Constant.SELECTED_SUB_DETAILS_KEY)) {
            selectedId = getIntent().getStringExtra(Constant.SELECTED_SUB_DETAILS_KEY);
        }
        if (getIntent().hasExtra(Constant.ITEM_SELECTED_REST_LOGO)) {
            logoUrl = getIntent().getStringExtra(Constant.ITEM_SELECTED_REST_LOGO);
        }

        if (getIntent().hasExtra(Constant.SELECTED_RESTAURANT_AR_NAME_FOR_MENU)) {
            arabicName = getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_AR_NAME_FOR_MENU);
        }

        if (getIntent().hasExtra(Constant.SELECTED_RESTAURANT_EN_NAME_FOR_MENU)) {
            englishName = getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_EN_NAME_FOR_MENU);
        }

        presenter = new SubDetailsPresenter(this, this);
        orderDetailsModel = new OrderDetailsModel();

        orderDetailsModel.setRestaurantId(getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU));

        orderViewModel = ViewModelProviders.of(this).get(OrderDetailsViewModel.class);
        orderViewModel.getAllOrders().observe(this, orderDetailsModels -> {
            for (OrderDetailsModel orderDetailsModel : orderDetailsModels) {
                if (orderDetailsModel.getRestaurantId().equals(this.orderDetailsModel.getRestaurantId())) {
                    // all data is from same restaurant
                    flagForFalse = Constant.TRUE;
                } else {
                    // all data is "NOT" from same restaurant
                    flagForFalse = Constant.FALSE;
                }
            }
        });

        swipeRefreshLayoutSubDetails = findViewById(R.id.swipeContainer);
        Constant.setSwipeLayourColor(this, swipeRefreshLayoutSubDetails);


        initViewsFirstRV();
        initViewsSecondRV();

        initViews();

        listExtras = new ArrayList<>();

        presenter.getSubMenuDetailsData(selectedId);
    }

    private void initViews() {
        close = findViewById(R.id.close);
        close.setOnClickListener(v -> {
            onBackPressed();
        });

        mETDesc = findViewById(R.id.et_desc);

        numerOfItems = findViewById(R.id.numer_of_items);
        numerOfItems.setText(String.valueOf(numberOfItems));

        imgIncrease = findViewById(R.id.img_increase);
        imgIncrease.setOnClickListener(v -> {
            ++numberOfItems;
            numerOfItems.setText(String.valueOf(numberOfItems));

            mTxtTotalPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));

            if (numberOfItems > 1) {
                imgDecrease.setImageResource(R.drawable.ic_minus);
            }
            if (numberOfItems == 1) {
                imgDecrease.setImageResource(R.drawable.ic_minus_diabled);
            }
        });

        imgDecrease = findViewById(R.id.img_decrease);
        imgDecrease.setOnClickListener(v -> {
            if (numberOfItems > 1) {
                --numberOfItems;
                imgDecrease.setImageResource(R.drawable.ic_minus);
                numerOfItems.setText(String.valueOf(numberOfItems));

                mTxtTotalPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));

            }
            if (numberOfItems == 1) {
                imgDecrease.setImageResource(R.drawable.ic_minus_diabled);
            }
        });


        addToBasket = findViewById(R.id.add_to_basket);

        addToBasket.setOnClickListener(v -> {
            orderDetailsModel.setExtraOrderDetails(currentSelectedItems);
            orderDetailsModel.setCountOfMeals(String.valueOf(numberOfItems));
            orderDetailsModel.setTotalPrice(mTxtTotalPrice.getText().toString());
            description = getString(R.string.there_is_no_desc);
            try {
                description = mETDesc.getText().toString();
                orderDetailsModel.setDescription(description);
            } catch (Exception ignored) {
                description = getString(R.string.there_is_no_desc);
                orderDetailsModel.setDescription(description);
            }


            try {
                currentSelectedItems.get(0).getExtraName();
                hasExtra = Constant.TRUE;
            } catch (Exception e) {
                hasExtra = Constant.FALSE;
            }
            orderDetailsModel.setHasExtra(hasExtra);
            orderDetailsModel.setImgLogo(logoUrl);
            orderDetailsModel.setArabicName(arabicName);
            orderDetailsModel.setEnglishName(englishName);
            //String restaurantId, String countOfMeals, String mainMealName, String mainMealSize, String mainMealPrice,
            // List<ExtraOrderDetails> extraOrderDetails, String description, String hasExtra, String totalPrice
            OrderDetailsModel orderDetailsModelForDB = new OrderDetailsModel(orderDetailsModel.getRestaurantId(), orderDetailsModel.getCountOfMeals()
                    , orderDetailsModel.getMainMealName(), orderDetailsModel.getMainMealSize(), orderDetailsModel.getMainMealPrice(),
                    orderDetailsModel.getExtraOrderDetails(), orderDetailsModel.getDescription(), orderDetailsModel.getHasExtra(),
                    orderDetailsModel.getTotalPrice(), orderDetailsModel.getArabicName(), orderDetailsModel.getEnglishName(), orderDetailsModel.getImgLogo()
                    , selectedId);


            if (flagForTrue.equals(flagForFalse)) {
                Toast.makeText(this, "ALL IS THE SAME", Toast.LENGTH_SHORT).show();
                Log.d("THE_STATUS_OF_DATA", "initViews: " + "ALL IS THE SAME");
                try {
                    if (orderDetailsModelForDB.getMainMealPrice().equals("null")) {
                        Constant.showErrorDialog(ViewSelectedItemActivity.this, getString(R.string.please_choose_meal_size_first));
                        return;
                    } else {
                        orderViewModel.insert(orderDetailsModelForDB);
                    }
                } catch (Exception ignored) {
                    Constant.showErrorDialog(ViewSelectedItemActivity.this, getString(R.string.please_choose_meal_size_first));
                    return;
                }

                flag = Constant.TRUE;


            } else {
                Log.d("THE_STATUS_OF_DATA", "initViews: " + "ALL IS NOT THE SAME");
                Toast.makeText(this, "ALL IS NOT THE SAME", Toast.LENGTH_SHORT).show();
                flag = Constant.FALSE;
            }

            if (flag.equals(Constant.TRUE)) {
//                Intent intent = new Intent(ViewSelectedItemActivity.this, OrderDetailsActivity.class);
//                intent.putExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU, getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU));
//                startActivity(intent);
//                Animatoo.animateSwipeLeft(ViewSelectedItemActivity.this);
                onBackPressed();
            } else {
                PrettyDialog prettyDialog = new PrettyDialog(this);

                prettyDialog.setCancelable(true);
                prettyDialog
                        .setIcon(R.drawable.ic_information)
                        .setTitle(getString(R.string.you_added_order_beofre))
                        .addButton(this.getString(R.string.cancel), android.R.color.white, R.color.color_blue, () -> prettyDialog.dismiss())
                        .addButton(this.getString(R.string.ok), android.R.color.white, R.color.color_blue, () -> {
                            orderViewModel.deleteAllOrders();
                            orderViewModel.insert(orderDetailsModelForDB);
//                            Intent intent = new Intent(ViewSelectedItemActivity.this, OrderDetailsActivity.class);
//                            intent.putExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU , getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU));
//                            startActivity(intent);
                            onBackPressed();
                            // Animatoo.animateSwipeLeft(ViewSelectedItemActivity.this);
                            prettyDialog.dismiss();
                        })
                        .show();

                Toast.makeText(this, "DATA IS DIFERENT", Toast.LENGTH_SHORT).show();
            }
            Log.d("DATA_THAT_ENTERED", "initViews: " + orderDetailsModel.toString());
        });

        mTxtMealName = findViewById(R.id.txt_meal_name);
        mTxtTotalPrice = findViewById(R.id.meal_price);

        swipeRefreshLayoutSubDetails.setOnRefreshListener(() -> {
            totalPrice = 0;
            totalExtras = 0;
            totalMainMeal = 0;
            numberOfItems = 1;
            numerOfItems.setText(String.valueOf(numberOfItems));
            imgDecrease.setImageResource(R.drawable.ic_minus_diabled);
            mTxtTotalPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));
            presenter.getSubMenuDetailsData(selectedId);
        });
    }

    private void initViewsSecondRV() {
        mRVExtras = findViewById(R.id.rv_extras);
        mRVExtras.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    private void loadRecyclerExtrasData() {
        listExtraName = new ArrayList<>();
        listPriceExtra = new ArrayList<>();
    }

    private void initViewsFirstRV() {
        mRVSize = findViewById(R.id.rv_sizes);
        mRVSize.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void loadRecyclerSizesData() {
        listPrices = new ArrayList<>();
        listSizes = new ArrayList<>();
    }

    @Override
    public void getSelectedRadioBtn(int position, String size) {
        //for size
        itemPrice = subMenuDetails.getPrice().getListSizeInMenu().get(position).getPrice();
        //Toast.makeText(this, itemPrice, Toast.LENGTH_SHORT).show();

        orderDetailsModel.setMainMealSize(size);
        orderDetailsModel.setMainMealPrice(itemPrice);

        totalMainMeal = Double.valueOf(itemPrice);
        totalPrice = totalMainMeal + totalExtras;
        mTxtTotalPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));
    }


    @Override
    public void onTypeClick(int position) {
        //for size
        // Toast.makeText(this, "onTypeClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSizeSelected(int position) {
        //for extras
    }

    @Override
    public void getSubDetails(SubMenuDetails mainMenus) {
        this.subMenuDetails = mainMenus;
        loadRecyclerSizesData();
        for (int i = 0; i < mainMenus.getPrice().getListSizeInMenu().size(); i++) {
            if (Constant.getLng(this).equals("ar")) {
                listSizes.add(mainMenus.getPrice().getListSizeInMenu().get(i).getSizeAR());
            } else {
                listSizes.add(mainMenus.getPrice().getListSizeInMenu().get(i).getSize());
            }
            listPrices.add(mainMenus.getPrice().getListSizeInMenu().get(i).getPrice());
        }
        sizeAdapter = new SizeAdapter(this, listSizes, listPrices, this, this);
        mRVSize.setAdapter(sizeAdapter);
        Constant.runLayoutAnimation(mRVSize);

        loadRecyclerExtrasData();
        List<ExtraOrderDetails> currentSelectedItems = new ArrayList<>();

        for (int i = 0; i < mainMenus.getExtras().size(); i++) {
            if (Constant.getLng(this).equals("ar")) {
                currentSelectedItems.add(new ExtraOrderDetails(mainMenus.getExtras().get(i).getNameAR(), mainMenus.getExtras().get(i).getPrice()));
            } else {
                currentSelectedItems.add(new ExtraOrderDetails(mainMenus.getExtras().get(i).getName(), mainMenus.getExtras().get(i).getPrice()));
            }
        }
        extraAdapter = new ExtraAdapter(this, currentSelectedItems, this, this);
        mRVExtras.setAdapter(extraAdapter);
        Constant.runLayoutAnimation(mRVExtras);

        mTxtMealName.setText(mainMenus.getPrice().getName());
        orderDetailsModel.setMainMealName(mainMenus.getPrice().getName());
    }


    @Override
    public void onItemCheck(ExtraOrderDetails item, String price) {
        Toast.makeText(this, "CHECKED", Toast.LENGTH_SHORT).show();
        this.currentSelectedItems.add(item);

        totalExtras += Double.valueOf(price);
        totalPrice += Double.valueOf(price);

        mTxtTotalPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));
    }

    @Override
    public void onItemUncheck(ExtraOrderDetails item, String price) {
        Toast.makeText(this, "UNCHECKED", Toast.LENGTH_SHORT).show();
        this.currentSelectedItems.remove(item);

        totalExtras -= Double.valueOf(price);

        totalPrice -= Double.valueOf(price);

        mTxtTotalPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));
    }

    @Override
    public void getCheckItems(int position, String price) {
        listExtras.add(price);

        Log.d("DATA_THAT_CHECKED", "getCheckItems: " + listExtras.get(position) + " __ " + listExtras.size());


    }

    @Override
    public void getUnCheckItems(int position, String price) {
        try {
            Log.d("DATA_THAT_CHECKED", "getUnCheckItems: " + listExtras.get(position) + " __ " + listExtras.size());

            listExtras.remove(position);

        } catch (Exception ignored) {
            listExtras = new ArrayList<>();
        }

        for (int i = 0; i < listExtras.size(); i++) {
            Toast.makeText(this, listExtras.get(listExtras.size() - 1), Toast.LENGTH_SHORT).show();
        }

    }
}
