package apps.pixel.al.egykey.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;

import androidx.annotation.Nullable;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.activites.beforeHome.SignInActivity;
import apps.pixel.al.egykey.activites.retaurant.restaurants.RestaurantsActivity;
import apps.pixel.al.egykey.utilities.Validation;

import static apps.pixel.al.egykey.activites.beforeHome.SplashActivity.gifImageView;
import static apps.pixel.al.egykey.activites.beforeHome.SplashActivity.showLoader;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (Validation.isConnected(context)) {
                showLoader(true);
                Log.d("CHECK", "Online Connect Intenet ");


                new Handler().postDelayed(() -> {

                    Intent startIntent = new Intent(context, SignInActivity.class);
                    context.startActivity(startIntent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ((Activity) context).finish();
                    Animatoo.animateSplit(context);

                }, 5000);


            } else {
                showLoader(false);


                infinityGif(context);

                Log.d("CHECK", "Conectivity Failure !!! ");

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.d("NULL", "onReceive: " + e.getMessage());
        }
    }

    private void infinityGif(Context context) {
        Glide.with(context).asGif()
                .apply(new RequestOptions()
                        .override(200, 600))
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (resource instanceof GifDrawable) {
                            resource.setLoopCount(Animation.INFINITE);
                        }
                        return false;
                    }
                }).load(R.drawable.splash_gif).into(gifImageView);
    }

}