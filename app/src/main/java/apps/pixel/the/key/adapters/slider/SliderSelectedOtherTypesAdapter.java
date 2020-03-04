package apps.pixel.the.key.adapters.slider;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.List;

import apps.pixel.the.key.R;


public class SliderSelectedOtherTypesAdapter extends PagerAdapter {

    private List<String> imageList;
    private Context context;
    private SharedPreferences sharedPreferences;
    private AppCompatImageView img;

    public SliderSelectedOtherTypesAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
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
        View view = inflater.inflate(R.layout.item_slider_home, null);
        img = view.findViewById(R.id.img);


        Picasso.get()
                .load(imageList.get(position))
                .fit()
                .centerCrop()
                .into(img);

        container.addView(view, 0);

        return view;
    }

    private void initViews(View view) {
        img = view.findViewById(R.id.img);

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}



