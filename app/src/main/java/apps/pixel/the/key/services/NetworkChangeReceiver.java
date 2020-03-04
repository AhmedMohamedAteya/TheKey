package apps.pixel.the.key.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.the.key.activites.Home.HomeActivity;
import apps.pixel.the.key.utilities.Validation;

import static apps.pixel.the.key.activites.beforeHome.SplashActivity.showLoader;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (Validation.isConnected(context)) {
                showLoader(true);
                Log.d("CHECK", "Online Connect Intenet ");


                new Handler().postDelayed(() -> {

                    Intent startIntent = new Intent(context, HomeActivity.class);
                    context.startActivity(startIntent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ((Activity) context).finish();
                    Animatoo.animateSplit(context);

                }, 5000);


            } else {
                showLoader(false);


                //infinityGif(context);

                Log.d("CHECK", "Conectivity Failure !!! ");

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.d("NULL", "onReceive: " + e.getMessage());
        }
    }

   /* private void infinityGif(Context context) {
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
*/
}