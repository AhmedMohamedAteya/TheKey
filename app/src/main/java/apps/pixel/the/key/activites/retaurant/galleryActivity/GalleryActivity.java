package apps.pixel.the.key.activites.retaurant.galleryActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import apps.pixel.the.key.R;
import apps.pixel.the.key.adapters.restaurant.GalleryAdapter;
import apps.pixel.the.key.fragments.selectedCatHome.home.HomeRestFragment;
import apps.pixel.the.key.utilities.Constant;
import apps.pixel.the.key.utilities.PhotoFullPopupWindow;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.OnClickHandler {

    private AppCompatImageView mImgBack;
    private RecyclerView mRV;
    private ArrayList<String> mListImgsPaths;
    private AppCompatImageView mImgPhoto;
    private String path;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Animatoo.animateSlideRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        try {
            path = HomeRestFragment.mListImgsPaths.get(0);
            initViews();

        } catch (Exception ignored) {
            Constant.showInformationDialogForMenu(this, getString(R.string.there_is_no_avaiable_menu));
        }
    }

    private void initViews() {
        mImgBack = findViewById(R.id.arrow_back_page_two);
        mImgBack.setOnClickListener(v -> {
            onBackPressed();
        });
        mImgPhoto = findViewById(R.id.selected_img);


        mRV = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRV.setLayoutManager(layoutManager);


        if (getIntent().hasExtra(Constant.GALLERY_IMGS)) {
            mListImgsPaths = getIntent().getStringArrayListExtra(Constant.GALLERY_IMGS);
            loadRecyclerData();
        }

        mImgPhoto.setOnClickListener(v -> {
            new PhotoFullPopupWindow(this, R.layout.popup_photo_full, mImgPhoto, path, null).setAnimationStyle(R.style.Animation);

        });
    }

    private void loadRecyclerData() {
        Log.d("SIZE_OF_LIST", "loadRecyclerData: " + mListImgsPaths.size());
        GalleryAdapter galleryAdapter = new GalleryAdapter(this, mListImgsPaths, this);
        mRV.setAdapter(galleryAdapter);
        Constant.runLayoutAnimation(mRV);


        setImageUsingPicasso(mListImgsPaths.get(0));

    }

    private void setImageUsingPicasso(String path) {
        try {
            Picasso.get()
                    .load(path)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.shape_light_app_color)
                    .into(mImgPhoto);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void onClick(String path) {
        this.path = path;
        setImageUsingPicasso(path);
    }
}
