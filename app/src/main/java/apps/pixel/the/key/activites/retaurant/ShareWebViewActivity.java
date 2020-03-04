package apps.pixel.the.key.activites.retaurant;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.DialogLoader;
import apps.pixel.the.key.utilities.Constant;

public class ShareWebViewActivity extends AppCompatActivity {

    private DialogLoader dialogLoader;
    private WebView mWebView;

    private String getUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_web_view);


        getUrl = getIntent().getStringExtra(Constant.SHARE_LINK);
        Log.d("FB_LINK", "onCreate: " + getIntent().getStringExtra(Constant.SHARE_LINK));

        initViews();

    }


    private void initViews() {
        dialogLoader = new DialogLoader();
        mWebView = findViewById(R.id.web_view);

        openWebSite(getUrl);
    }

    private void openWebSite(String url) {
        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                if (!dialogLoader.isAdded()) {
                    dialogLoader.show(getSupportFragmentManager(), "5");
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (dialogLoader.isAdded()) {
                    dialogLoader.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                dialogLoader.dismiss();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                dialogLoader.dismiss();
            }
        });
        mWebView.getSettings().setSupportZoom(true);
        mWebView.loadUrl(Constant.BASE_URL_HTTP);
        //Log.d("URL_IS_", urlConstant.BASE_URL_HTTP);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Animatoo.animateSlideLeft(this);
    }
}
