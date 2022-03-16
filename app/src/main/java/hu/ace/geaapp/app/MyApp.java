package hu.ace.geaapp.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static Context appcontext;

    public static Context getAppContext(){
        return MyApp.appcontext;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        MyApp.appcontext = getApplicationContext();
    }
}
