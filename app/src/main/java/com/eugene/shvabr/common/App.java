package com.eugene.shvabr.common;

import android.app.Application;

/**
 * Created by Eugene on 04.02.2018.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UIHelper.initialize(getResources());
    }
}
