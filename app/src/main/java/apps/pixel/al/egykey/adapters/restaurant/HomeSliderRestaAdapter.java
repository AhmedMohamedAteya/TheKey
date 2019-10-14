package apps.pixel.al.egykey.adapters.restaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.List;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.retaurant.videoActivity.VideoRestuarantActivity;
import apps.pixel.al.egykey.utilities.Constant;
import apps.pixel.al.egykey.utilities.PhotoFullPopupWindow;

import static apps.pixel.al.egykey.utilities.Constant.KEY_VIDEO_URL;

public class HomeSliderRestaAdapter extends PagerAdapter {

    private Context context;
    private List<String> imageList;
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

        AppCompatImageView imageView = view.findViewById(R.id.imge_slider);

        AppCompatImageView playVideo = view.findViewById(R.id.video);
        if (position == 0) {
            playVideo.setVisibility(View.VISIBLE);
        } else {
            playVideo.setVisibility(View.GONE);
        }

        videoUrl = sharedPreferences.getString(Constant.KEY_VIDEO_URL, "");

        playVideo.setOnClickListener(v -> {
            //KEY_VIDEO_URL
            Intent openVideo = new Intent(context, VideoRestuarantActivity.class);
            openVideo.putExtra(KEY_VIDEO_URL, videoUrl);
            context.startActivity(openVideo);
            Animatoo.animateSplit(context);

//            Intent openVideo = new Intent(context, VideoRestuarantFragment.class);
//            openVideo.putExtra(KEY_VIDEO_URL, videoUrl);
        });

        Picasso.get()
                .load(imageList.get(position))
                .fit()
                .centerCrop()
                .into(imageView);

        imageView.setOnClickListener(v -> {
            // Code to show image in full screen:
            new PhotoFullPopupWindow(context, R.layout.popup_photo_full, imageView, imageList.get(position), null).setAnimationStyle(R.style.Animation);

        });

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
