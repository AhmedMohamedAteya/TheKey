package apps.pixel.the.key.dialog.restuarant;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import apps.pixel.the.key.R;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;

import static apps.pixel.the.key.utilities.Constant.KEY_VIDEO_URL;
import static apps.pixel.the.key.utilities.Constant.NEWS_DESC;
import static apps.pixel.the.key.utilities.Constant.NEWS_IMG;
import static apps.pixel.the.key.utilities.Constant.NEWS_TITLE;

public class DialogNewsDetails extends DialogFragment {
    private AppCompatImageView imgNew;
    private CairoRegularTextView txtDesc;
    private CairoBoldTextView txtTitle;
    private SharedPreferences sharedPreferences;
    private AppCompatImageView mImgPlayVideo;

    private String isImg;

    private String imgUrl, title, desc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_new_details, container, true);
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


        imgNew = view.findViewById(R.id.img_new);
        txtDesc = view.findViewById(R.id.txt_desc);
        txtTitle = view.findViewById(R.id.txt_title);
        mImgPlayVideo = view.findViewById(R.id.btn_play);
        mImgPlayVideo.setVisibility(View.GONE);

        /*
        editor.putString(Constant.NEWS_IMG, img);
        editor.putString(Constant.NEWS_TITLE, title);
        editor.putString(Constant.NEWS_DESC, desc);
         */

        imgUrl = sharedPreferences.getString(NEWS_IMG, "");
        title = sharedPreferences.getString(NEWS_TITLE, "");
        desc = sharedPreferences.getString(NEWS_DESC, "");
        isImg = sharedPreferences.getString(Constant.IS_IMG, "true");

        txtTitle.setText(title);
        txtDesc.setText(desc);

        if (isImg.equals("true")) {
            Picasso.get()
                    .load(imgUrl)
                    .fit()
                    .placeholder(R.color.place_holder_color)
                    .centerCrop()
                    .into(imgNew);
        } else {
            mImgPlayVideo.setVisibility(View.VISIBLE);
            Constant.initializeThumbnail(getContext(), imgNew, imgUrl);
        }

        mImgPlayVideo.setOnClickListener(v -> {
            DialogVideo dialogVideo = new DialogVideo();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_VIDEO_URL, imgUrl);
            dialogVideo.setArguments(bundle);

            if (!dialogVideo.isAdded()) {
                dialogVideo.show(((AppCompatActivity) getContext()).getSupportFragmentManager(), "11");
            }

        });

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
}




