package apps.pixel.al.egykey.utilities;

import android.app.Application;
import android.content.Context;

public final class MyApp extends Application {
    private static Context context;

    public static Context getAppContext() {
        return MyApp.context;
    }

    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

}