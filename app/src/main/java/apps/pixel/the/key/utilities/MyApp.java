package apps.pixel.the.key.utilities;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public final class MyApp extends Application {
    private static Context context;

    public static Context getAppContext() {
        return MyApp.context;
    }

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        MyApp.context = getApplicationContext();
    }

}