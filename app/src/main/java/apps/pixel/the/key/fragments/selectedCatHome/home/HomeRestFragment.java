package apps.pixel.the.key.fragments.selectedCatHome.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.OpenPdfActivity;
import apps.pixel.the.key.activites.retaurant.galleryActivity.GalleryActivity;
import apps.pixel.the.key.activites.sliderShow.sliderShowClass;
import apps.pixel.the.key.adapters.LatestNewsAdapter;
import apps.pixel.the.key.adapters.restaurant.HomeSliderRestaAdapter;
import apps.pixel.the.key.dialog.restuarant.DialogAllBranchesRes;
import apps.pixel.the.key.dialog.restuarant.DialogNewsDetails;
import apps.pixel.the.key.dialog.restuarant.DialogShare;
import apps.pixel.the.key.dialog.restuarant.call.DialogPhone;
import apps.pixel.the.key.models.NewsModel;
import apps.pixel.the.key.models.selectedRestaurant.SelectedRestaurant;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.ViewPagerCustomDuration;

public class HomeRestFragment extends Fragment implements LatestNewsAdapter.OnClickHandler, HomeInterface, View.OnClickListener, OnRefreshListener {

    private static final String TAG = "ExoPlayerActivity";


    public static AppCompatImageView favouritesImg;
    public static CairoRegularTextView phone;
    public static CairoRegularTextView favourites;
    public static CairoRegularTextView shareTxt;
    public static CairoRegularTextView views;
    public static ArrayList<String> listNumbersDAta;
   // public static SwipeRefreshLayout mRefreshLayout;
    public static ShimmerFrameLayout mShimmer;
    public static ArrayList<String> mListImgsPaths;
    // public CairoRegularTextView hotLine;
    public boolean hasBranches;
    String videoUri;
    SimpleExoPlayer player;
    Handler mHandler;
    Runnable mRunnable;
    private boolean hasNews;
    private int favourite, share, call;
    private String pdfUrl;
    private int isPdf = 0;
    // like == 1
    //unlike == 0
    private SharedPreferences sharedPreferences;
    private DialogNewsDetails dialogNewsDetails;
    private DialogPhone dialogPhone;
    private DialogShare dialogShare;
    private HomePresenter presenter;
    private AppCompatImageView mImgAbout;
    private LinearLayoutCompat expandableAmenities;
    private LinearLayoutCompat expandableAbout;
    private AppCompatImageView mImgShowMore;
    private CairoRegularTextView mTxtAvaiableServ;
    private ViewPagerCustomDuration viewPager;
    private TabLayout indicator;
    private List<String> imageViewPagerList;
    private RecyclerView mRV;
    private List<String> titleList;
    private List<String> descList;
    private List<NewsModel> urlsImages;
    private FrameLayout linearBranches;
    private String selectedRestaurantId;
    private CairoRegularTextView address;
    private CairoRegularTextView txtMessenger;
    private CairoRegularTextView time;
    private CairoRegularTextView website;
    private CairoRegularTextView mTxtType;
    private CairoRegularTextView desc;
    private FrameLayout mLinearNews;
    private LinearLayoutCompat condit;
    private LinearLayoutCompat wifi;
    private LinearLayoutCompat delivery;
    private LinearLayoutCompat parking;
    private LinearLayoutCompat foodArea;
    private LinearLayoutCompat escortAnimals;
    private LinearLayoutCompat oldAge;
    private LinearLayoutCompat kidsArea;
    private LinearLayoutCompat contract;
    private LinearLayoutCompat gym;
    private LinearLayoutCompat onlinePayment;
    private AppCompatImageView phoneImg;
    private AppCompatImageView shareImg;
    private AppCompatImageView viewImg;
    private AppCompatImageView menuImg;
    private HomeSliderRestaAdapter sliderAdapter;
    private int likeStatus;
    private String mobFirstNum, mobSecondNum, mobThirdtNum;
    private String newDesc;
    private String newImg;
    private boolean haveNumbers;

    private String selectedCat;

    private Handler handler;
    private int delay = 3000; //milliseconds
    private int page = 0;
    private HomeSliderRestaAdapter myAdapter;
    Runnable runnable = new Runnable() {
        public void run() {
            if (myAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_rest, container, false);
        sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);

        selectedRestaurantId = getArguments().getString(Constant.ITEM_SELECTED_ID);
        selectedCat = getArguments().getString(Constant.CAT_THAT_SELECTED);


        Log.d("ID_RESTAURANT", "onCreateView: " + selectedRestaurantId);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.ITEM_SELECTED_ID, selectedRestaurantId);
        editor.putString(Constant.CAT_THAT_SELECTED, selectedCat);

        editor.apply();

        handler = new Handler();
        initViews(rootView);

        likeStatus = sharedPreferences.getInt(Constant.Like_STATUS, 0);

        presenter = new HomePresenter(getContext(), this);

        switch (selectedCat) {
            case Constant.CAT_RESTAURANT_VALUE:
                presenter.getSelectedRestaurant(selectedRestaurantId);
                break;
            case Constant.CAT_BEAUTY_VALUE:
                mTxtType.setVisibility(View.GONE);
                presenter.getSelectedBeautyCenter(selectedRestaurantId);
                break;
            case Constant.CAT_GYM_VALUE:
                mTxtType.setVisibility(View.GONE);
                presenter.getSelectedGymCenter(selectedRestaurantId);
                break;
            case Constant.CAT_HOSPITAL_VALUE:
                mTxtType.setVisibility(View.GONE);
                presenter.getSelectedHospital(selectedRestaurantId);
                break;
            case Constant.CAT_PHARMACY_VALUE:
                mTxtType.setVisibility(View.GONE);
                presenter.getSelectedPharmacy(selectedRestaurantId);
                break;
        }

        return rootView;
    }


    private void initViews(View rootView) {

        expandableAbout = rootView.findViewById(R.id.expandable_about);
        expandableAbout.setVisibility(View.GONE);

        mShimmer = rootView.findViewById(R.id.shimmer);
        mShimmer.startShimmerAnimation();
        mListImgsPaths = new ArrayList<>();

        // mRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        // Constant.setSwipeLayourColor(getContext(), mRefreshLayout);
//        mRefreshLayout.setWaveColor(getResources().getColor(R.color.colorAccent));
        //refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));


        listNumbersDAta = new ArrayList<>();
        mLinearNews = rootView.findViewById(R.id.linear_news);
        if (!hasNews) {
            mLinearNews.setVisibility(View.GONE);
            Constant.expand(expandableAbout);
        } else {
            Constant.collapse(expandableAbout);
        }


        phoneImg = rootView.findViewById(R.id.phone_);

        favouritesImg = rootView.findViewById(R.id.favourites_);
        if (likeStatus == 0) {
            changeImageResource(getContext(), favouritesImg, R.drawable.ic_unlike);
        } else {
            changeImageResource(getContext(), favouritesImg, R.drawable.ic_like);
        }

        favouritesImg.setOnClickListener(this);

        mTxtAvaiableServ = rootView.findViewById(R.id.txt_no_avaible_ser);
        mTxtAvaiableServ.setVisibility(View.GONE);

        shareImg = rootView.findViewById(R.id.share_);
        shareImg.setOnClickListener(this);

        viewImg = rootView.findViewById(R.id.view_);
        viewImg.setOnClickListener(this);

        menuImg = rootView.findViewById(R.id.menu);
        menuImg.setOnClickListener(this);

        phone = rootView.findViewById(R.id.phone);

        favourites = rootView.findViewById(R.id.favourites);
        favourites.setOnClickListener(this);

        shareTxt = rootView.findViewById(R.id.share);
        shareTxt.setOnClickListener(this);

        views = rootView.findViewById(R.id.views);
        views.setOnClickListener(this);

        //abouts
        address = rootView.findViewById(R.id.address);
        txtMessenger = rootView.findViewById(R.id.mail);
        Constant.makeUnderlineForText(txtMessenger);

        time = rootView.findViewById(R.id.time);
        website = rootView.findViewById(R.id.website);
        mTxtType = rootView.findViewById(R.id.type);
        desc = rootView.findViewById(R.id.desc);
        //hotLine = rootView.findViewById(R.id.hotline);


        //amen
        condit = rootView.findViewById(R.id.condit);
        condit.setVisibility(View.GONE);
        wifi = rootView.findViewById(R.id.wifi);
        wifi.setVisibility(View.GONE);
        delivery = rootView.findViewById(R.id.delivery);
        delivery.setVisibility(View.GONE);
        parking = rootView.findViewById(R.id.parking);
        parking.setVisibility(View.GONE);
        foodArea = rootView.findViewById(R.id.food_area);
        foodArea.setVisibility(View.GONE);
        escortAnimals = rootView.findViewById(R.id.escort_animals);
        escortAnimals.setVisibility(View.GONE);
        oldAge = rootView.findViewById(R.id.old_age);
        oldAge.setVisibility(View.GONE);
        kidsArea = rootView.findViewById(R.id.kids_area);
        kidsArea.setVisibility(View.GONE);
        contract = rootView.findViewById(R.id.contract);
        contract.setVisibility(View.GONE);
        gym = rootView.findViewById(R.id.gym);
        gym.setVisibility(View.GONE);
        onlinePayment = rootView.findViewById(R.id.online_payment);
        onlinePayment.setVisibility(View.GONE);
        //____________

        dialogNewsDetails = new DialogNewsDetails();

        dialogPhone = new DialogPhone();
        dialogShare = new DialogShare();


        phoneImg.setOnClickListener(v ->
        {
            if (listNumbersDAta.size()>0)
            {
                switch (selectedCat) {
                    case Constant.CAT_RESTAURANT_VALUE:
                        presenter.getCalls(selectedRestaurantId);
                        break;
                    case Constant.CAT_BEAUTY_VALUE:
                        presenter.getCallsBeauty(selectedRestaurantId);
                        break;
                    case Constant.CAT_GYM_VALUE:
                        presenter.getCallsGym(selectedRestaurantId);
                        break;
                    case Constant.CAT_HOSPITAL_VALUE:
                        presenter.getCallsHospital(selectedRestaurantId);
                        break;
                    case Constant.CAT_PHARMACY_VALUE:
                        presenter.getCallspharmacies(selectedRestaurantId);
                        break;
                }


                call += 1;
                phone.setText(String.valueOf(call));
                dialogPhone.show(getFragmentManager(), "");
            } else {
                Constant.showInformationDialog(getContext(), getContext().getString(R.string.there_is_no_avaiable_num));
            }

        });
        phone.setOnClickListener(v -> {
            if (listNumbersDAta.size()>0) {
                switch (selectedCat) {
                    case Constant.CAT_RESTAURANT_VALUE:
                        presenter.getCalls(selectedRestaurantId);
                        break;
                    case Constant.CAT_BEAUTY_VALUE:
                        presenter.getCallsBeauty(selectedRestaurantId);
                        break;
                    case Constant.CAT_GYM_VALUE:
                        presenter.getCallsGym(selectedRestaurantId);
                        break;
                    case Constant.CAT_HOSPITAL_VALUE:
                        presenter.getCallsHospital(selectedRestaurantId);
                        break;
                    case Constant.CAT_PHARMACY_VALUE:
                        presenter.getCallspharmacies(selectedRestaurantId);
                        break;

                }
                call += 1;
                phone.setText(String.valueOf(call));
                dialogPhone.show(getFragmentManager(), "");
            } else {
                Constant.showInformationDialog(getContext(), getContext().getString(R.string.there_is_no_avaiable_num));
            }

        });

        linearBranches = rootView.findViewById(R.id.linear_branches);
        if (!hasBranches)
            linearBranches.setVisibility(View.GONE);
        else
            linearBranches.setVisibility(View.VISIBLE);

        linearBranches.setOnClickListener(v -> {
            DialogAllBranchesRes dialogAllBranchesRes = new DialogAllBranchesRes();
            dialogAllBranchesRes.show(getFragmentManager(), "");
        });


        mImgAbout = rootView.findViewById(R.id.txt_about);
        mImgAbout.setOnClickListener(v -> {
            if (expandableAbout.getVisibility() == View.GONE) {
                Constant.expand(expandableAbout);
            } else {
                Constant.collapse(expandableAbout);
            }
        });

        expandableAmenities = rootView.findViewById(R.id.expandable);
        expandableAmenities.setVisibility(View.GONE);
        mImgShowMore = rootView.findViewById(R.id.show_more);
        mImgShowMore.setOnClickListener(v -> {
            if (expandableAmenities.getVisibility() == View.GONE) {
                Constant.expand(expandableAmenities);
            } else {
                Constant.collapse(expandableAmenities);
            }
        });

        //rv
        mRV = rootView.findViewById(R.id.rc_latest_news);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRV.setLayoutManager(layoutManager);
        loadRecyclerData();


        //slider
        indicator = rootView.findViewById(R.id.indicator);
        viewPager = rootView.findViewById(R.id.viewPager);
        viewPager.setScrollDurationFactor(1.0);
        indicator = rootView.findViewById(R.id.indicator);


        //smart_refresh
/*
        mRefreshLayout.setOnRefreshListener(() -> {
            switch (selectedCat) {
                case Constant.CAT_RESTAURANT_VALUE:
                    presenter.getSelectedRestaurant(selectedRestaurantId);
                    mShimmer.startShimmerAnimation();
                    break;
                case Constant.CAT_BEAUTY_VALUE:
                    presenter.getSelectedBeautyCenter(selectedRestaurantId);
                    mShimmer.startShimmerAnimation();
                    break;
                case Constant.CAT_GYM_VALUE:
                    presenter.getSelectedGymCenter(selectedRestaurantId);
                    mShimmer.startShimmerAnimation();
                    break;
                case Constant.CAT_PHARMACY_VALUE:
                    presenter.getSelectedPharmacy(selectedRestaurantId);
                    mShimmer.startShimmerAnimation();
                    break;
                case Constant.CAT_HOSPITAL_VALUE:
                    presenter.getSelectedHospital(selectedRestaurantId);
                    mShimmer.startShimmerAnimation();
                    break;
            }
        });
*/
    }

    private void loadRecyclerData() {
        LatestNewsAdapter adapter = new LatestNewsAdapter(getContext(), descList, titleList, urlsImages, this);
        mRV.setAdapter(adapter);
    }


    @Override
    public void getSelectedItemInCatData(SelectedRestaurant selectedRestaurant) {

        Constant.FB_LINK = "";
        Constant.TWITTER_LINK = "";
        Constant.INSTAGRAM_LINK = "";

        int passed;

        try {
            selectedRestaurant.getOurbranches().get(0).getAddress();
            passed = 1;
        } catch (Exception ignored) {
            hasBranches = false;
            linearBranches.setVisibility(View.GONE);
            passed = 0;
        }

        if (passed == 1) {
            hasBranches = true;
            linearBranches.setVisibility(View.VISIBLE);
        }


        SharedPreferences.Editor editoor = sharedPreferences.edit();

        handlingOfShareDialog(selectedRestaurant);

        listNumbersDAta.clear();
        //mRefreshLayout.setRefreshing(false);
        Log.d("SIZE_OF_NUMBERS", "getSelectedItemInCatData: " + listNumbersDAta.size());

        handlingOfMenu(selectedRestaurant);

        handlingOfNumbers(selectedRestaurant);

        handlingOfNews(selectedRestaurant);

        handlingOfAmenities(selectedRestaurant);


        handlingOfMessenger(selectedRestaurant);

        handlingOfAbout(selectedRestaurant);


        //imageViewPagerList.clear();
        handlingOfSlider(selectedRestaurant, editoor);


        loadRecyclerData();

        favourite = Integer.valueOf(favourites.getText().toString());
        share = Integer.valueOf(shareTxt.getText().toString());
        call = Integer.valueOf(phone.getText().toString());

        try {
            videoUri = selectedRestaurant.getResturant().getVideo();
        } catch (Exception igored) {
            videoUri = "";
        }

        try {
            selectedRestaurant.getNews().get(0).getTitle();
            hasNews = true;
            mLinearNews.setVisibility(View.VISIBLE);

        } catch (Exception ignored) {
            hasNews = false;
            mLinearNews.setVisibility(View.GONE);

        }
    }

    private void handlingOfShareDialog(SelectedRestaurant selectedRestaurant) {
        try {
            Constant.FB_LINK = selectedRestaurant.getResturant().getFaceBook();
//            editoor.putString(Constant.FB_LINK, selectedRestaurant.getResturant().getFaceBook());
//            editoor.apply();

        } catch (Exception e) {
            Constant.FB_LINK = "";

        }
        try {
            Constant.INSTAGRAM_LINK = selectedRestaurant.getResturant().getInstagram();
        } catch (Exception ignored) {
            Constant.INSTAGRAM_LINK = "";

//            editoor.putString(Constant.INSTAGRAM_LINK, "");
//            editoor.apply();

        }
        try {
            Constant.TWITTER_LINK = selectedRestaurant.getResturant().getTwitter();
            // editoor.putString(Constant.TWITTER_LINK, selectedRestaurant.getResturant().getTwitter());
        } catch (Exception ignored) {
            Constant.TWITTER_LINK = "";

//            editoor.putString(Constant.TWITTER_LINK, "");
//            editoor.apply();

        }
    }

    private void handlingOfMessenger(SelectedRestaurant selectedRestaurant) {
        try {
            if (selectedRestaurant.getAbout().getMessenger().equals("null".trim())) {
                txtMessenger.setText(getString(R.string.not_available));
                return;
            } else {
                txtMessenger.setText(selectedRestaurant.getAbout().getMessenger());
            }
        } catch (Exception ignored) {
            txtMessenger.setText(getString(R.string.not_available));
            return;
        }

        txtMessenger.setOnClickListener(v -> {
            Intent intent;
            try {
                Log.d("LINK_MSG", "getSelectedItemInCatData: " + selectedRestaurant.getAbout().getMessenger());
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.me/" + selectedRestaurant.getAbout().getMessenger()));
                startActivity(intent);

                Log.d("LINK_IS", "getSelectedItemInCatData: " + Uri.parse("http://m.me/" + "POsignature"));
            } catch (Exception ignored) {
                Log.d("ERROR_", "getSelectedItemInCatData: " + ignored.getCause() + ignored.getCause());
            }

        });
    }

    @SuppressLint("SetTextI18n")
    private void handlingOfAbout(SelectedRestaurant selectedRestaurant) {
        switch (selectedCat) {
            case Constant.CAT_RESTAURANT_VALUE:

                address.setText(selectedRestaurant.getAbout().getAddress().trim());

                try {
                    if (selectedRestaurant.getAbout().getOpenAllDay().equals("true".trim()))
                        time.setText(getString(R.string.all_day));
                    else if (selectedRestaurant.getAbout().getOpenAllDay().equals("false".trim()))
                        time.setText(selectedRestaurant.getAbout().getStartTime() + " : " + selectedRestaurant.getAbout().getEndTime());
                } catch (Exception e) {
                    time.setText(getString(R.string.all_day));
                }

                try {
                    if (Constant.getLng(getContext()).equals("ar")) {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_NameAR());
                    } else {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_Name());
                    }
                } catch (Exception ignored) {

                }


                try {
                    if (selectedRestaurant.getAbout().getWebsite().equals("null")) {
                        website.setText(getString(R.string.not_available));
                    } else {
                        website.setText(selectedRestaurant.getAbout().getWebsite());
                        // }
                    }
                } catch (Exception e) {
                    website.setText(getString(R.string.not_available));
                }
                desc.setText(selectedRestaurant.getAbout().getDescription());

//
//        try {
//            hotLine.setText(selectedRestaurant.getAbout().getHotLine());
//        } catch (NullPointerException ignored) {
//            hotLine.setText(getString(R.string.not_available));
//        }


                try {
                    favourites.setText(selectedRestaurant.getResturant().getFavourits());
                } catch (Exception inogred) {

                }

                try {
                    shareTxt.setText(selectedRestaurant.getResturant().getShare());
                } catch (Exception inogred) {

                }

                try {
                    views.setText(selectedRestaurant.getResturant().getViews());
                } catch (Exception inogred) {

                }

                try {
                    phone.setText(selectedRestaurant.getResturant().getCalls());
                } catch (Exception inogred) {

                }


                break;
            case Constant.CAT_BEAUTY_VALUE:
                address.setText(selectedRestaurant.getAbout().getAddress().trim());

                try {
                    if (selectedRestaurant.getAbout().getOpenAllDay().equals("true".trim()))
                        time.setText(getString(R.string.all_day));
                    else if (selectedRestaurant.getAbout().getOpenAllDay().equals("false".trim()))
                        time.setText(selectedRestaurant.getAbout().getStartTime() + " : " + selectedRestaurant.getAbout().getEndTime());
                } catch (Exception e) {
                    time.setText(getString(R.string.all_day));
                }

                try {
                    if (Constant.getLng(getContext()).equals("ar")) {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_NameAR());
                    } else {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_Name());
                    }
                } catch (Exception ignored) {

                }


                try {
                    if (selectedRestaurant.getAbout().getWebsite().equals("null".trim())) {
                        website.setText(getString(R.string.not_available));
                    } else {
                        website.setText(selectedRestaurant.getAbout().getWebsite());
                        // }
                    }
                } catch (Exception e) {
                    website.setText(getString(R.string.not_available));
                }
                desc.setText(selectedRestaurant.getAbout().getDescription());

                try {
                    favourites.setText(selectedRestaurant.getBeauty().getFavourits());
                } catch (Exception ignored) {

                }
                try {
                    shareTxt.setText(selectedRestaurant.getBeauty().getShare());
                } catch (Exception ignored) {

                }

                try {
                    views.setText(selectedRestaurant.getBeauty().getViews());
                } catch (Exception ignored) {

                }

                try {
                    phone.setText(selectedRestaurant.getBeauty().getCalls());
                } catch (Exception ignored) {

                }

                break;
            case Constant.CAT_GYM_VALUE:

                address.setText(selectedRestaurant.getAbout().getAddress().trim());

                try {
                    if (selectedRestaurant.getAbout().getOpenAllDay().equals("true".trim()))
                        time.setText(getString(R.string.all_day));
                    else if (selectedRestaurant.getAbout().getOpenAllDay().equals("false".trim()))
                        time.setText(selectedRestaurant.getAbout().getStartTime() + " : " + selectedRestaurant.getAbout().getEndTime());
                } catch (Exception e) {
                    time.setText(getString(R.string.all_day));
                }

                try {
                    if (Constant.getLng(getContext()).equals("ar")) {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_NameAR());
                    } else {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_Name());
                    }
                } catch (Exception ignored) {

                }


                try {
                    if (selectedRestaurant.getAbout().getWebsite().equals("null".trim())) {
                        website.setText(getString(R.string.not_available));
                    } else {
                        website.setText(selectedRestaurant.getAbout().getWebsite());
                        // }
                    }
                } catch (Exception e) {
                    website.setText(getString(R.string.not_available));
                }
                desc.setText(selectedRestaurant.getAbout().getDescription());

//
//        try {
//            hotLine.setText(selectedRestaurant.getAbout().getHotLine());
//        } catch (NullPointerException ignored) {
//            hotLine.setText(getString(R.string.not_available));
//        }


                try {
                    favourites.setText(selectedRestaurant.getGym().getFavourits());
                } catch (Exception ignored) {

                }
                try {
                    shareTxt.setText(selectedRestaurant.getGym().getShare());
                } catch (Exception ignored) {

                }

                try {
                    views.setText(selectedRestaurant.getGym().getViews());
                } catch (Exception ignored) {

                }

                try {
                    phone.setText(selectedRestaurant.getGym().getCalls());
                } catch (Exception ignored) {

                }

                break;

            case Constant.CAT_HOSPITAL_VALUE:

                address.setText(selectedRestaurant.getAbout().getAddress().trim());

                try {
                    if (selectedRestaurant.getAbout().getOpenAllDay().equals("true".trim()))
                        time.setText(getString(R.string.all_day));
                    else if (selectedRestaurant.getAbout().getOpenAllDay().equals("false".trim()))
                        time.setText(selectedRestaurant.getAbout().getStartTime() + " : " + selectedRestaurant.getAbout().getEndTime());
                } catch (Exception e) {
                    time.setText(getString(R.string.all_day));
                }

                try {
                    if (Constant.getLng(getContext()).equals("ar")) {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_NameAR());
                    } else {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_Name());
                    }
                } catch (Exception ignored) {

                }


                try {
                    if (selectedRestaurant.getAbout().getWebsite().equals("null".trim())) {
                        website.setText(getString(R.string.not_available));
                    } else {
                        website.setText(selectedRestaurant.getAbout().getWebsite());
                        // }
                    }
                } catch (Exception e) {
                    website.setText(getString(R.string.not_available));
                }
                desc.setText(selectedRestaurant.getAbout().getDescription());

//
//        try {
//            hotLine.setText(selectedRestaurant.getAbout().getHotLine());
//        } catch (NullPointerException ignored) {
//            hotLine.setText(getString(R.string.not_available));
//        }


                try {
                    favourites.setText(selectedRestaurant.getHospital().getFavourits());
                } catch (Exception ignored) {

                }
                try {
                    shareTxt.setText(selectedRestaurant.getHospital().getShare());
                } catch (Exception ignored) {

                }

                try {
                    views.setText(selectedRestaurant.getHospital().getViews());
                } catch (Exception ignored) {

                }

                try {
                    phone.setText(selectedRestaurant.getHospital().getCalls());
                } catch (Exception ignored) {

                }

                break;
            case Constant.CAT_PHARMACY_VALUE:

                address.setText(selectedRestaurant.getAbout().getAddress().trim());

                try {
                    if (selectedRestaurant.getAbout().getOpenAllDay().equals("true".trim()))
                        time.setText(getString(R.string.all_day));
                    else if (selectedRestaurant.getAbout().getOpenAllDay().equals("false".trim()))
                        time.setText(selectedRestaurant.getAbout().getStartTime() + " : " + selectedRestaurant.getAbout().getEndTime());
                } catch (Exception e) {
                    time.setText(getString(R.string.all_day));
                }

                try {
                    if (Constant.getLng(getContext()).equals("ar")) {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_NameAR());
                    } else {
                        mTxtType.setText(selectedRestaurant.getAbout().getRestaurantType_Name());
                    }
                } catch (Exception ignored) {

                }


                try {
                    if (selectedRestaurant.getAbout().getWebsite().equals("null".trim())) {
                        website.setText(getString(R.string.not_available));
                    } else {
                        website.setText(selectedRestaurant.getAbout().getWebsite());
                        // }
                    }
                } catch (Exception e) {
                    website.setText(getString(R.string.not_available));
                }
                desc.setText(selectedRestaurant.getAbout().getDescription());

//
//        try {
//            hotLine.setText(selectedRestaurant.getAbout().getHotLine());
//        } catch (NullPointerException ignored) {
//            hotLine.setText(getString(R.string.not_available));
//        }


                try {
                    favourites.setText(selectedRestaurant.getPharmacy().getFavourits());
                } catch (Exception ignored) {

                }
                try {
                    shareTxt.setText(selectedRestaurant.getPharmacy().getShare());
                } catch (Exception ignored) {

                }

                try {
                    views.setText(selectedRestaurant.getPharmacy().getViews());
                } catch (Exception ignored) {

                }

                try {
                    phone.setText(selectedRestaurant.getPharmacy().getCalls());
                } catch (Exception ignored) {

                }

                break;

        }


    }

    private void handlingOfNumbers(SelectedRestaurant selectedRestaurant) {
        try {
            /*haveNumbers = !selectedRestaurant.getAbout().getVodafone().equals("null") ||
                    !selectedRestaurant.getAbout().getEtisalat().equals("null") ||
                    !selectedRestaurant.getAbout().getWe().equals("null") ||
                    !selectedRestaurant.getAbout().getOrange().equals("null")||
                    !selectedRestaurant.getAbout().getHotLine().equals("null");*/


            try {
                listNumbersDAta.clear();
            } catch (Exception ignored) {

            }
            try {
                if (!selectedRestaurant.getAbout().getVodafone().equals("null")) {
                    listNumbersDAta.add(selectedRestaurant.getAbout().getVodafone());
                }
            } catch (Exception ignored) {
            }
            try {
                if (!selectedRestaurant.getAbout().getWe().equals("null")) {
                    listNumbersDAta.add(selectedRestaurant.getAbout().getWe());
                }
            } catch (Exception ignored) {
            }

            try {
                if (!selectedRestaurant.getAbout().getEtisalat().equals("null")) {
                    listNumbersDAta.add(selectedRestaurant.getAbout().getEtisalat());
                }
            } catch (Exception ignored) {
            }

            try {
                if (!selectedRestaurant.getAbout().getOrange().equals("null")) {
                    listNumbersDAta.add(selectedRestaurant.getAbout().getOrange());
                }
            } catch (Exception ignored) {
            }

            try {
                if (!selectedRestaurant.getAbout().getHotLine().equals("null")) {
                    listNumbersDAta.add(selectedRestaurant.getAbout().getHotLine());
                }
            } catch (Exception ignored) {
            }


        } catch (Exception ignored) {
            haveNumbers = false;
            // phoneNum.setText(getString(R.string.not_available));
        }
    }

    private void handlingOfNews(SelectedRestaurant selectedRestaurant) {
        titleList = new ArrayList<>();
        urlsImages = new ArrayList<>();
        descList = new ArrayList<>();

        for (int i = 0; i < selectedRestaurant.getNews().size(); i++) {
            titleList.add(selectedRestaurant.getNews().get(i).getTitle());
            if (selectedRestaurant.getNews().get(i).getStatus().equals("false".trim())) {
                urlsImages.add(new NewsModel(Constant.BASE_PATH_MEDIA + selectedRestaurant.getNews().get(i).getPathURL(), "true".trim()));
                Log.d("IMGS_", "handlingOfNews: " + Constant.BASE_PATH_MEDIA + selectedRestaurant.getNews().get(i).getPathURL());
            } else {
                urlsImages.add(new NewsModel(Constant.BASE_PATH_MEDIA + selectedRestaurant.getNews().get(i).getPathURL(), "false".trim()));
                Log.d("IMGS_NOT", "handlingOfNews: " + Constant.BASE_PATH_MEDIA + selectedRestaurant.getNews().get(i).getPathURL());
            }
            descList.add(selectedRestaurant.getNews().get(i).getDescription());
        }
    }

    private void handlingOfAmenities(SelectedRestaurant selectedRestaurant) {
        if (selectedRestaurant.getAmenities().getConditioning().equals("false".trim())) {
            condit.setVisibility(View.GONE);
        } else {
            condit.setVisibility(View.VISIBLE);
        }

        if (selectedRestaurant.getAmenities().getWifi().equals("false".trim())) {
            wifi.setVisibility(View.GONE);
        } else {
            wifi.setVisibility(View.VISIBLE);
        }
        if (selectedRestaurant.getAmenities().getDelivery().equals("false".trim())) {
            delivery.setVisibility(View.GONE);
        } else {
            delivery.setVisibility(View.VISIBLE);
        }
        if (selectedRestaurant.getAmenities().getParking().equals("false".trim())) {
            parking.setVisibility(View.GONE);
        } else {
            parking.setVisibility(View.VISIBLE);
        }
        if (selectedRestaurant.getAmenities().getFoodArea().equals("false".trim())) {
            foodArea.setVisibility(View.GONE);
        } else {
            foodArea.setVisibility(View.VISIBLE);
        }
        if (selectedRestaurant.getAmenities().getEscort().equals("false".trim())) {
            escortAnimals.setVisibility(View.GONE);
        } else {
            escortAnimals.setVisibility(View.VISIBLE);
        }

        if (selectedRestaurant.getAmenities().getOldAge().equals("false".trim())) {
            oldAge.setVisibility(View.GONE);
        } else {
            oldAge.setVisibility(View.VISIBLE);
        }

        if (selectedRestaurant.getAmenities().getKidsArea().equals("false".trim())) {
            kidsArea.setVisibility(View.GONE);
        } else {
            kidsArea.setVisibility(View.VISIBLE);
        }
        if (selectedRestaurant.getAmenities().getGym().equals("false".trim())) {
            gym.setVisibility(View.GONE);
        } else {
            gym.setVisibility(View.VISIBLE);
        }
        if (selectedRestaurant.getAmenities().getOnline().equals("false".trim())) {
            onlinePayment.setVisibility(View.GONE);
        } else {
            onlinePayment.setVisibility(View.VISIBLE);
        }

        if (selectedRestaurant.getAmenities().getContact().equals("false".trim())) {
            contract.setVisibility(View.GONE);
        } else {
            contract.setVisibility(View.VISIBLE);
        }
    }

    private void handlingOfMenu(SelectedRestaurant selectedRestaurant) {
        try {
            if (selectedRestaurant.getResturant().getMenu().size()>0)
            {
                if (selectedRestaurant.getResturant().getMenu().get(0).getStatus().equals("true")) {
                    isPdf = 1;
                    pdfUrl = Constant.BASE_PATH_MEDIA + selectedRestaurant.getResturant().getMenu().get(0).getImages();
                } else {
                    isPdf = 2;
                    for (int i = 0; i < selectedRestaurant.getResturant().getMenu().size(); i++) {
                        mListImgsPaths.add(Constant.BASE_PATH_MEDIA + selectedRestaurant.getResturant().getMenu().get(i).getImages());
                        //Log.d(TAG, "getSelectedItemInCatData: " + mListImgsPaths.get(i));
                    }
                }
            }

            // TODO MAKE MENU // true : contain only pdf , false contain list of images
            //  pdfUrl = Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getMenu();
        } catch (Exception e) {
            Log.d("ERROR_", "getSelectedItemInCatData: " + e.getMessage() + e.getCause());
        }
    }

    private void handlingOfSlider(SelectedRestaurant selectedRestaurant, SharedPreferences.Editor editoor) {
        if (selectedCat.equals(Constant.CAT_RESTAURANT_VALUE)) {
            imageViewPagerList = new ArrayList<>();
            try {

                if (!selectedRestaurant.getResturant().getVideo().equals("")) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP);
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "true".trim());
                    editoor.apply();
                } else {
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "false".trim());
                    editoor.apply();
                }

            } catch (Exception ignored) {
                editoor.putString(Constant.SLIDER_HAS_VIDEO, "false".trim());
                editoor.apply();
            }
            try {
                Log.d("handlingOfSlider", "handlingOfSlider: " + selectedRestaurant.getResturant().getVideo().equals(""));
                for (int i = 0; i < selectedRestaurant.getResturant().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getImages().get(i).getImage());

                }
            } catch (Exception ignored) {
//            imageViewPagerList.add("Constant.BASE_URL_HTTP".trim() + selectedRestaurant.getResturant().getImages().get(i).getImage());
                for (int i = 0; i < selectedRestaurant.getResturant().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP
                            + selectedRestaurant.getResturant().getImages().get(i).getImage());
                }
            }


            try {
                editoor.putString(Constant.KEY_VIDEO_URL, Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getVideo());
                editoor.apply();

            } catch (Exception e) {
                editoor.putString(Constant.KEY_VIDEO_URL, "");
                editoor.apply();
            }


            sliderAdapter = new HomeSliderRestaAdapter(getContext(), imageViewPagerList);
            viewPager.setAdapter(sliderAdapter);
            indicator.setupWithViewPager(viewPager, true);

            Log.d("VIDEO_URL", "getSelectedItemInCatData: " + Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getVideo());
            editoor.apply();
        } else if (selectedCat.equals(Constant.CAT_BEAUTY_VALUE)) {
            imageViewPagerList = new ArrayList<>();
            try {

                if (!selectedRestaurant.getBeauty().getVideo().equals("")) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP);
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "true".trim());
                    editoor.apply();
                } else {
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "false".trim());
                    editoor.apply();
                }

            } catch (Exception ignored) {
                editoor.putString(Constant.SLIDER_HAS_VIDEO, "false".trim());
                editoor.apply();
            }
            try {
                for (int i = 0; i < selectedRestaurant.getBeauty().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP + selectedRestaurant.getBeauty().getImages().get(i).getImage());

                }
            } catch (Exception ignored) {
//            imageViewPagerList.add("Constant.BASE_URL_HTTP".trim() + selectedRestaurant.getResturant().getImages().get(i).getImage());
                for (int i = 0; i < selectedRestaurant.getBeauty().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP
                            + selectedRestaurant.getBeauty().getImages().get(i).getImage());
                }
            }


            try {
                editoor.putString(Constant.KEY_VIDEO_URL, Constant.BASE_URL_HTTP + selectedRestaurant.getBeauty().getVideo());
                editoor.apply();

            } catch (Exception e) {
                editoor.putString(Constant.KEY_VIDEO_URL, "");
                editoor.apply();
            }


            sliderAdapter = new HomeSliderRestaAdapter(getContext(), imageViewPagerList);
            viewPager.setAdapter(sliderAdapter);
            indicator.setupWithViewPager(viewPager, true);

            Log.d("VIDEO_URL", "getSelectedItemInCatData: " + Constant.BASE_URL_HTTP+ selectedRestaurant.getBeauty().getVideo());
            editoor.apply();
        } else if (selectedCat.equals(Constant.CAT_GYM_VALUE)) {
            imageViewPagerList = new ArrayList<>();
            try {

                if (!selectedRestaurant.getGym().getVideo().equals("")) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP);
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "true");
                    editoor.apply();
                } else {
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "false");
                    editoor.apply();
                }

            } catch (Exception ignored) {
                editoor.putString(Constant.SLIDER_HAS_VIDEO, "false");
                editoor.apply();
            }
            try {
                for (int i = 0; i < selectedRestaurant.getGym().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP + selectedRestaurant.getGym().getImages().get(i).getImage());

                }
            } catch (Exception ignored) {
//            imageViewPagerList.add("Constant.BASE_URL_HTTP"Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getImages().get(i).getImage());
                for (int i = 0; i < selectedRestaurant.getGym().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP
                            + selectedRestaurant.getGym().getImages().get(i).getImage());
                }
            }


            try {
                editoor.putString(Constant.KEY_VIDEO_URL, Constant.BASE_URL_HTTP + selectedRestaurant.getGym().getVideo());
                editoor.apply();

            } catch (Exception e) {
                editoor.putString(Constant.KEY_VIDEO_URL, "");
                editoor.apply();
            }


            sliderAdapter = new HomeSliderRestaAdapter(getContext(), imageViewPagerList);
            viewPager.setAdapter(sliderAdapter);
            indicator.setupWithViewPager(viewPager, true);

            Log.d("VIDEO_URL", "getSelectedItemInCatData: " + Constant.BASE_URL_HTTP + selectedRestaurant.getGym().getVideo());
            editoor.apply();

        } else if (selectedCat.equals(Constant.CAT_HOSPITAL_VALUE)) {
            imageViewPagerList = new ArrayList<>();
            try {

                if (!selectedRestaurant.getHospital().getVideo().equals("")) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP);
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "true");
                    editoor.apply();
                } else {
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "false");
                    editoor.apply();
                }

            } catch (Exception ignored) {
                editoor.putString(Constant.SLIDER_HAS_VIDEO, "false");
                editoor.apply();
            }
            try {
                for (int i = 0; i < selectedRestaurant.getHospital().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP+ selectedRestaurant.getHospital().getImages().get(i).getImage());

                }
            } catch (Exception ignored) {
//            imageViewPagerList.add(Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getImages().get(i).getImage());
                for (int i = 0; i < selectedRestaurant.getHospital().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP
                            + selectedRestaurant.getHospital().getImages().get(i).getImage());
                }
            }


            try {
                editoor.putString(Constant.KEY_VIDEO_URL, Constant.BASE_URL_HTTP + selectedRestaurant.getHospital().getVideo());
                editoor.apply();

            } catch (Exception e) {
                editoor.putString(Constant.KEY_VIDEO_URL, "");
                editoor.apply();
            }


            sliderAdapter = new HomeSliderRestaAdapter(getContext(), imageViewPagerList);
            viewPager.setAdapter(sliderAdapter);
            indicator.setupWithViewPager(viewPager, true);

            Log.d("VIDEO_URL", "getSelectedItemInCatData: " + Constant.BASE_URL_HTTP + selectedRestaurant.getGym().getVideo());
            editoor.apply();

        } else if (selectedCat.equals(Constant.CAT_PHARMACY_VALUE)) {
            imageViewPagerList = new ArrayList<>();
            try {

                if (!selectedRestaurant.getPharmacy().getVideo().equals("")) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP);
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "true");
                    editoor.apply();
                } else {
                    editoor.putString(Constant.SLIDER_HAS_VIDEO, "false");
                    editoor.apply();
                }

            } catch (Exception ignored) {
                editoor.putString(Constant.SLIDER_HAS_VIDEO, "false");
                editoor.apply();
            }
            try {
                for (int i = 0; i < selectedRestaurant.getPharmacy().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP + selectedRestaurant.getPharmacy().getImages().get(i).getImage());

                }
            } catch (Exception ignored) {
//            imageViewPagerList.add(Constant.BASE_URL_HTTP + selectedRestaurant.getResturant().getImages().get(i).getImage());
                for (int i = 0; i < selectedRestaurant.getPharmacy().getImages().size(); i++) {
                    imageViewPagerList.add(Constant.BASE_URL_HTTP
                            + selectedRestaurant.getPharmacy().getImages().get(i).getImage());
                }
            }


            try {
                editoor.putString(Constant.KEY_VIDEO_URL, Constant.BASE_URL_HTTP + selectedRestaurant.getPharmacy().getVideo());
                editoor.apply();

            } catch (Exception e) {
                editoor.putString(Constant.KEY_VIDEO_URL, "");
                editoor.apply();
            }


            sliderAdapter = new HomeSliderRestaAdapter(getContext(), imageViewPagerList);
            viewPager.setAdapter(sliderAdapter);
            indicator.setupWithViewPager(viewPager, true);

            Log.d("VIDEO_URL", "getSelectedItemInCatData: " + Constant.BASE_URL_HTTP + selectedRestaurant.getGym().getVideo());
            editoor.apply();

        }

        mShimmer.stopShimmerAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.favourites_:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (favouritesImg.getDrawable().getConstantState() == getContext().getResources().getDrawable(R.drawable.ic_unlike).getConstantState()) {
                    switch (selectedCat) {
                        case Constant.CAT_RESTAURANT_VALUE:
                            presenter.getLikes(selectedRestaurantId);
                            break;
                        case Constant.CAT_BEAUTY_VALUE:
                            presenter.getLikesBeauty(selectedRestaurantId);
                            break;
                        case Constant.CAT_GYM_VALUE:
                            presenter.getLikesGym(selectedRestaurantId);
                            break;
                        case Constant.CAT_HOSPITAL_VALUE:
                            presenter.getLikesHospital(selectedRestaurantId);
                            break;
                        case Constant.CAT_PHARMACY_VALUE:
                            presenter.getLikespharmacies(selectedRestaurantId);
                            break;

                    }


                    favourite += 1;
                    changeImageResource(getContext(), favouritesImg, R.drawable.ic_like);
                    favourites.setText(String.valueOf(favourite));
                } else if (favouritesImg.getDrawable().getConstantState() == getContext().getResources().getDrawable(R.drawable.ic_like).getConstantState()) {
                    switch (selectedCat) {
                        case Constant.CAT_RESTAURANT_VALUE:
                            presenter.getUnLikes(selectedRestaurantId);
                            break;
                        case Constant.CAT_BEAUTY_VALUE:
                            presenter.getUnLikesBeauty(selectedRestaurantId);
                            break;
                        case Constant.CAT_GYM_VALUE:
                            presenter.getUnLikesGym(selectedRestaurantId);
                            break;
                        case Constant.CAT_HOSPITAL_VALUE:
                            presenter.getUnLikesHospital(selectedRestaurantId);
                        case Constant.CAT_PHARMACY_VALUE:
                            presenter.getUnLikespharmacies(selectedRestaurantId);
                            break;


                    }

                    changeImageResource(getContext(), favouritesImg, R.drawable.ic_unlike);
                    if (favourite > 0) {
                        favourite -= 1;
                        favourites.setText(String.valueOf(favourite));
                    }
                }
//                if (likeStatus == 0) {
//                    presenter.getLikes(selectedRestaurantId);
//                    editor.putInt(Like_STATUS, 1);
//                    editor.apply();
//                } else {
//                    editor.putInt(Like_STATUS, 0);
//                    editor.apply();
//                }
                break;
            case R.id.favourites:
                if (favouritesImg.getDrawable().getConstantState() == getContext().getResources().getDrawable(R.drawable.ic_unlike).getConstantState()) {
                    switch (selectedCat) {
                        case Constant.CAT_RESTAURANT_VALUE:
                            presenter.getLikes(selectedRestaurantId);
                            break;
                        case Constant.CAT_BEAUTY_VALUE:
                            presenter.getLikesBeauty(selectedRestaurantId);
                            break;
                        case Constant.CAT_GYM_VALUE:
                            presenter.getLikesGym(selectedRestaurantId);
                            break;
                        case Constant.CAT_HOSPITAL_VALUE:
                            presenter.getLikesHospital(selectedRestaurantId);
                            break;
                        case Constant.CAT_PHARMACY_VALUE:
                            presenter.getLikespharmacies(selectedRestaurantId);
                            break;

                    }
                    favourite += 1;
                    changeImageResource(getContext(), favouritesImg, R.drawable.ic_like);
                    favourites.setText(String.valueOf(favourite));
                } else if (favouritesImg.getDrawable().getConstantState() == getContext().getResources().getDrawable(R.drawable.ic_like).getConstantState()) {
                    switch (selectedCat) {
                        case Constant.CAT_RESTAURANT_VALUE:
                            presenter.getUnLikes(selectedRestaurantId);
                            break;
                        case Constant.CAT_BEAUTY_VALUE:
                            presenter.getUnLikesBeauty(selectedRestaurantId);
                            break;
                        case Constant.CAT_GYM_VALUE:
                            presenter.getUnLikesGym(selectedRestaurantId);
                            break;
                        case Constant.CAT_HOSPITAL_VALUE:
                            presenter.getUnLikesHospital(selectedRestaurantId);
                            break;
                        case Constant.CAT_PHARMACY_VALUE:
                            presenter.getUnLikespharmacies(selectedRestaurantId);
                            break;

                    }


                    changeImageResource(getContext(), favouritesImg, R.drawable.ic_unlike);
                    favourite -= 1;
                    if (favourite != 0)
                        favourites.setText(String.valueOf(favourite));
                }
                break;
            case R.id.share_:
                switch (selectedCat) {
                    case Constant.CAT_RESTAURANT_VALUE:
                        presenter.getShares(selectedRestaurantId);
                        break;
                    case Constant.CAT_BEAUTY_VALUE:
                        presenter.getSharesBeauty(selectedRestaurantId);
                        break;
                    case Constant.CAT_GYM_VALUE:
                        presenter.getSharesGym(selectedRestaurantId);
                        break;
                    case Constant.CAT_HOSPITAL_VALUE:
                        presenter.getSharesHospital(selectedRestaurantId);
                        break;
                    case Constant.CAT_PHARMACY_VALUE:
                        presenter.getSharespharmacies(selectedRestaurantId);
                        break;
                }


                share += 1;
                shareTxt.setText(String.valueOf(share));
                if (!dialogShare.isAdded()) {
                    dialogShare.show(getFragmentManager(), "");
                }
                break;
            case R.id.share:
                switch (selectedCat) {
                    case Constant.CAT_RESTAURANT_VALUE:
                        presenter.getShares(selectedRestaurantId);
                        break;
                    case Constant.CAT_BEAUTY_VALUE:
                        presenter.getSharesBeauty(selectedRestaurantId);
                        break;
                    case Constant.CAT_GYM_VALUE:
                        presenter.getSharesGym(selectedRestaurantId);
                        break;
                    case Constant.CAT_HOSPITAL_VALUE:
                        presenter.getSharesHospital(selectedRestaurantId);
                        break;
                    case Constant.CAT_PHARMACY_VALUE:
                        presenter.getSharespharmacies(selectedRestaurantId);
                        break;


                }

                share += 1;
                shareTxt.setText(String.valueOf(share));
                if (!dialogShare.isAdded()) {
                    dialogShare.show(getFragmentManager(), "");
                }

                break;
            case R.id.menu:
                Log.i("IS_PDF_", "onClick: " + isPdf);
                if (isPdf==1) {
                    Intent openPdf = new Intent(getContext(), OpenPdfActivity.class);
                    openPdf.putExtra(Constant.PDF_URL, pdfUrl);
                    startActivity(openPdf);
                    Animatoo.animateSlideLeft(getContext());
                }else if (isPdf==2){
                    /*Intent openGallery = new Intent(getContext(), GalleryActivity.class);
                    openGallery.putStringArrayListExtra(Constant.GALLERY_IMGS, mListImgsPaths);
                    Log.d(TAG, "onClick: " + mListImgsPaths.size());
                    startActivity(openGallery);
                    Animatoo.animateSlideLeft(getContext());*/
                    Intent openGallery = new Intent(getContext(), sliderShowClass.class);
                    openGallery.putStringArrayListExtra(Constant.GALLERY_IMGS, mListImgsPaths);
                    Log.d(TAG, "onClick: " + mListImgsPaths.size());
                    startActivity(openGallery);
                    Animatoo.animateSlideLeft(getContext());

                    //startActivity(new Intent(getContext(), sliderShowClass.class ));

                }
                else
                {
                    Constant.showErrorDialog(getContext(), getActivity().getString(R.string.Not_Found));
                }
                break;

        }
    }

    @Override
    public void onNewsItemClick(String isImg, int position) {
        String desc = descList.get(position);
        String img = urlsImages.get(position).getApth();
        String title = titleList.get(position);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.NEWS_IMG, img);
        editor.putString(Constant.NEWS_TITLE, title);
        editor.putString(Constant.NEWS_DESC, desc);
        editor.putString(Constant.IS_IMG, isImg);

        editor.apply();

        if (!dialogNewsDetails.isAdded()) {
            dialogNewsDetails.show(getFragmentManager(), "");
        }

    }

    public void changeImageResource(Context context, AppCompatImageView imageView, int path) {
        imageView.setImageDrawable(context.getResources().getDrawable(path));
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (selectedCat.equals(Constant.CAT_RESTAURANT_VALUE)) {
            presenter.getSelectedRestaurant(selectedRestaurantId);
        } else if (selectedCat.equals(Constant.CAT_BEAUTY_VALUE)) {
            presenter.getSelectedBeautyCenter(selectedRestaurantId);
        }
    }
}
