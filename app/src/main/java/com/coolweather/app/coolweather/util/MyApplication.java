package com.coolweather.app.coolweather.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by baosisi on 2015/8/19.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
