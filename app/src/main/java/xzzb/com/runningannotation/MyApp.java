package xzzb.com.runningannotation;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LRouter.init(this);
    }
}
