package apps.pixel.al.egykey.activites.relatedToBasket;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.relatedToBasket.subDetails.SubDetailsInterface;
import apps.pixel.al.egykey.activites.relatedToBasket.subDetails.SubDetailsPresenter;
import apps.pixel.al.egykey.adapters.realtedToBakset.BasketAdapter;
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

public class OrderDetailsActivity extends AppCompatActivity implements BasketAdapter.OnClickHandler, SubDetailsInterface, SizeAdapter.SelectedInterface, SizeAdapter.OnClickHandler, ExtraAdapter.OnItemCheckListener, ExtraAdapter.OnClickHandler {

    private RecyclerView mRVBasket;
    private BasketAdapter basketAdapter;

    private SubMenuDetails subMenuDetails;

    private List<OrderDetailsModel> orderDetailsModels;

    private String arabicNameStr;
    private String englishNameStr;
    private String imgLogoStr;
    private AppCompatImageView imgIncrease;
    private CairoRegularTextView numerOfItems;
    private AppCompatImageView imgDecrease;

    private List<String> listSizes, listPrices;
    private RecyclerView rvSizes, rvExtras;
    private CairoBoldEditText etDesc;
    private String itemPrice;
    private OrderDetailsModel orderDetailsModel;
    private double totalPrice = 0;
    private double totalExtras = 0;
    private double totalMainMeal = 0;
    private int numberOfItems = 1;
    private CairoBoldTextView txtMealName;
    private String description;
    private String hasExtra = Constant.FALSE;


    private List<String> listPriceExtra, listExtraName;
    private List<ExtraOrderDetails> currentSelectedItems = new ArrayList<>();


    private List<String> listMealNames;
    private List<String> listNumbers;
    private List<String> listExtras;
    private List<String> listExtraPrices;
    private CairoRegularTextView mTxtMealPrice;

    private CairoBoldTextView mTxtRemove;

    private CairoBoldButton btnCheckout;

    private AppCompatImageView back;

    private OrderDetailsViewModel orderViewModel;

    private CairoBoldTextView mTxtTotalPrice;

    private AppCompatImageView imgLogo;
    private CairoBoldTextView txtRestName;

    private int removeClicked = 0;

    private CairoBoldTextView txtTitle;
    private CairoBoldTextView txtRemove;
    private LinearLayoutCompat linearHeader;
    private LinearLayoutCompat linearTotal;
    private CairoBoldTextView txtNoData;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        initViews();


    }

    private void initViews() {

        txtTitle = findViewById(R.id.txt_title);
        txtRemove = findViewById(R.id.txt_remove);
        linearHeader = findViewById(R.id.linear_header);
        linearTotal = findViewById(R.id.linear_total);
        btnCheckout = findViewById(R.id.btn_checkout);
        txtNoData = findViewById(R.id.txt_no_data);


        orderViewModel = ViewModelProviders.of(this).get(OrderDetailsViewModel.class);

        imgLogo = findViewById(R.id.img_logo);
        txtRestName = findViewById(R.id.txt_rest_name);

        mTxtTotalPrice = findViewById(R.id.txt_salary);
        btnCheckout = findViewById(R.id.btn_checkout);
        mTxtRemove = findViewById(R.id.txt_remove);
        mRVBasket = findViewById(R.id.rv_my_basket);
        mRVBasket.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // if (removeClicked == 0) {

        if (removeClicked == 0)
            try {
                orderViewModel.getAllOrders().observe(this, orderDetailsModels -> {
                    this.orderDetailsModels = orderDetailsModels;
                    try {
                        if (Constant.getLng(this).equals("ar"))
                            txtRestName.setText(orderDetailsModels.get(orderDetailsModels.size() - 1).getArabicName());
                        else
                            txtRestName.setText(orderDetailsModels.get(orderDetailsModels.size() - 1).getEnglishName());

                        arabicNameStr = orderDetailsModels.get(orderDetailsModels.size() - 1).getArabicName();
                        englishNameStr = orderDetailsModels.get(orderDetailsModels.size() - 1).getEnglishName();

                        double price = 0;
                        for (OrderDetailsModel orderDetailsModel : orderDetailsModels) {
                            price = price + Double.valueOf(orderDetailsModel.getTotalPrice().replace(" EGP", ""));
                        }
                        mTxtTotalPrice.setText(String.valueOf(price) + " " + getString(R.string.egp));

                        Picasso.get()
                                .load(orderDetailsModels.get(orderDetailsModels.size() - 1).getImgLogo())
                                .fit()
                                .centerCrop()
                                .into(imgLogo);

                        imgLogoStr = orderDetailsModels.get(orderDetailsModels.size() - 1).getImgLogo();

                        loadBasketData();
                        for (int i = 0; i < orderDetailsModels.size(); i++) {
                            orderDetailsModels.get(i).getId();
                            Log.d("DATA_THAT_SAVED", "initViews: " + "SIZE_ " + orderDetailsModels.size() + "  " + orderDetailsModels.get(i).toString());

//                            double totalPrice =+ Double.valueOf(orderDetailsModels.get(i).getTotalPrice().replace(" EGP", ""));
//
//                            mTxtTotalPrice.setText(String.valueOf(totalPrice) + " " + getString(R.string.egp));

                            listMealNames.add(orderDetailsModels.get(i).getMainMealName());
                            listNumbers.add("1");

                            loadBasketExtraData();

                            for (ExtraOrderDetails extraOrderDetails : orderDetailsModels.get(i).getExtraOrderDetails()) {
                                Log.d("SUB_LIST_SIZE", "initViews: " + orderDetailsModels.get(i).getExtraOrderDetails().size());
                                if (orderDetailsModels.get(i).getHasExtra().equals(Constant.TRUE)) {
                                    listExtras.add(extraOrderDetails.getExtraName());
                                    listExtraPrices.add(extraOrderDetails.getExtraPrice());
                                } else {

                                }
                            }
                        }
                        basketAdapter = new BasketAdapter(this, orderDetailsModels, this);
                        mRVBasket.setAdapter(basketAdapter);
                        Constant.runLayoutAnimation(mRVBasket);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                        //  Toast.makeText(this, "DATA DELETED", Toast.LENGTH_SHORT).show();
                        txtRemove.setVisibility(View.INVISIBLE);
                        linearHeader.setVisibility(View.GONE);
                        linearTotal.setVisibility(View.GONE);
                        btnCheckout.setVisibility(View.GONE);
                        mRVBasket.setVisibility(View.GONE);
                        txtNoData.setVisibility(View.VISIBLE);

                    }
                });
            } catch (Exception ignored) {
                // Toast.makeText(this, "DATA DELETED", Toast.LENGTH_SHORT).show();
            }
//
//            removeClicked = 0;
//        }


        mTxtRemove.setOnClickListener(v -> {
            // if (removeClicked == 0)
            try {
                PrettyDialog prettyDialog = new PrettyDialog(this);

                prettyDialog.setCancelable(true);
                prettyDialog
                        .setIcon(R.drawable.ic_information)
                        .setTitle(getString(R.string.are_your_sure))
                        .addButton(getString(R.string.ok), android.R.color.white, R.color.color_blue, () -> {
                            removeClicked = 1;
                            orderViewModel.deleteAllOrders();
                            basketAdapter.notifyDataSetChanged();
                            prettyDialog.dismiss();
                        }).addButton(getString(R.string.cancel), android.R.color.white, R.color.color_blue, () -> prettyDialog.dismiss())
                        .show();

            } catch (Exception ignored) {
                Toast.makeText(this, "DATA DELETED", Toast.LENGTH_SHORT).show();
            }

            // removeClicked = 1;
        });

        back = findViewById(R.id.close);
        back.setOnClickListener(v -> {
            onBackPressed();
        });

        btnCheckout.setOnClickListener(v -> {
            startActivity(new Intent(OrderDetailsActivity.this, AddressActivity.class));
            Animatoo.animateSwipeLeft(OrderDetailsActivity.this);
        });
    }

    private void loadBasketData() {
        listMealNames = new ArrayList<>();
        listNumbers = new ArrayList<>();
    }

    private void loadBasketExtraData() {
        listExtras = new ArrayList<>();
        listExtraPrices = new ArrayList<>();
    }

    @Override
    public void onClick(int position, int id) {

        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemDeleted(int position, int id) {
        PrettyDialog prettyDialog = new PrettyDialog(this);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_information)
                .setTitle(this.getString(R.string.are_your_sure_item))
                .addButton(this.getString(R.string.ok), android.R.color.white, R.color.color_blue, () -> {
                    orderViewModel.deleteSpecificMenu(id);
                    basketAdapter.removeAt(position);
                    prettyDialog.dismiss();
                }).addButton(this.getString(R.string.cancel), android.R.color.white, R.color.color_blue, () -> prettyDialog.dismiss())
                .show();
    }

    @Override
    public void onItemEdited(int position, int id, String selectedId) {

        openBottomDialog(position, selectedId, id);

    }

    private void openBottomDialog(int position, String selectedId, int id) {
        Log.d("DATA_THAT_SAVED_TO_EDIT", "openBottomDialog: " + orderDetailsModels.get(position).toString());
        Rect displayRectangle = new Rect();
        Window window = Objects.requireNonNull(getWindow());
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        //  builder                        = new AlertDialog.Builder(getContext());
        @SuppressLint("InflateParams")
        View mview = getLayoutInflater().inflate(R.layout.dilaog_edit_order, null);
        Dialog dialog = new BottomSheetDialog(OrderDetailsActivity.this);
        dialog.setContentView(mview);
        SubDetailsPresenter presenter = new SubDetailsPresenter(this, this);
        presenter.getSubMenuDetailsData(selectedId);
        dialog.show();
        //______________-

        this.orderDetailsModel = new OrderDetailsModel();
        CairoRegularTextView mTxtDesc = dialog.findViewById(R.id.txt_last_order_desc);
        // if (Constant.getLng(this).equals("ar")) {
        try {
            mTxtDesc.setText(getString(R.string.you_order_was) + "\n" + orderDetailsModels.get(position).getMainMealName()
                    + " - " + orderDetailsModels.get(position).getMainMealSize() + " ( " + orderDetailsModels.get(position).getMainMealPrice() + " ) " + " - "
                    + getString(R.string.extras) + " : ");
            for (int i = 0; i < orderDetailsModels.get(position).getExtraOrderDetails().size(); i++) {
                mTxtDesc.append(orderDetailsModels.get(position).getExtraOrderDetails().get(i).getExtraName() + " ( "
                        + orderDetailsModels.get(position).getExtraOrderDetails().get(i).getExtraPrice() + " ) ");
            }
            mTxtDesc.append(" " + orderDetailsModels.get(position).getTotalPrice() + " " + getString(R.string.egp));
        } catch (Exception ignored) {
            mTxtDesc.setText(getString(R.string.you_order_was) + "\n" + orderDetailsModels.get(position).getMainMealName()
                    + " - " + orderDetailsModels.get(position).getMainMealSize() + " ( " + orderDetailsModels.get(position).getMainMealPrice() + " ) " +
                    "  " + getString(R.string.no_extras) + " " + orderDetailsModels.get(position).getTotalPrice() + " " + getString(R.string.egp));
        }

//        } else {
//            try {
//                mTxtDesc.setText(orderDetailsModels.get(position).getMainMealName() + " " + getString(R.string.extras) + " : ");
//                for (int i = 0; i < orderDetailsModels.get(position).getExtraOrderDetails().size(); i++) {
//                    mTxtDesc.append(orderDetailsModels.get(position).getExtraOrderDetails().get(i).getExtraName() + " ");
//                }
//
//            } catch (Exception ignored) {
//                mTxtDesc.setText(orderDetailsModels.get(position).getMainMealName() + " " + getString(R.string.no_extras));
//            }
//        }


        mTxtMealPrice = dialog.findViewById(R.id.meal_price);
        numerOfItems = dialog.findViewById(R.id.numer_of_items);
        numerOfItems.setText(String.valueOf(numberOfItems));
        imgIncrease = dialog.findViewById(R.id.img_increase);
        imgIncrease.setOnClickListener(v -> {
            ++numberOfItems;
            numerOfItems.setText(String.valueOf(numberOfItems));

            mTxtMealPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));

            if (numberOfItems > 1) {
                imgDecrease.setImageResource(R.drawable.ic_minus);
            }
            if (numberOfItems == 1) {
                imgDecrease.setImageResource(R.drawable.ic_minus_diabled);
            }
        });

        imgDecrease = dialog.findViewById(R.id.img_decrease);
        imgDecrease.setOnClickListener(v -> {
            if (numberOfItems > 1) {
                --numberOfItems;
                imgDecrease.setImageResource(R.drawable.ic_minus);
                numerOfItems.setText(String.valueOf(numberOfItems));

                mTxtMealPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));

            }
            if (numberOfItems == 1) {
                imgDecrease.setImageResource(R.drawable.ic_minus_diabled);
            }
        });

        //________----
        txtMealName = dialog.findViewById(R.id.txt_meal_name);
        rvSizes = dialog.findViewById(R.id.rv_sizes);
        rvExtras = dialog.findViewById(R.id.rv_extras);
        rvExtras.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvSizes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        etDesc = dialog.findViewById(R.id.et_desc);
        CairoBoldButton btnCancel = dialog.findViewById(R.id.btn_cancel);
        CairoBoldButton addToBasket = dialog.findViewById(R.id.add_to_basket);

        AppCompatImageView mClose = dialog.findViewById(R.id.close);
        mClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        if (Constant.getLng(this).equals("ar")) {
            txtMealName.setText(this.orderDetailsModel.getArabicName());
        } else {
            txtMealName.setText(this.orderDetailsModel.getEnglishName());
        }

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        this.orderDetailsModel.setHasExtra(hasExtra);
        this.orderDetailsModel.setImgLogo(imgLogoStr);
        this.orderDetailsModel.setArabicName(arabicNameStr);
        this.orderDetailsModel.setEnglishName(englishNameStr);

        addToBasket.setOnClickListener(v -> {
            this.orderDetailsModel.setExtraOrderDetails(currentSelectedItems);
            this.orderDetailsModel.setCountOfMeals(String.valueOf(numberOfItems));
            this.orderDetailsModel.setRestaurantId(selectedId);
            this.orderDetailsModel.setTotalPrice(mTxtMealPrice.getText().toString());
            description = getString(R.string.there_is_no_desc);
            try {
                description = etDesc.getText().toString();
                this.orderDetailsModel.setDescription(description);
            } catch (Exception ignored) {
                description = getString(R.string.there_is_no_desc);
                this.orderDetailsModel.setDescription(description);
            }


            etDesc.setText(this.orderDetailsModel.getDescription());

            try {
                currentSelectedItems.get(0).getExtraName();
                hasExtra = Constant.TRUE;
            } catch (Exception e) {
                hasExtra = Constant.FALSE;
            }

            Log.d("DATA_TO_UPDATE", "openBottomDialog: " + id + getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU)
                    + this.orderDetailsModel.getCountOfMeals() + this.orderDetailsModel.getMainMealName()
                    + this.orderDetailsModel.getMainMealSize() + this.orderDetailsModel.getMainMealPrice()
                    + this.orderDetailsModel.getExtraOrderDetails() + this.orderDetailsModel.getDescription()
                    + this.orderDetailsModel.getHasExtra() + this.orderDetailsModel.getTotalPrice()
                    + this.orderDetailsModel.getArabicName() + this.orderDetailsModel.getEnglishName()
                    + this.orderDetailsModel.getImgLogo() + selectedId);
            // SharedPreferences sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);
            orderViewModel.updateSpecificOrder(id, getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU)
                    , this.orderDetailsModel.getCountOfMeals(), this.orderDetailsModel.getMainMealName()
                    , this.orderDetailsModel.getMainMealSize(), this.orderDetailsModel.getMainMealPrice()
                    , this.orderDetailsModel.getExtraOrderDetails(), this.orderDetailsModel.getDescription()
                    , this.orderDetailsModel.getHasExtra(), this.orderDetailsModel.getTotalPrice()
                    , this.orderDetailsModel.getArabicName(), this.orderDetailsModel.getEnglishName()
                    , this.orderDetailsModel.getImgLogo(), this.orderDetailsModel.getSelectedId());
//
//            basketAdapter.notifyItemChanged(position);
//            dialog.dismiss();
//


            finish();
            Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
            intent.putExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU , getIntent().getStringExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU));
            startActivity(intent);

//            orderViewModel.getAllOrders().observe(OrderDetailsActivity.this, orderDetailsModels -> {
//                initViews();
//                //this.orderDetailsModels = orderDetailsModels;
//                try {
//                    if (Constant.getLng(this).equals("ar"))
//                        txtRestName.setText(orderDetailsModels.get(orderDetailsModels.size() - 1).getArabicName());
//                    else
//                        txtRestName.setText(orderDetailsModels.get(orderDetailsModels.size() - 1).getEnglishName());
//
//                    arabicNameStr = orderDetailsModels.get(orderDetailsModels.size() - 1).getArabicName();
//                    englishNameStr = orderDetailsModels.get(orderDetailsModels.size() - 1).getEnglishName();
//
//                    double price = 0;
//                    for (OrderDetailsModel orderDetailsModel : orderDetailsModels) {
//                        price = price + Double.valueOf(orderDetailsModel.getTotalPrice().replace(" EGP", ""));
//                    }
//                    mTxtTotalPrice.setText(String.valueOf(price) + " " + getString(R.string.egp));
//
//                    Picasso.get()
//                            .load(orderDetailsModels.get(orderDetailsModels.size() - 1).getImgLogo())
//                            .fit()
//                            .centerCrop()
//                            .into(imgLogo);
//
//                    imgLogoStr = orderDetailsModels.get(orderDetailsModels.size() - 1).getImgLogo();
//
//                    loadBasketData();
//                    for (int i = 0; i < orderDetailsModels.size(); i++) {
//                        orderDetailsModels.get(i).getId();
//                        Log.d("DATA_THAT_SAVED", "initViews: " + "SIZE_ " + orderDetailsModels.size() + "  " + orderDetailsModels.get(i).toString());
//
////                            double totalPrice =+ Double.valueOf(orderDetailsModels.get(i).getTotalPrice().replace(" EGP", ""));
////
////                            mTxtTotalPrice.setText(String.valueOf(totalPrice) + " " + getString(R.string.egp));
//
//                        listMealNames.add(orderDetailsModels.get(i).getMainMealName());
//                        listNumbers.add("1");
//
//                        loadBasketExtraData();
//
//                        for (ExtraOrderDetails extraOrderDetails : orderDetailsModels.get(i).getExtraOrderDetails()) {
//                            Log.d("SUB_LIST_SIZE", "initViews: " + orderDetailsModels.get(i).getExtraOrderDetails().size());
//                            if (orderDetailsModels.get(i).getHasExtra().equals(Constant.TRUE)) {
//                                listExtras.add(extraOrderDetails.getExtraName());
//                                listExtraPrices.add(extraOrderDetails.getExtraPrice());
//                            } else {
//
//                            }
//                        }
//                    }
//                    basketAdapter = new BasketAdapter(this, orderDetailsModels, this);
//                    mRVBasket.setAdapter(basketAdapter);
//                    Constant.runLayoutAnimation(mRVBasket);
//                } catch (ArrayIndexOutOfBoundsException ignored) {
//                    //  Toast.makeText(this, "DATA DELETED", Toast.LENGTH_SHORT).show();
//                    txtRemove.setVisibility(View.INVISIBLE);
//                    linearHeader.setVisibility(View.GONE);
//                    linearTotal.setVisibility(View.GONE);
//                    btnCheckout.setVisibility(View.GONE);
//                    mRVBasket.setVisibility(View.GONE);
//                    txtNoData.setVisibility(View.VISIBLE);
//
//                }
//
//            });
//            finish();
//            Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
//            startActivity(intent);
        });


        OrderDetailsModel orderDetailsModelForDB = new OrderDetailsModel(this.orderDetailsModel.getRestaurantId(), this.orderDetailsModel.getCountOfMeals()
                , this.orderDetailsModel.getMainMealName(), this.orderDetailsModel.getMainMealSize(), this.orderDetailsModel.getMainMealPrice(),
                this.orderDetailsModel.getExtraOrderDetails(), this.orderDetailsModel.getDescription(), this.orderDetailsModel.getHasExtra(),
                this.orderDetailsModel.getTotalPrice(), this.orderDetailsModel.getArabicName(), this.orderDetailsModel.getEnglishName(),
                this.orderDetailsModel.getImgLogo(), this.orderDetailsModel.getSelectedId());

        Log.d("DATA_THAT_ENTERED", "openBottomDialog: " + orderDetailsModelForDB.toString());


        btnCancel.setOnClickListener(view -> dialog.cancel());

    }

    private void loadRecyclerSizesData() {
        listPrices = new ArrayList<>();
        listSizes = new ArrayList<>();
    }

    private void loadRecyclerExtrasData() {
        listExtraName = new ArrayList<>();
        listPriceExtra = new ArrayList<>();
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
        SizeAdapter sizeAdapter = new SizeAdapter(this, listSizes, listPrices, this, this);
        rvSizes.setAdapter(sizeAdapter);
        Constant.runLayoutAnimation(rvSizes);


        loadRecyclerExtrasData();
        List<ExtraOrderDetails> currentSelectedItems = new ArrayList<>();

        for (int i = 0; i < mainMenus.getExtras().size(); i++) {
            if (Constant.getLng(this).equals("ar")) {
                currentSelectedItems.add(new ExtraOrderDetails(mainMenus.getExtras().get(i).getNameAR(), mainMenus.getExtras().get(i).getPrice()));
            } else {
                currentSelectedItems.add(new ExtraOrderDetails(mainMenus.getExtras().get(i).getName(), mainMenus.getExtras().get(i).getPrice()));
            }
        }

        ExtraAdapter extraAdapter = new ExtraAdapter(this, currentSelectedItems, this, this);
        rvExtras.setAdapter(extraAdapter);
        Constant.runLayoutAnimation(rvExtras);

        txtMealName.setText(mainMenus.getPrice().getName());
        this.orderDetailsModel.setMainMealName(mainMenus.getPrice().getName());
    }

    @Override
    public void getSelectedRadioBtn(int position, String size) {
        itemPrice = subMenuDetails.getPrice().getListSizeInMenu().get(position).getPrice();
        //Toast.makeText(this, itemPrice, Toast.LENGTH_SHORT).show();

        this.orderDetailsModel.setMainMealSize(size);
        this.orderDetailsModel.setMainMealPrice(itemPrice);

        totalMainMeal = Double.valueOf(itemPrice);
        totalPrice = totalMainMeal + totalExtras;
        mTxtMealPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));

    }

    @Override
    public void onSizeSelected(int position) {

    }

    @Override
    public void onItemCheck(ExtraOrderDetails item, String price) {
        this.currentSelectedItems.add(item);

        totalExtras += Double.valueOf(price);
        totalPrice += Double.valueOf(price);

        mTxtMealPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));
    }

    @Override
    public void onItemUncheck(ExtraOrderDetails item, String price) {
        this.currentSelectedItems.remove(item);

        totalExtras -= Double.valueOf(price);

        totalPrice -= Double.valueOf(price);

        mTxtMealPrice.setText(String.valueOf(totalPrice * numberOfItems) + " " + getString(R.string.egp));
    }

    @Override
    public void onTypeClick(int position) {

    }
}
