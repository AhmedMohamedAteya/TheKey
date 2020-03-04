package apps.pixel.the.key.dialog.restuarant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.URLUtil;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.ShareWebViewActivity;
import apps.pixel.the.key.utilities.Constant;

public class DialogShare extends DialogFragment implements View.OnClickListener {
    private SharedPreferences sharedPreferences;

    private View seperatorOne;
    private View seperatorTwo;

    private AppCompatImageView imgFb;
    private AppCompatImageView imgTwitter;
    private AppCompatImageView imgInstagram;

    private String fbLink, twitterLink, instagramLink;


    private int choosenIcon; //checkOnClickFun

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_share, container, true);
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

        //sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);

        fbLink = Constant.FB_LINK;
        twitterLink = Constant.TWITTER_LINK;
        instagramLink = Constant.INSTAGRAM_LINK;


        Log.d("LINK_IS", "initViews: " + fbLink);

        seperatorOne = view.findViewById(R.id.seperator_one);
        seperatorTwo = view.findViewById(R.id.seperator_two);

        imgFb = view.findViewById(R.id.img_fb);
        imgFb.setOnClickListener(this);
        imgTwitter = view.findViewById(R.id.img_twitter);
        imgTwitter.setOnClickListener(this);
        imgInstagram = view.findViewById(R.id.img_instagram);
        imgInstagram.setOnClickListener(this);


        try {
            if (!URLUtil.isValidUrl(twitterLink)) {
                imgTwitter.setVisibility(View.GONE);
                seperatorOne.setVisibility(View.GONE);
            }
        } catch (NullPointerException ignored) {
            imgTwitter.setVisibility(View.GONE);
            seperatorOne.setVisibility(View.GONE);
        }

        try {
            if (!URLUtil.isValidUrl(instagramLink)) {
                imgInstagram.setVisibility(View.GONE);
                seperatorTwo.setVisibility(View.GONE);
            }
        } catch (NullPointerException ignored) {
            imgInstagram.setVisibility(View.GONE);
            seperatorTwo.setVisibility(View.GONE);
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

    @Override
    public void onClick(View v) {
        Intent openUrl = new Intent(getContext(), ShareWebViewActivity.class);
        switch (v.getId()) {
            case R.id.img_fb:
                choosenIcon = 1;
                Log.d("FB_LINK", "onClick: " + fbLink);
                try {
                    if (!fbLink.equals(""))
                        openUrl.putExtra(Constant.SHARE_LINK, fbLink);
                } catch (NullPointerException ignored) {
                    imgFb.setVisibility(View.GONE);
                }

                break;
            case R.id.img_twitter:
                choosenIcon = 2;

                try {
                    if (!twitterLink.equals(""))
                        openUrl.putExtra(Constant.SHARE_LINK, twitterLink);
                } catch (NullPointerException ignored) {
                    imgTwitter.setVisibility(View.GONE);
                }
                break;
            case R.id.img_instagram:
                choosenIcon = 3;

                try {
                    if (!instagramLink.equals(""))
                        openUrl.putExtra(Constant.SHARE_LINK, instagramLink);
                } catch (NullPointerException ignored) {
                    imgInstagram.setVisibility(View.GONE);
                }
                break;

        }

        //Constant.SHARE_LINK

        if (choosenIcon == 1) {
            if (URLUtil.isValidUrl(fbLink)) {
                startActivity(openUrl);
                Animatoo.animateWindmill(getContext());
            } else {
                Constant.showInformationDialog(getContext(), getString(R.string.invalid_link));
            }

        } else if (choosenIcon == 2) {
            if (URLUtil.isValidUrl(twitterLink)) {
                startActivity(openUrl);
                Animatoo.animateWindmill(getContext());
            } else {
                imgTwitter.setVisibility(View.GONE);
                seperatorOne.setVisibility(View.GONE);
                // Constant.showInformationDialog(getContext(), getString(R.string.invalid_link));
            }
        } else if (choosenIcon == 3) {
            if (URLUtil.isValidUrl(instagramLink)) {
                startActivity(openUrl);
                Animatoo.animateWindmill(getContext());
            } else {
                imgInstagram.setVisibility(View.GONE);
                seperatorTwo.setVisibility(View.GONE);
                //Constant.showInformationDialog(getContext(), getString(R.string.invalid_link));
            }
        }


    }
}



