package apps.pixel.the.key.adapters.homeSlider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.selectedRestaurant.SelectedItemKotlinActivity;
import apps.pixel.the.key.utilities.Constant;


public class HomeSliderAdapter extends PagerAdapter {

    private List<String> imageList,IDS;
    private Context context;
    private AppCompatImageView img;
    private List<String> listLogo;
    private List<String> listNameAR;
    private List<String> listNameEn;
    public HomeSliderAdapter(Context context, List<String> imageList, List<String> listLogo, List<String> listNameAR
            , List<String> listNameEn,List<String> IDS) {
        this.context = context;
        this.imageList = imageList;
        this.listLogo = listLogo;
        this.listNameAR = listNameAR;
        this.listNameEn = listNameEn;
        this.IDS = IDS;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object)
    {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.item_slider_home, null);

        initViews(view);

        img.setOnClickListener(v -> {

            Intent intent = new Intent(context, SelectedItemKotlinActivity.class);
            intent.putExtra(Constant.ITEM_SELECTED_ID, IDS.get(position));
            intent.putExtra(Constant.ITEM_SELECTED_REST_LOGO, listLogo.get(position));
            intent.putExtra(Constant.ITEM_SELECTED_NAME_EN, listNameEn.get(position));
            intent.putExtra(Constant.ITEM_SELECTED_NAME_AR, listNameAR.get(position));
            intent.putExtra(Constant.CAT_THAT_SELECTED, Constant.CAT_RESTAURANT_VALUE);

            //Log.d("ID_RESTAURANT", "onCreateView: " + id);

            context.startActivity(intent);
            Animatoo.animateSwipeLeft(context);
        });
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
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object)
    {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}


