package apps.pixel.the.key.adapters.slider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import apps.pixel.the.key.R;

public class TutorialsSliderAdapter extends PagerAdapter {

    private List<Drawable> imageList;
    private Context context;
    private SharedPreferences sharedPreferences;
    private AppCompatImageView img;

    public TutorialsSliderAdapter(Context context, List<Drawable> imageList) {
        this.context = context;
        this.imageList = imageList;

    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.item_slider_intro, null);

        initViews(view);


//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.fitCenter();

        Glide.with(context)
                .load(imageList.get(position))
                .into(img);
        //                .apply(requestOptions)

        container.addView(view, 0);

        return view;
    }

    private void initViews(View view) {
        img = view.findViewById(R.id.img);
    }


    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}

