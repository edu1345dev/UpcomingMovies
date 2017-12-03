package com.android.josesantos.upcomingmovies;

import android.app.Application;

import com.android.josesantos.upcomingmovies.injection.AppComponent;
import com.android.josesantos.upcomingmovies.injection.DaggerAppComponent;

/**
 * Created by josesantos on 03/12/17.
 */

public class AppApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
