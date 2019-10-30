package apps.pixel.al.egykey.dialog.restuarant.call;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.adapters.restaurant.CallAdapter;
import apps.pixel.al.egykey.utilities.CairoBoldButton;
import apps.pixel.al.egykey.utilities.Constant;

import static apps.pixel.al.egykey.fragments.retaurant.home.HomeRestFragment.listNumbersDAta;

public class DialogPhone extends DialogFragment implements CallAdapter.OnClickHandler, SelectedInterface {

    private CairoBoldButton mCall;
    private CairoBoldButton mCancel;
    private SharedPreferences sharedPreferences;

    // private HomePresenter presenter;

    private RecyclerView mRV;

    private String selectedId;

    private String selectedPhoneNumber = "mafish";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dilaog_phone, container, true);
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
        sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        selectedId = sharedPreferences.getString(Constant.ITEM_SELECTED_ID, "");

        //presenter = new HomePresenter(getContext(), this);
        //presenter.getSelectedRestaurant(selectedId);

        mRV = view.findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRV.setLayoutManager(layoutManager);

        loadRecyclerData();


//        mFirstTwoNumber = view.findViewById(R.id.linear_first_two_numbers);
//
//        mTxtNumFirst = view.findViewById(R.id.txt_num);
//        if (!mobFirstNum.equals("")) {
//            mTxtNumFirst.setText(mobFirstNum);
//        } else {
//            mTxtNumFirst.setVisibility(View.GONE);
//        }
//
//        if (!mobSecondNum.equals("")) {
//            mTxtNumSecond.setText(mobFirstNum);
//        } else {
//            mTxtNumFirst.setVisibility(View.GONE);
//        }
//
//        if (mobFirstNum.equals("") && mobSecondNum.equals("")) {
//            mFirstTwoNumber.setVisibility(View.GONE);
//        }
//
//        if (!mobThirdNum.equals("")) {
//            mTxtNumThird.setText(mobFirstNum);
//        } else {
//            mTxtNumThird.setVisibility(View.GONE);
//        }
//
        mCall = view.findViewById(R.id.card_ok);
        mCall.setOnClickListener(v -> {
            if (!selectedPhoneNumber.equals("mafish")) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+2" + selectedPhoneNumber));
                    startActivity(intent);
                } catch (ActivityNotFoundException activityException) {
                    Constant.showErrorDialog(getContext(), getContext().getString(R.string.error_while_calling_this_num));
                }
            }
        });

        mCancel = view.findViewById(R.id.card_cancel);
        mCancel.setOnClickListener(v -> {
            dismiss();
        });


    }

    private void loadRecyclerData() {
        try {
            Log.d("NUMS_LIST_SIZE", "loadRecyclerData: " + listNumbersDAta.size());
            CallAdapter adapter = new CallAdapter(getContext(), listNumbersDAta, this, this);
            mRV.setAdapter(adapter);
            Constant.runLayoutAnimation(mRV);
        } catch (NullPointerException ignored) {

        }

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


//    @Override
//    public void getSelectedRestaurantData(SelectedBeauty selectedRestaurant) {
//
//
//    }

    @Override
    public void getSelectedRadioBtn(int position) {
        selectedPhoneNumber = listNumbersDAta.get(position);
    }


    @Override
    public void onClick(int position) {

    }
}



