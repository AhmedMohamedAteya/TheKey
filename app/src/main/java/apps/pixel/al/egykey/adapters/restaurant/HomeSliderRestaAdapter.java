package apps.pixel.al.egykey.adapters.restaurant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.dialog.restuarant.DialogVideo;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.PhotoFullPopupWindow;

import static apps.pixel.al.egykey.utilities.Constant.KEY_VIDEO_URL;

public class HomeSliderRestaAdapter extends PagerAdapter {

    public static List<String> imageList;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String videoUrl;

    public HomeSliderRestaAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider, null);

        videoUrl = sharedPreferences.getString(Constant.KEY_VIDEO_URL, "");
        Log.d("VIDEO_URL", "instantiateItem: " + videoUrl);

        AppCompatImageView imageView = view.findViewById(R.id.imge_slider);

        AppCompatImageView playVideo = view.findViewById(R.id.video);
        playVideo.setVisibility(View.GONE);

        if (sharedPreferences.getString(Constant.SLIDER_HAS_VIDEO, "false".trim()).equals("true".trim())) {
            if (position == 0) {
                Constant.initializeThumbnail(context, imageView, videoUrl);
                Log.d("FOR_THUMBNIAL", videoUrl);
                playVideo.setVisibility(View.VISIBLE);
            } else {
                playVideo.setVisibility(View.GONE);
            }
        }


        playVideo.setOnClickListener(v -> {
            //KEY_VIDEO_URL
            handlingOfVideoClickListner();
//            Intent openVideo = new Intent(context, VideoRestuarantActivity.class);
//            openVideo.putExtra(KEY_VIDEO_URL, videoUrl);
//            context.startActivity(openVideo);
//            Animatoo.animateSplit(context);

//            Intent openVideo = new Intent(context, VideoRestuarantFragment.class);
//            openVideo.putExtra(KEY_VIDEO_URL, videoUrl);
        });


//        if (position != 0) {
        Picasso.get()
                .load(imageList.get(position))
                .fit()
                .centerCrop()
                .into(imageView);
        // }

        imageView.setOnClickListener(v -> {
            // Code to show image in full screen:
            if (position != 0) {
                new PhotoFullPopupWindow(context, R.layout.popup_photo_full, imageView, imageList.get(position), null).setAnimationStyle(R.style.Animation);
            } else {
                Log.d("URL_OF_VIEO_ZERO_", "instantiateItem: " + videoUrl);
                if (videoUrl.equals("http://pixelserver-001-site61.ctempurl.com".trim()))
                    new PhotoFullPopupWindow(context, R.layout.popup_photo_full, imageView, imageList.get(position), null).setAnimationStyle(R.style.Animation);
                else
                    handlingOfVideoClickListner();
            }


        });

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    private void handlingOfVideoClickListner() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_VIDEO_URL, videoUrl);
        DialogVideo dialogVideo = new DialogVideo();
        dialogVideo.setArguments(bundle);
        if (!dialogVideo.isAdded()) {
            dialogVideo.show(((AppCompatActivity) context).getSupportFragmentManager(), "1");
            Animatoo.animateSplit(context);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
