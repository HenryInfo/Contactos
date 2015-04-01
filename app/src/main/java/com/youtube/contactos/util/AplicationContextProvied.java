package com.youtube.contactos.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by henryyerrybravosanchez on 3/30/15.
 */
public class AplicationContextProvied extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
