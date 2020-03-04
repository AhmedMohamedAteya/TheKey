package apps.pixel.the.key.activites.sliderShow;

import android.os.Bundle;

import com.zeuskartik.mediaslider.MediaSliderActivity;

import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.adapters.restaurant.HomeSliderRestaAdapter;
import apps.pixel.the.key.utilities.Constant;

public class sliderShowClass extends MediaSliderActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ArrayList<String> list=new ArrayList<>();
        //list.add("http://pixelserver-001-site61.ctempurl.com/Content/Images/4a3a0d84-db40-430f-b3ad-bcb3d4235581Recepie.jpg");
        if (getIntent().getStringArrayListExtra(Constant.GALLERY_IMGS)!=null)
        loadMediaSliderView(getIntent().getStringArrayListExtra(Constant.GALLERY_IMGS),"image",false,true,false,"","#000000",null,0);

    }
}
