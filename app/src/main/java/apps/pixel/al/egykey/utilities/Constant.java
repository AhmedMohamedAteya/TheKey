package apps.pixel.al.egykey.utilities;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import apps.pixel.al.egykey.R;
import libs.mjn.prettydialog.PrettyDialog;
import retrofit2.HttpException;

import static android.content.Context.MODE_PRIVATE;

public final class Constant {

    public static final String SHARED_PREFERENCE = "MySharedPreference";
    public static final String LANGUAGE = "LANG";
    public static final String BASE_URL_HTTP = "http://pixelserver-001-site61.ctempurl.com/".trim();
    public static final String RESTAURANT_SELECTED_ID = "RESTAURANT_SELECTED_ID";
    public static final String KEY_VIDEO_URL = "VIDEO_URL_KEY";
    public static final String Like_STATUS = "LIKE_STATUS";
    public static final String PDF_URL = "PDF_URL";
    public static final String MOB_NUM_FIRST = "MOB_NUM_FIRST";
    public static final String NEWS_IMG = "NEWS_IMG";
    public static final String NEWS_TITLE = "NEWS_TITLE";
    public static final String NEWS_DESC = "NEWS_DESC";

    public static final String SHARE_LINK = "SHARE_LINK";
    public static final String SELECTED_JOB_ID = "SELECTED_JOB_ID";
    public static final float BITMAP_SCALE = 0.4f;
    public static final int BLUR_RADIUS = 8;
    public static final String BASE_PATH_MEDIA = "http://pixelserver-001-site61.ctempurl.com".trim();
    public static final String CURRECT_POSITION_KEY = "KEY_CURRECT_POSITON";
    public static final String KEY_NUMBERS = "KEY_NUMBERS";
    public static final String GALLERY_IMGS = "GALLERY_IMGS_KEY";
    public static final String CURRENT_POSITION = "CURRENT_POSITION";
    public static final String IS_IMG = "IS_IMG";
    public static final String SLIDER_HAS_VIDEO = "HAS_VIDEO";

    public static final String CAT_HOSPITAL = "CAT_HOSPITAL";
    public static final String CAT_CLINIC = "CAT_CLINIC";
    public static final String CAT_PHARMACY = "CAT_PHARMACY";
    public static final String CAT_BEAUTY = "CAT_BEAUTY";
    public static final String CAT_GYM = "CAT_GYM";

    public static final String CAT_HOSPITAL_VALUE = "1";
    public static final String CAT_CLINIC_VALUE = "2";
    public static final String CAT_PHARMACY_VALUE = "3";
    public static final String CAT_BEAUTY_VALUE = "4";
    public static final String CAT_GYM_VALUE = "5";

    public static String FB_LINK;
    public static String INSTAGRAM_LINK;
    public static String TWITTER_LINK;

    public static String convertFileToByteArray(File f) {
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 11];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }

            byteArray = bos.toByteArray();

            Log.e("Byte array", ">" + byteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }

    public static String convertImgToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        Log.d("BASE64", "convertToBase64: " + Base64.encodeToString(imgByte, Base64.DEFAULT));

        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }

    public static void setSwipeLayourColor(Context context, SwipeRefreshLayout swipeLayourColor) {
        swipeLayourColor.setColorSchemeColors(context.getResources().getColor(R.color.pdlg_color_white));
        swipeLayourColor.setColorSchemeResources(
                R.color.colorAccent,
                R.color.timestamp,
                android.R.color.holo_orange_light,
                android.R.color.black,
                R.color.colorPrimary);
    }

    public static String getLng(Context context) {
        SharedPreferences mSharedPreferences;

        mSharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);
        return mSharedPreferences.getString(Constant.LANGUAGE, Locale.getDefault().getLanguage());
    }

    public static void changeLang(Context mContext, String countryCode) {
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(countryCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        view = ((AppCompatActivity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


    }

    public static void hideStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = activity.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public static void clearStackIntent(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);

        context.startActivity(intent);
        ((AppCompatActivity) context).finish();

    }

    public static void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right); //TODO (Other Option)R.anim.layout_animation

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static void expand(View view) {
        //set Visible
        view.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);

        ValueAnimator mAnimator = slideAnimator(0, view.getMeasuredHeight(), view);
        mAnimator.start();
    }

    public static void makeUnderlineForText(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    public static void collapse(final View view) {

        int finalHeight = view.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0, view);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.start();

    }

    public static ValueAnimator slideAnimator(int start, int end, final View view) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(valueAnimator -> {
            //Update Height
            int value = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = value;
            view.setLayoutParams(layoutParams);
        });
        return animator;
    }

    public static void handleError(Context context, Throwable throwable) {
        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("msg");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.getErrorDependingOnResponse(context, message);

        }
    }

    private static void getErrorDependingOnResponse(Context context, String response) {
        String message;
        switch (response) {
            case "1":
                message = "error one";
                break;
            default:
                message = context.getString(R.string.default_error);
                break;
        }

        Constant.showErrorDialog(context, message);

        Log.d("ERROR_TAG", "handleError: " + response);
    }

    public static void showErrorDialog(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_error)
                .setTitle(message)
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.pdlg_color_red, prettyDialog::dismiss)
                .show();
    }

    public static void showInformationDialog(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_information)
                .setTitle(message)
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.color_blue, prettyDialog::dismiss)
                .show();
    }

    public static void showInformationDialogForMenu(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_information)
                .setTitle(message)
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.color_blue, () -> {
                    prettyDialog.dismiss();
                    ((AppCompatActivity) context).finish();
                    Animatoo.animateSlideRight(context);
                })
                .show();
    }

    public static Bitmap fastblur(Bitmap sentBitmap) {
        float scale = BITMAP_SCALE;
        int radius = BLUR_RADIUS;
        int width = Math.round(sentBitmap.getWidth() * scale);
        int height = Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);

    }


    public static void initializeThumbnail(Context context, AppCompatImageView imageView, String path) {
        long microSecond = 1000000;

        RequestOptions requestOptions = new RequestOptions()
                .frame(microSecond)
                .override(600, 200)
                .fitCenter()
                .placeholder(R.color.place_holder_color)//R.drawable.shape_light_app_color
                .error(R.drawable.shape_light_app_color);

        Glide.with(context)
                .load(path)
                .apply(requestOptions)
                .thumbnail(Glide.with(context)
                        .load(path))
                .into(imageView);


    }


}
